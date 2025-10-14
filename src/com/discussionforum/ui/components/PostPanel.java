package com.discussionforum.ui.components;

import com.discussionforum.service.MockDataService.Post;
import com.discussionforum.ui.DashboardFrame;
import com.discussionforum.ui.Theme;

import javax.swing.*;
import java.awt.*;

public class PostPanel extends JPanel {
    public PostPanel(Post post, DashboardFrame parent) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setBackground(Color.WHITE);

        JLabel title = new JLabel(post.title);
        title.setFont(Theme.FONT_TITLE);
        JLabel author = new JLabel("by " + post.author);
        author.setFont(Theme.FONT_NORMAL);

        JButton viewBtn = new JButton("View");
        viewBtn.addActionListener(e -> parent.openPost(post.id));

        add(title, BorderLayout.NORTH);
        add(author, BorderLayout.CENTER);
        add(viewBtn, BorderLayout.EAST);
    }
}
