package com.discussionforum.ui;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Theme {
    // Sexy Modern Color Palette with Gradients
    public static final Color PRIMARY = new Color(74, 144, 226);            // Vibrant Blue
    public static final Color PRIMARY_DARK = new Color(45, 105, 176);       // Deep Blue
    public static final Color PRIMARY_LIGHT = new Color(135, 185, 255);     // Light Blue
    
    public static final Color ACCENT = new Color(255, 107, 129);            // Coral Pink
    public static final Color ACCENT_DARK = new Color(229, 74, 100);        // Deep Coral
    public static final Color ACCENT_LIGHT = new Color(255, 158, 174);      // Light Coral
    
    public static final Color SECONDARY = new Color(156, 39, 176);          // Purple
    public static final Color SECONDARY_DARK = new Color(123, 31, 162);     // Deep Purple
    public static final Color SECONDARY_LIGHT = new Color(186, 104, 200);   // Light Purple
    
    // Gradient Backgrounds
    public static final Color BACKGROUND = new Color(248, 250, 252);        // Off White
    public static final Color BACKGROUND_GRADIENT_START = new Color(240, 245, 251);
    public static final Color BACKGROUND_GRADIENT_END = new Color(255, 255, 255);
    
    public static final Color CARD_BACKGROUND = Color.WHITE;
    public static final Color CARD_SHADOW = new Color(0, 0, 0, 15);         // Subtle shadow
    public static final Color HOVER_BACKGROUND = new Color(249, 250, 251);
    public static final Color HOVER_GRADIENT_START = new Color(245, 247, 250);
    public static final Color HOVER_GRADIENT_END = new Color(253, 254, 255);
    
    // Text Colors
    public static final Color TEXT_PRIMARY = new Color(31, 41, 55);         // Dark Slate
    public static final Color TEXT_SECONDARY = new Color(107, 114, 128);    // Medium Grey
    public static final Color TEXT_TERTIARY = new Color(156, 163, 175);     // Light Grey
    public static final Color TEXT_LIGHT = Color.WHITE;
    public static final Color TEXT_MUTED = new Color(75, 85, 99);           // Muted Grey
    
    // Status Colors with Gradients
    public static final Color SUCCESS = new Color(16, 185, 129);            // Emerald
    public static final Color SUCCESS_LIGHT = new Color(110, 231, 183);     // Light Emerald
    public static final Color ERROR = new Color(239, 68, 68);              // Red
    public static final Color ERROR_LIGHT = new Color(252, 165, 165);       // Light Red
    public static final Color WARNING = new Color(245, 158, 11);            // Amber
    public static final Color WARNING_LIGHT = new Color(251, 191, 36);      // Light Amber
    
    // Special Effect Colors
    public static final Color GLASS_OVERLAY = new Color(255, 255, 255, 80); // Glass effect
    public static final Color BORDER_LIGHT = new Color(229, 231, 235);      // Light border
    public static final Color BORDER_DEFAULT = new Color(209, 213, 219);    // Default border
    public static final Color BORDER_FOCUS = PRIMARY;                       // Focus border
    
    // Fonts
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 26);
    public static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font FONT_HEADING = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font FONT_NORMAL = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_SMALL = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FONT_TINY = new Font("Segoe UI", Font.PLAIN, 10);
    public static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 14);
    
    // Enhanced Borders and Spacing
    public static final Border CARD_BORDER = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(BORDER_LIGHT, 1),
        BorderFactory.createEmptyBorder(20, 20, 20, 20)
    );
    
    public static final Border CARD_BORDER_HOVER = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(PRIMARY_LIGHT, 2),
        BorderFactory.createEmptyBorder(19, 19, 19, 19)
    );
    
    public static final Border ELEVATED_BORDER = BorderFactory.createCompoundBorder(
        BorderFactory.createRaisedBevelBorder(),
        BorderFactory.createEmptyBorder(15, 15, 15, 15)
    );
    
    public static final int PADDING_TINY = 4;
    public static final int PADDING_SMALL = 8;
    public static final int PADDING_MEDIUM = 15;
    public static final int PADDING_LARGE = 25;
    public static final int PADDING_XLARGE = 35;
    
    // Button Styles
    public static final int BUTTON_HEIGHT = 45;
    public static final int BUTTON_ARC = 12;
    public static final int CARD_ARC = 16;
}
