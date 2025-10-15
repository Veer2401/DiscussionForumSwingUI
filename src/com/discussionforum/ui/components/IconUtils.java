package com.discussionforum.ui.components;

import javax.swing.*;
import java.awt.*;

/**
 * Utility class for creating consistent icons and visual elements
 */
public class IconUtils {
    
    /**
     * Creates a discussion forum icon using ASCII art
     */
    public static JLabel createForumIcon(int size) {
        JLabel icon = new JLabel("<html><div style='text-align: center; font-family: monospace; font-size: " + size + "px;'>" +
            "+-----------------+<br/>" +
            "|  Discussion     |<br/>" +
            "|     Forum       |<br/>" +
            "+-----------------+</div></html>");
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        return icon;
    }
    
    /**
     * Creates a simple text-based forum icon
     */
    public static JLabel createSimpleForumIcon(int fontSize) {
        JLabel icon = new JLabel("FORUM");
        icon.setFont(new Font("Arial", Font.BOLD, fontSize));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        return icon;
    }
    
    /**
     * Creates a create post icon
     */
    public static JLabel createPostIcon(int fontSize) {
        JLabel icon = new JLabel("POST");
        icon.setFont(new Font("Arial", Font.BOLD, fontSize));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        return icon;
    }
    
    /**
     * Creates a register/signup icon
     */
    public static JLabel createRegisterIcon(int fontSize) {
        JLabel icon = new JLabel("USER");
        icon.setFont(new Font("Arial", Font.BOLD, fontSize));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        return icon;
    }
    
    /**
     * Creates a welcome icon
     */
    public static JLabel createWelcomeIcon(int fontSize) {
        JLabel icon = new JLabel("HI");
        icon.setFont(new Font("Arial", Font.BOLD, fontSize));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        return icon;
    }
    
    /**
     * Creates a back arrow icon
     */
    public static JLabel createBackIcon(int fontSize) {
        JLabel icon = new JLabel("<");
        icon.setFont(new Font("Arial", Font.BOLD, fontSize));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        return icon;
    }
    
    /**
     * Creates a modern styled icon with background circle
     */
    public static JLabel createStyledIcon(String text, int fontSize, Color backgroundColor, Color textColor) {
        JLabel icon = new JLabel(text);
        icon.setFont(new Font("Arial", Font.BOLD, fontSize));
        icon.setForeground(textColor);
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create a circular background
        icon.setOpaque(true);
        icon.setBackground(backgroundColor);
        icon.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        return icon;
    }
    
    /**
     * Creates a forum discussion icon with modern styling
     */
    public static JLabel createModernForumIcon() {
        return createStyledIcon("FORUM", 16, new Color(52, 152, 219), Color.WHITE);
    }
    
    /**
     * Creates a create post icon with modern styling
     */
    public static JLabel createModernPostIcon() {
        return createStyledIcon("POST", 14, new Color(46, 204, 113), Color.WHITE);
    }
    
    /**
     * Creates a register icon with modern styling
     */
    public static JLabel createModernRegisterIcon() {
        return createStyledIcon("USER", 14, new Color(155, 89, 182), Color.WHITE);
    }
}