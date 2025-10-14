package com.discussionforum.ui;

import com.discussionforum.service.MockDataService;
import com.discussionforum.ui.components.ModernButton;
import com.discussionforum.ui.components.ModernTextField;

import javax.swing.*;
import java.awt.*;

public class CreatePostFrame extends JFrame {
    private ModernTextField titleField;
    private JTextArea contentArea;

    public CreatePostFrame() {
        setTitle("Discussion Forum - Create Post");
        setSize(700, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Theme.BACKGROUND);
        setLayout(new BorderLayout());

        // Create main panel
        JPanel mainPanel = createMainPanel();
        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Theme.BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Header section
        JPanel headerPanel = createHeaderPanel();
        panel.add(headerPanel);
        panel.add(Box.createVerticalStrut(30));

        // Form section
        JPanel formPanel = createFormPanel();
        panel.add(formPanel);

        return panel;
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(Theme.BACKGROUND);

        // App icon/logo placeholder
        JLabel iconLabel = new JLabel("✏️");
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 56));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Title
        JLabel titleLabel = new JLabel("Create New Post");
        titleLabel.setFont(Theme.FONT_TITLE);
        titleLabel.setForeground(Theme.TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Share your thoughts with the community");
        subtitleLabel.setFont(Theme.FONT_NORMAL);
        subtitleLabel.setForeground(Theme.TEXT_SECONDARY);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(iconLabel);
        header.add(Box.createVerticalStrut(10));
        header.add(titleLabel);
        header.add(Box.createVerticalStrut(5));
        header.add(subtitleLabel);

        return header;
    }

    private JPanel createFormPanel() {
        JPanel formContainer = new JPanel(new BorderLayout());
        formContainer.setBackground(Theme.CARD_BACKGROUND);
        formContainer.setBorder(Theme.CARD_BORDER);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Theme.CARD_BACKGROUND);
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title field
        JLabel titleLabel = new JLabel("Post Title");
        titleLabel.setFont(Theme.FONT_NORMAL);
        titleLabel.setForeground(Theme.TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        titleField = new ModernTextField("Enter an engaging title...");
        titleField.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        form.add(titleLabel);
        form.add(Box.createVerticalStrut(8));
        form.add(titleField);
        form.add(Box.createVerticalStrut(25));

        // Content area
        JLabel contentLabel = new JLabel("Post Content");
        contentLabel.setFont(Theme.FONT_NORMAL);
        contentLabel.setForeground(Theme.TEXT_PRIMARY);
        contentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        contentArea = createModernTextArea();
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        contentScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentScrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        contentScrollPane.setPreferredSize(new Dimension(Integer.MAX_VALUE, 300));
        contentScrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        form.add(contentLabel);
        form.add(Box.createVerticalStrut(8));
        form.add(contentScrollPane);
        form.add(Box.createVerticalStrut(30));

        // Buttons
        JPanel buttonPanel = createButtonPanel();
        form.add(buttonPanel);

        formContainer.add(form, BorderLayout.CENTER);
        return formContainer;
    }

    private JTextArea createModernTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setFont(Theme.FONT_NORMAL);
        textArea.setBackground(Theme.CARD_BACKGROUND);
        textArea.setForeground(Theme.TEXT_PRIMARY);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        textArea.setText("Share your thoughts, ask questions, or start a discussion...");
        textArea.setForeground(Theme.TEXT_SECONDARY);

        // Add focus listener for placeholder behavior
        textArea.addFocusListener(new java.awt.event.FocusAdapter() {
            private boolean isPlaceholder = true;

            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (isPlaceholder) {
                    textArea.setText("");
                    textArea.setForeground(Theme.TEXT_PRIMARY);
                    isPlaceholder = false;
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (textArea.getText().trim().isEmpty()) {
                    textArea.setText("Share your thoughts, ask questions, or start a discussion...");
                    textArea.setForeground(Theme.TEXT_SECONDARY);
                    isPlaceholder = true;
                }
            }
        });

        return textArea;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Theme.CARD_BACKGROUND);

        ModernButton backBtn = ModernButton.createSecondaryButton("Cancel");
        backBtn.setPreferredSize(new Dimension(120, Theme.BUTTON_HEIGHT));

        ModernButton postBtn = ModernButton.createAccentButton("Publish Post");
        postBtn.setPreferredSize(new Dimension(140, Theme.BUTTON_HEIGHT));

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(backBtn);
        buttonPanel.add(Box.createHorizontalStrut(15));
        buttonPanel.add(postBtn);

        // Event listeners
        postBtn.addActionListener(e -> {
            String title = titleField.getText().trim();
            String content = getContentText().trim();
            
            if (title.isEmpty() || content.isEmpty()) {
                showErrorMessage("Please fill in both title and content.");
                return;
            }
            
            MockDataService.addPost(title, content);
            showSuccessMessage("Post created successfully!");
            Navigation.switchFrame(this, new DashboardFrame());
        });

        backBtn.addActionListener(e -> Navigation.switchFrame(this, new DashboardFrame()));

        return buttonPanel;
    }

    private String getContentText() {
        String text = contentArea.getText().trim();
        return text.equals("Share your thoughts, ask questions, or start a discussion...") ? "" : text;
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
