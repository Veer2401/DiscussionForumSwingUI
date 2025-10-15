package com.discussionforum.ui.components;

import com.discussionforum.ui.Theme;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ModernTextField extends JTextField {
    private String placeholder;
    private boolean showingPlaceholder = false;

    public ModernTextField() {
        this("");
    }

    public ModernTextField(String placeholder) {
        super();
        this.placeholder = placeholder;
        setupTextField();
    }

    private void setupTextField() {
        setFont(Theme.FONT_NORMAL);
        setBackground(Theme.CARD_BACKGROUND);
        setForeground(Theme.TEXT_PRIMARY);
        setBorder(new RoundedBorder());
        setPreferredSize(new Dimension(getPreferredSize().width, 50));
        
        if (!placeholder.isEmpty()) {
            showPlaceholder();
        }

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showingPlaceholder) {
                    hidePlaceholder();
                }
                setBorder(new RoundedBorder(Theme.PRIMARY));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty() && !placeholder.isEmpty()) {
                    showPlaceholder();
                }
                setBorder(new RoundedBorder());
            }
        });
    }

    private void showPlaceholder() {
        showingPlaceholder = true;
        setText(placeholder);
        setForeground(Theme.TEXT_SECONDARY);
    }

    private void hidePlaceholder() {
        showingPlaceholder = false;
        setText("");
        setForeground(Theme.TEXT_PRIMARY);
    }

    @Override
    public String getText() {
        return showingPlaceholder ? "" : super.getText();
    }

    private static class RoundedBorder extends AbstractBorder {
        private Color borderColor;

        public RoundedBorder() {
            this(new Color(220, 220, 220));
        }

        public RoundedBorder(Color borderColor) {
            this.borderColor = borderColor;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(borderColor);
            g2d.drawRoundRect(x, y, width - 1, height - 1, 15, 15);
            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(12, 16, 12, 16);
        }
    }
}