package com.discussionforum.ui;

import com.discussionforum.service.MockDataService;
import com.discussionforum.service.MockDataService.Post;
import com.discussionforum.ui.components.PostPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DashboardFrame extends JFrame {

    private JPanel postListPanel;

    public DashboardFrame() {
        setTitle("Dashboard - Discussion Forum");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Welcome, " + MockDataService.getCurrentUser().username);
        header.setFont(Theme.FONT_TITLE);
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(header, BorderLayout.NORTH);

        postListPanel = new JPanel();
        postListPanel.setLayout(new BoxLayout(postListPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(postListPanel);
        add(scrollPane, BorderLayout.CENTER);

        JButton createPostBtn = new JButton("Create New Post");
        add(createPostBtn, BorderLayout.SOUTH);

        createPostBtn.addActionListener(e -> Navigation.switchFrame(this, new CreatePostFrame()));

        refreshPosts();
    }

    private void refreshPosts() {
        postListPanel.removeAll();
        List<Post> posts = MockDataService.getPosts();
        for (Post p : posts) {
            postListPanel.add(new PostPanel(p, this));
        }
        postListPanel.revalidate();
        postListPanel.repaint();
    }

    public void openPost(int postId) {
        Navigation.switchFrame(this, new PostViewFrame(postId));
    }
}
