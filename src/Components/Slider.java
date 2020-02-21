package Components;

import javax.swing.*;
import java.awt.*;

public class Slider extends JSlider {
    private Color bgColor;

    public Slider(Color bgColor) {
        this.bgColor = bgColor;
        setOrientation(HORIZONTAL);
        setMinimum(1);
        setMaximum(5);
        setValue(3);
        setBackground(bgColor);
        setPaintLabels(true);
        setPaintTicks(true);
        setSnapToTicks(true);
        setMajorTickSpacing(1);
        setMinorTickSpacing(1);
    }
}
