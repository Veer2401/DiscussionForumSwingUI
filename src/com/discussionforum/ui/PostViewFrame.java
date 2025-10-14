package com.discussionforum.ui;

import com.discussionforum.service.MockDataService;
import com.discussionforum.service.MockDataService.Post;

import javax.swing.*;
import java.awt.*;

public class PostViewFrame extends JFrame {
    private JTextArea commentArea;
    private JPanel commentsPanel;
    private int postId;

    public PostViewFrame(int postId) {
        this.postId = postId;
        Post post = MockDataService.getPostById(postId);

        setTitle("View Post - " + post.title);
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel(post.title);
        title.setFont(Theme.FONT_TITLE);
        
        JLabel author = new JLabel("By " + post.author);
        author.setFont(Theme.FONT_NORMAL);
        
        JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        headerPanel.add(title);
        headerPanel.add(author);

        JTextArea content = new JTextArea(post.content);
        content.setEditable(false);
        content.setLineWrap(true);
        content.setWrapStyleWord(true);

        commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        refreshComments();

        JScrollPane commentScroll = new JScrollPane(commentsPanel);
        commentScroll.setBorder(BorderFactory.createTitledBorder("Comments"));

        JPanel addCommentPanel = new JPanel(new BorderLayout());
        commentArea = new JTextArea(3, 20);
        JButton addCommentBtn = new JButton("Add Comment");
        JButton backBtn = new JButton("Back");
        JPanel btnPanel = new JPanel();
        btnPanel.add(addCommentBtn);
        btnPanel.add(backBtn);

        addCommentPanel.add(new JScrollPane(commentArea), BorderLayout.CENTER);
        addCommentPanel.add(btnPanel, BorderLayout.SOUTH);

        add(headerPanel, BorderLayout.NORTH);
        add(new JScrollPane(content), BorderLayout.CENTER);
        add(commentScroll, BorderLayout.EAST);
        add(addCommentPanel, BorderLayout.SOUTH);

        addCommentBtn.addActionListener(e -> {
            String comment = commentArea.getText().trim();
            if (!comment.isEmpty()) {
                MockDataService.addComment(postId, comment);
                commentArea.setText("");
                refreshComments();
            }
        });

        backBtn.addActionListener(e -> Navigation.switchFrame(this, new DashboardFrame()));
    }

    private void refreshComments() {
        commentsPanel.removeAll();
        MockDataService.Post post = MockDataService.getPostById(postId);
        for (String c : post.comments) {
            commentsPanel.add(new JLabel("- " + c));
        }
        commentsPanel.revalidate();
        commentsPanel.repaint();
    }
}
