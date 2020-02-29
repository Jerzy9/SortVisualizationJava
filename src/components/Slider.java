package components;

import javax.swing.*;
import java.awt.*;

public class Slider extends JSlider {
    private Color bgColor;
    private int max, min, value;

    public Slider(Color bgColor, int max, int min, int value) {
        this.bgColor = bgColor;
        this.max = max;
        this.min = min;
        this.value = value;

        setOrientation(HORIZONTAL);
        setMinimum(min);
        setMaximum(max);
        setValue(value);
        setBackground(bgColor);
        setPaintLabels(true);
        setPaintTicks(true);
        setSnapToTicks(true);
        setMajorTickSpacing(1);
        setMinorTickSpacing(1);
    }
}
