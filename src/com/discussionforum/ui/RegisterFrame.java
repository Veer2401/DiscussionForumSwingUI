package com.discussionforum.ui;

import com.discussionforum.service.MockDataService;
import com.discussionforum.ui.components.ModernButton;
import com.discussionforum.ui.components.ModernTextField;
import com.discussionforum.ui.components.ModernPasswordField;
import com.discussionforum.ui.components.IconUtils;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private ModernTextField usernameField;
    private ModernPasswordField passwordField, confirmField;

    public RegisterFrame() {
        setTitle("Discussion Forum - Create Account");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Theme.BACKGROUND);
        setLayout(new BorderLayout());

        // Create main panel with modern design
        JPanel mainPanel = createMainPanel();
        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Theme.BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Header section
        JPanel headerPanel = createHeaderPanel();
        panel.add(headerPanel);
        panel.add(Box.createVerticalStrut(40));

        // Registration form
        JPanel formPanel = createFormPanel();
        panel.add(formPanel);

        return panel;
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(Theme.BACKGROUND);

        // App icon/logo placeholder
        JLabel iconLabel = IconUtils.createModernRegisterIcon();

        // Title
        JLabel titleLabel = new JLabel("Create Your Account");
        titleLabel.setFont(Theme.FONT_TITLE);
        titleLabel.setForeground(Theme.TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Join our community today");
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

        // Username field
        form.add(createFieldSection("Username", 
            usernameField = new ModernTextField("Choose a username")));
        form.add(Box.createVerticalStrut(20));

        // Password field
        form.add(createFieldSection("Password", 
            passwordField = new ModernPasswordField("Create a password")));
        form.add(Box.createVerticalStrut(20));

        // Confirm password field
        form.add(createFieldSection("Confirm Password", 
            confirmField = new ModernPasswordField("Confirm your password")));
        form.add(Box.createVerticalStrut(30));

        // Buttons
        JPanel buttonPanel = createButtonPanel();
        form.add(buttonPanel);

        formContainer.add(form, BorderLayout.CENTER);
        return formContainer;
    }

    private JPanel createFieldSection(String labelText, JComponent field) {
        JPanel section = new JPanel();
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setBackground(Theme.CARD_BACKGROUND);

        JLabel label = new JLabel(labelText);
        label.setFont(Theme.FONT_NORMAL);
        label.setForeground(Theme.TEXT_PRIMARY);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setMaximumSize(new Dimension(400, 50));
        field.setPreferredSize(new Dimension(400, 50));

        section.add(label);
        section.add(Box.createVerticalStrut(8));
        section.add(field);

        return section;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Theme.CARD_BACKGROUND);

        ModernButton registerBtn = ModernButton.createAccentButton("Create Account");
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerBtn.setMaximumSize(new Dimension(400, Theme.BUTTON_HEIGHT));
        registerBtn.setPreferredSize(new Dimension(400, Theme.BUTTON_HEIGHT));

        ModernButton backBtn = ModernButton.createSecondaryButton("Back to Login");
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.setMaximumSize(new Dimension(400, Theme.BUTTON_HEIGHT));
        backBtn.setPreferredSize(new Dimension(400, Theme.BUTTON_HEIGHT));

        buttonPanel.add(registerBtn);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(backBtn);

        // Event listeners
        registerBtn.addActionListener(e -> {
            String user = usernameField.getText().trim();
            String pass = new String(passwordField.getPassword()).trim();
            String confirm = new String(confirmField.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {
                showErrorMessage("Fill all fields");
                return;
            }
            if (!pass.equals(confirm)) {
                showErrorMessage("Passwords don't match");
                return;
            }
            if (MockDataService.register(user, pass)) {
                showSuccessMessage("Registration successful!");
                Navigation.switchFrame(this, new LoginFrame());
            } else {
                showErrorMessage("Username already exists.");
            }
        });

        backBtn.addActionListener(e -> Navigation.switchFrame(this, new LoginFrame()));

        return buttonPanel;
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
