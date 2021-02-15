package components;

import javax.swing.*;
import java.awt.*;

public class CheckBox extends JCheckBox {
    private Color bgColor;

    public CheckBox(String text, int fontSize, Color bgColor) {
        this.bgColor = bgColor;

        String font = "arial-BOLD-" + fontSize;
        setFont(Font.decode(font));
        setText(text);

        setHorizontalTextPosition(SwingConstants.LEFT);
        setOpaque(true);
        setVisible(true);
        setFocusable(true);
        setBorderPainted(false);
        setContentAreaFilled(false);
    }
}
