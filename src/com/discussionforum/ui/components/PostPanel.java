package com.discussionforum.ui.components;

import com.discussionforum.service.MockDataService.Post;
import com.discussionforum.ui.DashboardFrame;
import com.discussionforum.ui.Theme;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PostPanel extends JPanel {
    private Post post;
    private DashboardFrame parent;

    public PostPanel(Post post, DashboardFrame parent) {
        this.post = post;
        this.parent = parent;
        
        setupPanel();
        createContent();
        addInteraction();
    }

    private void setupPanel() {
        setLayout(new BorderLayout());
        setBackground(Theme.CARD_BACKGROUND);
        setBorder(new ModernCardBorder());
        setOpaque(true);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Set a minimum height to ensure content is visible
        setMinimumSize(new Dimension(0, 120));
        setPreferredSize(new Dimension(Integer.MAX_VALUE, 120));
    }

    private void createContent() {
        // Main content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Theme.CARD_BACKGROUND);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        contentPanel.setOpaque(true);

        // Create left side with post information
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Theme.CARD_BACKGROUND);
        leftPanel.setOpaque(true);

        // Post title
        JLabel titleLabel = new JLabel(post.title);
        titleLabel.setFont(Theme.FONT_SUBTITLE);
        titleLabel.setForeground(Theme.TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Post content preview (truncated and styled)
        String contentPreview = post.content.length() > 150 ? 
            post.content.substring(0, 150) + "..." : post.content;
        
        JTextArea contentArea = new JTextArea(contentPreview);
        contentArea.setFont(Theme.FONT_NORMAL);
        contentArea.setForeground(Theme.TEXT_SECONDARY);
        contentArea.setBackground(Theme.CARD_BACKGROUND);
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setOpaque(true);
        contentArea.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        contentArea.setFocusable(false);
        contentArea.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Author and metadata panel
        JPanel metaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        metaPanel.setBackground(Theme.CARD_BACKGROUND);
        metaPanel.setOpaque(true);

        JLabel authorIcon = new JLabel("USER ");
        authorIcon.setFont(new Font("Arial", Font.BOLD, 10));

        JLabel authorLabel = new JLabel("by " + post.author);
        authorLabel.setFont(Theme.FONT_SMALL);
        authorLabel.setForeground(Theme.TEXT_TERTIARY);

        // Comments count
        JLabel commentsCount = new JLabel("  " + post.comments.size() + " comments");
        commentsCount.setFont(Theme.FONT_SMALL);
        commentsCount.setForeground(Theme.TEXT_TERTIARY);

        metaPanel.add(authorIcon);
        metaPanel.add(authorLabel);
        metaPanel.add(commentsCount);

        // Add components to left panel
        leftPanel.add(titleLabel);
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(contentArea);
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(metaPanel);

        // Action panel with button
        JPanel actionPanel = new JPanel(new BorderLayout());
        actionPanel.setBackground(Theme.CARD_BACKGROUND);
        actionPanel.setOpaque(true);

        ModernButton viewBtn = new ModernButton("Read >", 
            Theme.PRIMARY, Theme.PRIMARY_DARK, Theme.TEXT_LIGHT);
        viewBtn.setPreferredSize(new Dimension(120, 36));
        viewBtn.addActionListener(e -> parent.openPost(post.id));

        actionPanel.add(viewBtn, BorderLayout.NORTH);

        // Layout
        contentPanel.add(leftPanel, BorderLayout.CENTER);
        contentPanel.add(actionPanel, BorderLayout.EAST);

        add(contentPanel, BorderLayout.CENTER);
    }

    private void addInteraction() {
        MouseAdapter clickHandler = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Theme.HOVER_BACKGROUND);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Theme.CARD_BACKGROUND);
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                parent.openPost(post.id);
            }
        };
        
        // Add mouse listener to the main panel
        addMouseListener(clickHandler);
        
        // Add mouse listeners to all child components to ensure clicks work everywhere
        addMouseListenerToChildren(this, clickHandler);
    }
    
    private void addMouseListenerToChildren(Container container, MouseAdapter listener) {
        for (Component component : container.getComponents()) {
            if (component instanceof Container) {
                addMouseListenerToChildren((Container) component, listener);
            }
            component.addMouseListener(listener);
        }
    }

    private static class ModernCardBorder extends AbstractBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Fill the entire background first
            g2d.setColor(Theme.CARD_BACKGROUND);
            g2d.fillRoundRect(x, y, width, height, Theme.CARD_ARC, Theme.CARD_ARC);
            
            // Multiple shadow layers for depth
            for (int i = 0; i < 3; i++) {
                g2d.setColor(new Color(0, 0, 0, 10 - i * 3));
                g2d.fillRoundRect(x + 2 + i, y + 2 + i, width - 4 - i * 2, height - 4 - i * 2, 
                    Theme.CARD_ARC, Theme.CARD_ARC);
            }
            
            // Main card background (ensure it's solid)
            g2d.setColor(Theme.CARD_BACKGROUND);
            g2d.fillRoundRect(x + 1, y + 1, width - 5, height - 5, Theme.CARD_ARC, Theme.CARD_ARC);
            
            // Subtle border
            g2d.setColor(Theme.BORDER_LIGHT);
            g2d.drawRoundRect(x + 1, y + 1, width - 6, height - 6, Theme.CARD_ARC, Theme.CARD_ARC);
            
            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(5, 5, 8, 8);
        }
    }
}
