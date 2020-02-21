package Core;

import Components.TextLabel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class StatsPanel extends JPanel {
    private int width, height;
    private Color bgColor;

    public StatsPanel(int width, int height, Color bgColor) {
        this.width = width;
        this.height = height;
        this.bgColor = bgColor;

        TextLabel title = new TextLabel("Stats:", 24, bgColor);

        int fontSize = 16;
        JPanel layout = new JPanel();
        layout.setBackground(bgColor);
        layout.setLayout(new GridLayout(5,1,1,2));
        layout.setBorder(new LineBorder(Color.black,4));

        Dimension dim = new Dimension(150,100);
        layout.setPreferredSize(new Dimension(dim));
        layout.setMinimumSize(new Dimension(dim));
        layout.setMaximumSize(new Dimension(dim));

        TextLabel elements = new TextLabel("elements:  ", fontSize, bgColor);
        TextLabel comparision   = new TextLabel("comparison:  ", fontSize, bgColor);
        TextLabel conversions = new TextLabel("conversions:  ", fontSize, bgColor);
        TextLabel time = new TextLabel("time:  ", fontSize, bgColor);
        TextLabel delay = new TextLabel("delay:  ", fontSize, bgColor);

        layout.add(elements);
        layout.add(comparision);
        layout.add(conversions);
        layout.add(time);
        layout.add(delay);

        add(title, BorderLayout.NORTH);
        add(layout, BorderLayout.CENTER);
        setStatsPanel();
    }
    private void setStatsPanel() {
        Dimension dim = new Dimension(width,height);
        setOpaque(true);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setMinimumSize(dim);
        setBackground(bgColor);
    }
}
