package com.discussionforum.ui.components;

import com.discussionforum.ui.Theme;

import javax.swing.*;
import java.awt.*;

/**
 * Utility class for creating modern UI components with consistent styling
 */
public class UIUtils {
    
    /**
     * Creates a modern-styled label with specified text and style
     */
    public static JLabel createStyledLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }
    
    /**
     * Creates a title label with primary styling
     */
    public static JLabel createTitleLabel(String text) {
        return createStyledLabel(text, Theme.FONT_TITLE, Theme.TEXT_PRIMARY);
    }
    
    /**
     * Creates a subtitle label with secondary styling
     */
    public static JLabel createSubtitleLabel(String text) {
        return createStyledLabel(text, Theme.FONT_NORMAL, Theme.TEXT_SECONDARY);
    }
    
    /**
     * Creates a modern panel with card styling
     */
    public static JPanel createCardPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Theme.CARD_BACKGROUND);
        panel.setBorder(Theme.CARD_BORDER);
        return panel;
    }
    
    /**
     * Creates spacing between components
     */
    public static Component createVerticalStrut(int height) {
        return Box.createVerticalStrut(height);
    }
    
    public static Component createHorizontalStrut(int width) {
        return Box.createHorizontalStrut(width);
    }
    
    /**
     * Applies hover effect to a component
     */
    public static void addHoverEffect(JComponent component, Color hoverColor) {
        Color originalColor = component.getBackground();
        component.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                component.setBackground(hoverColor);
                component.repaint();
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                component.setBackground(originalColor);
                component.repaint();
            }
        });
    }
    
    /**
     * Sets consistent margins for a component
     */
    public static void setMargin(JComponent component, int top, int left, int bottom, int right) {
        component.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
    }
    
    /**
     * Creates a modern scroll pane with improved styling
     */
    public static JScrollPane createModernScrollPane(Component component) {
        JScrollPane scrollPane = new JScrollPane(component);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(Theme.BACKGROUND);
        return scrollPane;
    }
    
    /**
     * Shows a modern error dialog
     */
    public static void showErrorDialog(Component parent, String message, String title) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Shows a modern success dialog
     */
    public static void showSuccessDialog(Component parent, String message, String title) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Shows a modern confirmation dialog
     */
    public static int showConfirmDialog(Component parent, String message, String title) {
        return JOptionPane.showConfirmDialog(parent, message, title, JOptionPane.YES_NO_OPTION);
    }
}