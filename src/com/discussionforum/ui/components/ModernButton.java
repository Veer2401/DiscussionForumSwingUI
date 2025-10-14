package com.discussionforum.ui.components;

import com.discussionforum.ui.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModernButton extends JButton {
    private Color backgroundColor;
    private Color hoverColor;
    private Color textColor;

    public ModernButton(String text) {
        this(text, Theme.PRIMARY, Theme.PRIMARY_DARK, Theme.TEXT_LIGHT);
    }

    public ModernButton(String text, Color backgroundColor, Color hoverColor, Color textColor) {
        super(text);
        this.backgroundColor = backgroundColor;
        this.hoverColor = hoverColor;
        this.textColor = textColor;
        
        setupButton();
    }

    private void setupButton() {
        setFont(Theme.FONT_BUTTON);
        setForeground(textColor);
        setBackground(backgroundColor);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Set preferred size for consistent button height
        setPreferredSize(new Dimension(getPreferredSize().width, Theme.BUTTON_HEIGHT));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(backgroundColor);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Paint rounded background
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), Theme.BUTTON_ARC, Theme.BUTTON_ARC);
        
        g2d.dispose();
        super.paintComponent(g);
    }

    // Factory methods for different button types
    public static ModernButton createPrimaryButton(String text) {
        return new ModernButton(text, Theme.PRIMARY, Theme.PRIMARY_DARK, Theme.TEXT_LIGHT);
    }

    public static ModernButton createAccentButton(String text) {
        return new ModernButton(text, Theme.ACCENT, Theme.ACCENT_DARK, Theme.TEXT_PRIMARY);
    }

    public static ModernButton createSecondaryButton(String text) {
        return new ModernButton(text, Theme.CARD_BACKGROUND, Theme.HOVER_BACKGROUND, Theme.TEXT_PRIMARY);
    }
}