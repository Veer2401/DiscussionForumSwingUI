package com.discussionforum.ui;

import com.discussionforum.service.MockDataService;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login - Discussion Forum");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        form.add(new JLabel("Username:"));
        usernameField = new JTextField();
        form.add(usernameField);

        form.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        form.add(passwordField);

        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        form.add(loginBtn);
        form.add(registerBtn);
        add(form, BorderLayout.CENTER);

        loginBtn.addActionListener(e -> {
            String user = usernameField.getText().trim();
            String pass = new String(passwordField.getPassword()).trim();
            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            } else if (MockDataService.login(user, pass)) {
                Navigation.switchFrame(this, new DashboardFrame());
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.");
            }
        });

        registerBtn.addActionListener(e -> Navigation.switchFrame(this, new RegisterFrame()));
    }
}
