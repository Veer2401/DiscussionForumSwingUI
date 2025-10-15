package com.discussionforum.ui;

import com.discussionforum.service.MockDataService;
import com.discussionforum.ui.components.ModernButton;
import com.discussionforum.ui.components.ModernTextField;
import com.discussionforum.ui.components.ModernPasswordField;
import com.discussionforum.ui.components.GradientPanel;
import com.discussionforum.ui.components.IconUtils;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private ModernTextField usernameField;
    private ModernPasswordField passwordField;

    public LoginFrame() {
        setTitle("Discussion Forum - Login");
        setSize(500, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create gradient background
        GradientPanel backgroundPanel = new GradientPanel(Theme.BACKGROUND_GRADIENT_START, Theme.BACKGROUND_GRADIENT_END);
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        // Create main panel with modern design
        JPanel mainPanel = createMainPanel();
        backgroundPanel.add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Header section
        JPanel headerPanel = createHeaderPanel();
        panel.add(headerPanel);
        panel.add(Box.createVerticalStrut(40));

        // Login form
        JPanel formPanel = createFormPanel();
        panel.add(formPanel);

        return panel;
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setOpaque(false);

        // App icon/logo placeholder
        JLabel iconLabel = IconUtils.createModernForumIcon();

        // Title
        JLabel titleLabel = new JLabel("Discussion Forum");
        titleLabel.setFont(Theme.FONT_TITLE);
        titleLabel.setForeground(Theme.TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Connect, share, and inspire together");
        subtitleLabel.setFont(Theme.FONT_NORMAL);
        subtitleLabel.setForeground(Theme.TEXT_SECONDARY);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(iconLabel);
        header.add(Box.createVerticalStrut(12));
        header.add(titleLabel);
        header.add(Box.createVerticalStrut(6));
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

        // Username field
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(Theme.FONT_NORMAL);
        usernameLabel.setForeground(Theme.TEXT_PRIMARY);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameField = new ModernTextField("Enter your username");
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField.setMaximumSize(new Dimension(400, 50));
        usernameField.setPreferredSize(new Dimension(400, 50));

        form.add(usernameLabel);
        form.add(Box.createVerticalStrut(8));
        form.add(usernameField);
        form.add(Box.createVerticalStrut(20));

        // Password field
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(Theme.FONT_NORMAL);
        passwordLabel.setForeground(Theme.TEXT_PRIMARY);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        passwordField = new ModernPasswordField("Enter your password");
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setMaximumSize(new Dimension(400, 50));
        passwordField.setPreferredSize(new Dimension(400, 50));

        form.add(passwordLabel);
        form.add(Box.createVerticalStrut(8));
        form.add(passwordField);
        form.add(Box.createVerticalStrut(30));

        // Buttons
        JPanel buttonPanel = createButtonPanel();
        form.add(buttonPanel);

        formContainer.add(form, BorderLayout.CENTER);
        return formContainer;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Theme.CARD_BACKGROUND);

        ModernButton loginBtn = ModernButton.createPrimaryButton("Login");
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setMaximumSize(new Dimension(400, Theme.BUTTON_HEIGHT));
        loginBtn.setPreferredSize(new Dimension(400, Theme.BUTTON_HEIGHT));

        ModernButton registerBtn = ModernButton.createSecondaryButton("Create Account");
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerBtn.setMaximumSize(new Dimension(400, Theme.BUTTON_HEIGHT));
        registerBtn.setPreferredSize(new Dimension(400, Theme.BUTTON_HEIGHT));

        buttonPanel.add(loginBtn);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(registerBtn);

        // Event listeners
        loginBtn.addActionListener(e -> {
            String user = usernameField.getText().trim();
            String pass = new String(passwordField.getPassword()).trim();
            if (user.isEmpty() || pass.isEmpty()) {
                showErrorMessage("Please fill in all fields.");
            } else if (MockDataService.login(user, pass)) {
                Navigation.switchFrame(this, new DashboardFrame());
            } else {
                showErrorMessage("Invalid credentials.");
            }
        });

        registerBtn.addActionListener(e -> Navigation.switchFrame(this, new RegisterFrame()));

        return buttonPanel;
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
