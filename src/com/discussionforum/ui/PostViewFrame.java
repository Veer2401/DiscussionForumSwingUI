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

        ModernButton backBtn = ModernButton.createSecondaryButton("← Back to Dashboard");
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

        JLabel authorIcon = new JLabel("👤 ");
        authorIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));

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
        JLabel commentsHeader = new JLabel("💬 Comments (" + MockDataService.getPostById(postId).comments.size() + ")");
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
        container.setBackground(Theme.CARD_BACKGROUND);
        container.setBorder(Theme.CARD_BORDER);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Theme.CARD_BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Label
        JLabel label = new JLabel("Add a comment");
        label.setFont(Theme.FONT_NORMAL);
        label.setForeground(Theme.TEXT_PRIMARY);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Comment text area
        commentArea = new JTextArea(3, 0);
        commentArea.setFont(Theme.FONT_NORMAL);
        commentArea.setBackground(Theme.CARD_BACKGROUND);
        commentArea.setForeground(Theme.TEXT_PRIMARY);
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        commentArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JScrollPane commentScrollPane = new JScrollPane(commentArea);
        commentScrollPane.setBorder(null);
        commentScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Theme.CARD_BACKGROUND);

        ModernButton addCommentBtn = ModernButton.createPrimaryButton("Post Comment");
        addCommentBtn.setPreferredSize(new Dimension(120, 35));

        buttonPanel.add(addCommentBtn);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(label);
        panel.add(Box.createVerticalStrut(8));
        panel.add(commentScrollPane);
        panel.add(Box.createVerticalStrut(10));
        panel.add(buttonPanel);

        // Event listener
        addCommentBtn.addActionListener(e -> {
            String comment = commentArea.getText().trim();
            if (!comment.isEmpty()) {
                MockDataService.addComment(postId, comment);
                commentArea.setText("");
                refreshComments();
                // Refresh the comments section
                refreshComments();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a comment.", "Error", JOptionPane.ERROR_MESSAGE);
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

            JLabel emptyIcon = new JLabel("💭");
            emptyIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
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
                commentsPanel.add(Box.createVerticalStrut(10));
            }
        }

        commentsPanel.revalidate();
        commentsPanel.repaint();
    }

    private JPanel createCommentPanel(String comment) {
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Theme.CARD_BACKGROUND);
        container.setBorder(new CommentBorder());
        container.setMaximumSize(new Dimension(Integer.MAX_VALUE, container.getPreferredSize().height));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Theme.CARD_BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));

        // Comment author (using current user for now)
        JPanel authorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        authorPanel.setBackground(Theme.CARD_BACKGROUND);

        JLabel authorIcon = new JLabel("👤 ");
        authorIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));

        JLabel authorLabel = new JLabel(MockDataService.getCurrentUser().username);
        authorLabel.setFont(Theme.FONT_SMALL);
        authorLabel.setForeground(Theme.TEXT_SECONDARY);

        authorPanel.add(authorIcon);
        authorPanel.add(authorLabel);
        authorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Comment text
        JLabel commentLabel = new JLabel("<html><div style='width: 400px;'>" + comment + "</div></html>");
        commentLabel.setFont(Theme.FONT_NORMAL);
        commentLabel.setForeground(Theme.TEXT_PRIMARY);
        commentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(authorPanel);
        panel.add(Box.createVerticalStrut(5));
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
