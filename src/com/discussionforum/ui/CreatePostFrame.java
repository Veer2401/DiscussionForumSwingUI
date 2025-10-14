package com.discussionforum.ui;

import com.discussionforum.service.MockDataService;

import javax.swing.*;
import java.awt.*;

public class CreatePostFrame extends JFrame {
    private JTextField titleField;
    private JTextArea contentArea;

    public CreatePostFrame() {
        setTitle("Create New Post");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new BorderLayout(10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        titleField = new JTextField();
        contentArea = new JTextArea();
        JButton postBtn = new JButton("Post");
        JButton backBtn = new JButton("Back");

        form.add(new JLabel("Title:"), BorderLayout.NORTH);
        form.add(titleField, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(new JLabel("Content:"), BorderLayout.NORTH);
        bottom.add(new JScrollPane(contentArea), BorderLayout.CENTER);

        JPanel actions = new JPanel();
        actions.add(postBtn);
        actions.add(backBtn);
        bottom.add(actions, BorderLayout.SOUTH);

        add(form, BorderLayout.NORTH);
        add(bottom, BorderLayout.CENTER);

        postBtn.addActionListener(e -> {
            String title = titleField.getText().trim();
            String content = contentArea.getText().trim();
            if (title.isEmpty() || content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }
            MockDataService.addPost(title, content);
            JOptionPane.showMessageDialog(this, "Post created!");
            Navigation.switchFrame(this, new DashboardFrame());
        });

        backBtn.addActionListener(e -> Navigation.switchFrame(this, new DashboardFrame()));
    }
}
