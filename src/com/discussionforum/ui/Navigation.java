package com.discussionforum.ui;

import javax.swing.*;

public class Navigation {
    public static void switchFrame(JFrame current, JFrame next) {
        current.dispose();
        next.setVisible(true);
    }
}
