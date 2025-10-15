package com.discussionforum.ui;

import com.discussionforum.service.MockDataService;
import com.discussionforum.service.MockDataService.Post;
import com.discussionforum.ui.components.PostPanel;
import com.discussionforum.ui.components.ModernButton;
import com.discussionforum.ui.components.GradientPanel;
import com.discussionforum.ui.components.IconUtils;

import javax.swing.*;
import java.awt.*; 
import java.util.List;

public class DashboardFrame extends JFrame {

    private JPanel postListPanel;

    public DashboardFrame() {
        setTitle("Discussion Forum - Dashboard");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Theme.BACKGROUND);
        setLayout(new BorderLayout());

        // Create header
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        // Create main content area
        JPanel mainPanel = createMainPanel();
        add(mainPanel, BorderLayout.CENTER);

        // Create bottom action panel
        JPanel actionPanel = createActionPanel();
        add(actionPanel, BorderLayout.SOUTH);

        refreshPosts();
    }

    private JPanel createHeaderPanel() {
        // Create gradient background panel
        GradientPanel header = new GradientPanel(Theme.PRIMARY, Theme.PRIMARY_DARK);
        header.setLayout(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        // Welcome message with enhanced styling
        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        welcomePanel.setOpaque(false);

        JLabel welcomeIcon = IconUtils.createWelcomeIcon(24);

        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(Theme.FONT_TITLE);
        welcomeLabel.setForeground(Theme.TEXT_LIGHT);

        JLabel subtitleLabel = new JLabel("Share your thoughts");
        subtitleLabel.setFont(Theme.FONT_NORMAL);
        subtitleLabel.setForeground(new Color(255, 255, 255, 180));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.add(welcomeLabel);
        textPanel.add(Box.createVerticalStrut(2));
        textPanel.add(subtitleLabel);

        welcomePanel.add(welcomeIcon);
        welcomePanel.add(textPanel);

        // Logout button with modern styling
        ModernButton logoutBtn = new ModernButton("Sign Out", 
            Theme.ACCENT, Theme.ACCENT_DARK, Theme.TEXT_LIGHT);
        logoutBtn.setPreferredSize(new Dimension(110, 38));
        logoutBtn.addActionListener(e -> Navigation.switchFrame(this, new LoginFrame()));

        header.add(welcomePanel, BorderLayout.WEST);
        header.add(logoutBtn, BorderLayout.EAST);

        return header;
    }

    private JPanel createMainPanel() {
        // Create gradient background for main content
        GradientPanel mainPanel = new GradientPanel(Theme.BACKGROUND_GRADIENT_START, Theme.BACKGROUND_GRADIENT_END);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Posts section header with enhanced styling
        JPanel postsHeader = new JPanel(new BorderLayout());
        postsHeader.setOpaque(false);

        JLabel postsIcon = new JLabel("POSTS ");
        postsIcon.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel postsTitle = new JLabel("Recent Discussions");
        postsTitle.setFont(Theme.FONT_SUBTITLE);
        postsTitle.setForeground(Theme.TEXT_PRIMARY);

        JLabel postsSubtitle = new JLabel("Discover what the community is talking about");
        postsSubtitle.setFont(Theme.FONT_NORMAL);
        postsSubtitle.setForeground(Theme.TEXT_SECONDARY);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setOpaque(false);
        titlePanel.add(postsIcon);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.add(postsTitle);
        textPanel.add(Box.createVerticalStrut(3));
        textPanel.add(postsSubtitle);

        titlePanel.add(textPanel);
        postsHeader.add(titlePanel, BorderLayout.WEST);
        postsHeader.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));

        // Posts list with proper visibility
        postListPanel = new JPanel();
        postListPanel.setLayout(new BoxLayout(postListPanel, BoxLayout.Y_AXIS));
        postListPanel.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(postListPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        mainPanel.add(postsHeader, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createActionPanel() {
        JPanel actionContainer = new JPanel(new BorderLayout());
        actionContainer.setOpaque(false);
        
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        actionPanel.setOpaque(false);
        actionPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));

        ModernButton createPostBtn = new ModernButton("New Post", 
            Theme.ACCENT, Theme.ACCENT_DARK, Theme.TEXT_LIGHT);
        createPostBtn.setPreferredSize(new Dimension(220, Theme.BUTTON_HEIGHT));
        createPostBtn.addActionListener(e -> Navigation.switchFrame(this, new CreatePostFrame()));

        actionPanel.add(createPostBtn);
        actionContainer.add(actionPanel, BorderLayout.CENTER);

        return actionContainer;
    }

    private void refreshPosts() {
        postListPanel.removeAll();
        List<Post> posts = MockDataService.getPosts();
        
        if (posts.isEmpty()) {
            // Empty state
            JPanel emptyPanel = createEmptyState();
            postListPanel.add(emptyPanel);
        } else {
            for (Post p : posts) {
                PostPanel postPanel = new PostPanel(p, this);
                postListPanel.add(postPanel);
                postListPanel.add(Box.createVerticalStrut(15)); // Increased spacing between posts
            }
        }
        
        postListPanel.revalidate();
        postListPanel.repaint();
    }

    private JPanel createEmptyState() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setLayout(new BoxLayout(emptyPanel, BoxLayout.Y_AXIS));
        emptyPanel.setOpaque(false);
        emptyPanel.setBorder(BorderFactory.createEmptyBorder(60, 50, 60, 50));

        JLabel emptyIcon = new JLabel("NO POSTS");
        emptyIcon.setFont(new Font("Arial", Font.BOLD, 24));
        emptyIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emptyTitle = new JLabel("No discussions yet");
        emptyTitle.setFont(Theme.FONT_SUBTITLE);
        emptyTitle.setForeground(Theme.TEXT_PRIMARY);
        emptyTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emptySubtitle = new JLabel("Start the first conversation");
        emptySubtitle.setFont(Theme.FONT_NORMAL);
        emptySubtitle.setForeground(Theme.TEXT_SECONDARY);
        emptySubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        emptyPanel.add(emptyIcon);
        emptyPanel.add(Box.createVerticalStrut(20));
        emptyPanel.add(emptyTitle);
        emptyPanel.add(Box.createVerticalStrut(8));
        emptyPanel.add(emptySubtitle);

        return emptyPanel;
    }

    public void openPost(int postId) {
        Navigation.switchFrame(this, new PostViewFrame(postId));
    }
}
