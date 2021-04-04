package Gui;

import javax.swing.*;
import java.awt.*;

public class Defaults {
    private static final Font textFieldFont = new Font(UIManager.getDefaults().getFont("TextField.font").getFontName(), Font.PLAIN, 21);
    private static final Font comboBoxFont = new Font(UIManager.getDefaults().getFont("TextField.font").getFontName(), Font.PLAIN, 17);
    private static final int screenWidth = 600;
    private static final int screenHeight = 375;

    public static Font getTextFieldFont() {
        return textFieldFont;
    }

    public static Font getComboBoxFont() {
        return comboBoxFont;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static Dimension getScreenDimension() {
        return new Dimension(screenWidth, screenHeight);
    }
}
