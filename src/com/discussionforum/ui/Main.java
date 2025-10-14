package com.discussionforum.ui;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Set system look and feel for better native appearance
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                
                // Enable anti-aliasing for better text rendering
                System.setProperty("awt.useSystemAAFontSettings", "on");
                System.setProperty("swing.aatext", "true");
                
                // Custom UI Manager settings for better appearance
                setupUIDefaults();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            new LoginFrame().setVisible(true);
        });
    }
    
    private static void setupUIDefaults() {
        // Improve button appearance
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(8, 16, 8, 16));
        UIManager.put("Button.font", Theme.FONT_BUTTON);
        
        // Improve text field appearance
        UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        // Set default fonts
        UIManager.put("Label.font", Theme.FONT_NORMAL);
        UIManager.put("Panel.font", Theme.FONT_NORMAL);
        
        // Improve scroll pane appearance
        UIManager.put("ScrollPane.border", BorderFactory.createEmptyBorder());
        
        // Set tooltip appearance
        UIManager.put("ToolTip.background", Theme.TEXT_PRIMARY);
        UIManager.put("ToolTip.foreground", Theme.TEXT_LIGHT);
        UIManager.put("ToolTip.font", Theme.FONT_SMALL);
    }
}
