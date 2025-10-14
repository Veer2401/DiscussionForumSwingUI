package com.discussionforum.ui;

import com.discussionforum.service.MockDataService;
import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField, confirmField;

    public RegisterFrame() {
        setTitle("Register - Discussion Forum");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        form.add(new JLabel("Username:"));
        usernameField = new JTextField();
        form.add(usernameField);

        form.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        form.add(passwordField);

        form.add(new JLabel("Confirm Password:"));
        confirmField = new JPasswordField();
        form.add(confirmField);

        JButton registerBtn = new JButton("Register");
        JButton backBtn = new JButton("Back");
        form.add(registerBtn);
        form.add(backBtn);
        add(form, BorderLayout.CENTER);

        registerBtn.addActionListener(e -> {
            String user = usernameField.getText().trim();
            String pass = new String(passwordField.getPassword()).trim();
            String confirm = new String(confirmField.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }
            if (!pass.equals(confirm)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match.");
                return;
            }
            if (MockDataService.register(user, pass)) {
                JOptionPane.showMessageDialog(this, "Registration successful! Please login.");
                Navigation.switchFrame(this, new LoginFrame());
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists.");
            }
        });

        backBtn.addActionListener(e -> Navigation.switchFrame(this, new LoginFrame()));
    }
}
