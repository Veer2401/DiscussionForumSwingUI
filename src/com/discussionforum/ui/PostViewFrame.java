package com.discussionforum.ui;

import com.discussionforum.service.MockDataService;
import com.discussionforum.service.MockDataService.Post;
import com.discussionforum.ui.components.ModernButton;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.util.List;

public class PostViewFrame extends JFrame {
    private JTextArea commentArea;
    private JPanel commentsPanel;
    private int postId;

    public PostViewFrame(int postId) {
        this.postId = postId;
        Post post = MockDataService.getPostById(postId);

        setTitle("Discussion Forum - " + post.title);
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Theme.BACKGROUND);
        setLayout(new BorderLayout());

        // Create main panel
        JPanel mainPanel = createMainPanel(post);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createMainPanel(Post post) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Theme.BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Header with back button
        JPanel headerPanel = createHeaderPanel();
        panel.add(headerPanel, BorderLayout.NORTH);

        // Content area
        JPanel contentPanel = createContentPanel(post);
        panel.add(contentPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Theme.BACKGROUND);
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        ModernButton backBtn = ModernButton.createSecondaryButton("Back");
        backBtn.setPreferredSize(new Dimension(180, 35));
        backBtn.addActionListener(e -> Navigation.switchFrame(this, new DashboardFrame()));

        header.add(backBtn, BorderLayout.WEST);
        return header;
    }

    private JPanel createContentPanel(Post post) {
        JPanel content = new JPanel(new BorderLayout(0, 20));
        content.setBackground(Theme.BACKGROUND);

        // Post content
        JPanel postPanel = createPostPanel(post);
        content.add(postPanel, BorderLayout.NORTH);

        // Comments section
        JPanel commentsSection = createCommentsSection();
        content.add(commentsSection, BorderLayout.CENTER);

        return content;
    }

    private JPanel createPostPanel(Post post) {
        JPanel postContainer = new JPanel(new BorderLayout());
        postContainer.setBackground(Theme.CARD_BACKGROUND);
        postContainer.setBorder(Theme.CARD_BORDER);

        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.setBackground(Theme.CARD_BACKGROUND);
        postPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // Post title
        JLabel titleLabel = new JLabel(post.title);
        titleLabel.setFont(Theme.FONT_TITLE);
        titleLabel.setForeground(Theme.TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Author info
        JPanel authorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        authorPanel.setBackground(Theme.CARD_BACKGROUND);

        JLabel authorIcon = new JLabel("USER ");
        authorIcon.setFont(new Font("Arial", Font.BOLD, 12));

        JLabel authorLabel = new JLabel("by " + post.author);
        authorLabel.setFont(Theme.FONT_SMALL);
        authorLabel.setForeground(Theme.TEXT_SECONDARY);

        authorPanel.add(authorIcon);
        authorPanel.add(authorLabel);
        authorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Post content
        JTextArea contentArea = new JTextArea(post.content);
        contentArea.setFont(Theme.FONT_NORMAL);
        contentArea.setForeground(Theme.TEXT_PRIMARY);
        contentArea.setBackground(Theme.CARD_BACKGROUND);
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        contentArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        postPanel.add(titleLabel);
        postPanel.add(Box.createVerticalStrut(10));
        postPanel.add(authorPanel);
        postPanel.add(contentArea);

        postContainer.add(postPanel, BorderLayout.CENTER);
        return postContainer;
    }

    private JPanel createCommentsSection() {
        JPanel section = new JPanel(new BorderLayout(0, 15));
        section.setBackground(Theme.BACKGROUND);

        // Comments header
        JLabel commentsHeader = new JLabel("Comments (" + MockDataService.getPostById(postId).comments.size() + ")");
        commentsHeader.setFont(Theme.FONT_SUBTITLE);
        commentsHeader.setForeground(Theme.TEXT_PRIMARY);

        // Comments list
        commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        commentsPanel.setBackground(Theme.BACKGROUND);

        JScrollPane commentsScrollPane = new JScrollPane(commentsPanel);
        commentsScrollPane.setBorder(null);
        commentsScrollPane.getViewport().setBackground(Theme.BACKGROUND);
        commentsScrollPane.setPreferredSize(new Dimension(0, 200));

        // Add comment panel
        JPanel addCommentPanel = createAddCommentPanel();

        section.add(commentsHeader, BorderLayout.NORTH);
        section.add(commentsScrollPane, BorderLayout.CENTER);
        section.add(addCommentPanel, BorderLayout.SOUTH);

        refreshComments();
        return section;
    }

    private JPanel createAddCommentPanel() {
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(new Color(245, 248, 250));
        container.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 150, 0), 2),
            BorderFactory.createEmptyBorder(12, 16, 12, 16)
        ));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 248, 250));
        panel.setOpaque(false);

        // Label
        JLabel label = new JLabel("Add comment");
        label.setFont(Theme.FONT_NORMAL);
        label.setForeground(Theme.TEXT_PRIMARY);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Comment text area
        commentArea = new JTextArea(2, 0);
        commentArea.setFont(new Font("Arial", Font.PLAIN, 13));
        commentArea.setBackground(Color.WHITE);
        commentArea.setForeground(Color.BLACK);
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        commentArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 150, 0), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        // Add placeholder text
        commentArea.setText("Type comment...");
        commentArea.setForeground(new Color(150, 150, 150));
        commentArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (commentArea.getText().equals("Type comment...")) {
                    commentArea.setText("");
                    commentArea.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (commentArea.getText().trim().isEmpty()) {
                    commentArea.setText("Type comment...");
                    commentArea.setForeground(new Color(150, 150, 150));
                }
            }
        });

        JScrollPane commentScrollPane = new JScrollPane(commentArea);
        commentScrollPane.setBorder(null);
        commentScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(245, 248, 250));
        buttonPanel.setOpaque(false);

        ModernButton addCommentBtn = ModernButton.createPrimaryButton("Post");
        addCommentBtn.setPreferredSize(new Dimension(100, 40));

        buttonPanel.add(addCommentBtn);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(label);
        panel.add(Box.createVerticalStrut(6));
        panel.add(commentScrollPane);
        panel.add(Box.createVerticalStrut(8));
        panel.add(buttonPanel);

        // Event listener
        addCommentBtn.addActionListener(e -> {
            String comment = commentArea.getText().trim();
            if (!comment.isEmpty() && !comment.equals("Type comment...")) {
                MockDataService.addComment(postId, comment);
                commentArea.setText("Type comment...");
                commentArea.setForeground(new Color(150, 150, 150));
                refreshComments();
                // Scroll to bottom to show new comment
                SwingUtilities.invokeLater(() -> {
                    JScrollPane scrollPane = (JScrollPane) commentsPanel.getParent().getParent();
                    JScrollBar vertical = scrollPane.getVerticalScrollBar();
                    vertical.setValue(vertical.getMaximum());
                });
            } else {
                JOptionPane.showMessageDialog(this, "Enter a comment", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        container.add(panel, BorderLayout.CENTER);
        return container;
    }

    private void refreshComments() {
        commentsPanel.removeAll();
        MockDataService.Post post = MockDataService.getPostById(postId);
        List<String> comments = post.comments;

        if (comments.isEmpty()) {
            // Empty state
            JPanel emptyPanel = new JPanel();
            emptyPanel.setLayout(new BoxLayout(emptyPanel, BoxLayout.Y_AXIS));
            emptyPanel.setBackground(Theme.BACKGROUND);
            emptyPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

            JLabel emptyIcon = new JLabel("NO COMMENTS");
            emptyIcon.setFont(new Font("Arial", Font.BOLD, 16));
            emptyIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel emptyText = new JLabel("No comments yet");
            emptyText.setFont(Theme.FONT_NORMAL);
            emptyText.setForeground(Theme.TEXT_SECONDARY);
            emptyText.setAlignmentX(Component.CENTER_ALIGNMENT);

            emptyPanel.add(emptyIcon);
            emptyPanel.add(Box.createVerticalStrut(10));
            emptyPanel.add(emptyText);

            commentsPanel.add(emptyPanel);
        } else {
            for (String comment : comments) {
                JPanel commentPanel = createCommentPanel(comment);
                commentsPanel.add(commentPanel);
                commentsPanel.add(Box.createVerticalStrut(8));
            }
        }

        // Force layout update
        commentsPanel.revalidate();
        commentsPanel.repaint();
        
        // Ensure the panel is visible
        commentsPanel.setVisible(true);
    }

    private JPanel createCommentPanel(String comment) {
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(new Color(220, 248, 198)); // Light green like WhatsApp
        container.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        container.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(220, 248, 198));
        panel.setOpaque(false);

        // Comment author and timestamp
        JPanel authorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        authorPanel.setBackground(new Color(220, 248, 198));
        authorPanel.setOpaque(false);

        JLabel authorLabel = new JLabel(MockDataService.getCurrentUser().username);
        authorLabel.setFont(new Font("Arial", Font.BOLD, 11));
        authorLabel.setForeground(new Color(0, 100, 0));

        JLabel timeLabel = new JLabel(" • " + java.time.LocalTime.now().toString().substring(0, 5));
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        timeLabel.setForeground(new Color(100, 100, 100));

        authorPanel.add(authorLabel);
        authorPanel.add(timeLabel);
        authorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Comment text with better formatting
        JLabel commentLabel = new JLabel("<html><div style='width: 400px; word-wrap: break-word;'>" + 
            comment.replace("\n", "<br/>") + "</div></html>");
        commentLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        commentLabel.setForeground(Color.BLACK);
        commentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(authorPanel);
        panel.add(Box.createVerticalStrut(3));
        panel.add(commentLabel);

        container.add(panel, BorderLayout.CENTER);
        return container;
    }

    private static class CommentBorder extends AbstractBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(new Color(240, 240, 240));
            g2d.drawRoundRect(x, y, width - 1, height - 1, 8, 8);
            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(5, 5, 5, 5);
        }
    }
}
