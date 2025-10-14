package com.discussionforum.service;

import java.util.*;

public class MockDataService {

    // ----- Inner models -----
    public static class User {
        public String username, password;
        public User(String u, String p) { username = u; password = p; }
    }

    public static class Post {
        public int id;
        public String title, content, author;
        public List<String> comments = new ArrayList<>();
        public Post(int id, String t, String c, String a) {
            this.id = id; title = t; content = c; author = a;
        }
    }

    // ----- Mock storage -----
    private static List<User> users = new ArrayList<>();
    private static List<Post> posts = new ArrayList<>();
    private static int nextId = 1;
    private static User currentUser;

    static {
        users.add(new User("admin", "123"));
        posts.add(new Post(nextId++, "Welcome!", "This is a mock discussion post.", "admin"));
    }

    // ----- Auth -----
    public static boolean login(String u, String p) {
        for (User usr : users) {
            if (usr.username.equals(u) && usr.password.equals(p)) {
                currentUser = usr;
                return true;
            }
        }
        return false;
    }

    public static boolean register(String u, String p) {
        for (User usr : users) {
            if (usr.username.equals(u)) return false;
        }
        users.add(new User(u, p));
        return true;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    // ----- Posts -----
    public static List<Post> getPosts() {
        return posts;
    }

    public static void addPost(String title, String content) {
        posts.add(new Post(nextId++, title, content, currentUser.username));
    }

    public static Post getPostById(int id) {
        for (Post p : posts) if (p.id == id) return p;
        return null;
    }

    public static void addComment(int postId, String comment) {
        Post p = getPostById(postId);
        if (p != null) p.comments.add(comment);
    }
}
