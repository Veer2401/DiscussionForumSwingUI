package com.discussionforum.ui.components;

import javax.swing.*;
import java.awt.*;

/**
 * A panel that renders with a gradient background
 */
public class GradientPanel extends JPanel {
    private Color startColor;
    private Color endColor;
    private boolean isVertical;

    public GradientPanel(Color startColor, Color endColor) {
        this(startColor, endColor, true);
    }

    public GradientPanel(Color startColor, Color endColor, boolean isVertical) {
        this.startColor = startColor;
        this.endColor = endColor;
        this.isVertical = isVertical;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        int width = getWidth();
        int height = getHeight();

        GradientPaint gradient;
        if (isVertical) {
            gradient = new GradientPaint(0, 0, startColor, 0, height, endColor);
        } else {
            gradient = new GradientPaint(0, 0, startColor, width, 0, endColor);
        }

        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();
    }

    public void setGradientColors(Color startColor, Color endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
        repaint();
    }
}