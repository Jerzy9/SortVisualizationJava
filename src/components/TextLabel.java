package components;

import javax.swing.*;
import java.awt.*;

public class TextLabel extends JLabel {
    private int fontSize;
    private String text;
    private Color bgColor;

    public TextLabel(String text, int fontSize, Color bgColor) {
        this.fontSize = fontSize;
        this.text = text;
        this.bgColor = bgColor;

        String font = "arial-BOLD-" + fontSize;
        setFont(Font.decode(font));
        setText(text);
        setBackground(bgColor);
        setOpaque(true);
    }
}
