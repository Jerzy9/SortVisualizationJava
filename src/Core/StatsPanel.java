package Core;

import Components.TextLabel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class StatsPanel extends JPanel {
    private int width, height;
    private Color bgColor;
    private int elements = 0,comparisons = 0, conversions = 0;
    private float time = 0, delay = 0;

    private TextLabel   elementsLabel, comparisionLabel, conversionsLabel, timeLabel, delayLabel,
                        elementsNumberLabel, comparisionNumberLabel, conversionsNumberLabel, timeNumberLabel, delayNumberLabel;

    StatsPanel(int width, int height, Color bgColor) {
        this.width = width;
        this.height = height;
        this.bgColor = bgColor;

        // TITLE
        TextLabel title = new TextLabel("  Stats: ", 24, bgColor);
        Dimension titleDim = new Dimension(110,30);
        title.setPreferredSize(titleDim);
        title.setMinimumSize(titleDim);
        title.setMaximumSize(titleDim);

        // STATS TABLE
        int fontSize = 16;
        // text Layout
        JPanel textLayout = new JPanel();
        textLayout.setBackground(bgColor);
        textLayout.setLayout(new GridLayout(5,1,5,4));
        textLayout.setBorder(new MatteBorder(4,4,4,0,Color.black));

        // number Layout
        JPanel numberLayout = new JPanel();
        numberLayout.setBackground(bgColor);
        numberLayout.setLayout(new GridLayout(5,1,5,4));
        numberLayout.setBorder(new MatteBorder(4,2,4,4,Color.black));

        // first dimension for textLayout
        Dimension textLayoutDim = new Dimension(120,140);
        textLayout.setPreferredSize(textLayoutDim);
        textLayout.setMinimumSize(textLayoutDim);
        textLayout.setMaximumSize(textLayoutDim);

        // second dimension for numberLayout
        Dimension numberLayoutDim = new Dimension(90,140);
        numberLayout.setPreferredSize(numberLayoutDim);
        numberLayout.setMinimumSize(numberLayoutDim);
        numberLayout.setMaximumSize(numberLayoutDim);

        //create texts
        elementsLabel = new TextLabel("elements:  ", fontSize, bgColor);
        comparisionLabel   = new TextLabel("comparison:  ", fontSize, bgColor);
        conversionsLabel = new TextLabel("conversions:  ", fontSize, bgColor);
        timeLabel = new TextLabel("time:  ", fontSize, bgColor);
        delayLabel = new TextLabel("delay:  ", fontSize, bgColor);

        //create numbers, but same class as above(TextLabel)
        elementsNumberLabel = new TextLabel("" + elements, fontSize, bgColor);
        comparisionNumberLabel   = new TextLabel("" + comparisons, fontSize, bgColor);
        conversionsNumberLabel = new TextLabel("" + conversions, fontSize, bgColor);
        timeNumberLabel = new TextLabel("" + time, fontSize, bgColor);
        delayNumberLabel = new TextLabel("" + delay, fontSize, bgColor);

        // add all text to layout
        textLayout.add(elementsLabel);
        textLayout.add(comparisionLabel);
        textLayout.add(conversionsLabel);
        textLayout.add(timeLabel);
        textLayout.add(delayLabel);

        // add all numbers to layout
        numberLayout.add(elementsNumberLabel);
        numberLayout.add(comparisionNumberLabel);
        numberLayout.add(conversionsNumberLabel);
        numberLayout.add(timeNumberLabel);
        numberLayout.add(delayNumberLabel);

        // container for texts and numbers
        Container container = new Container();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setLayout(new FlowLayout(FlowLayout.RIGHT,0,10));

        // add components
        add(title, BorderLayout.NORTH);
        container.add(textLayout);
        container.add(numberLayout);
        add(container);
        setStatsPanel();
    }
    private void setStatsPanel() {
        Dimension dim = new Dimension(width,height);
        setOpaque(true);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setMinimumSize(dim);
        setBackground(bgColor);
        setBorder(new MatteBorder(0,4,4,4,Color.black));
    }
    public void setElements(int elements) {
        this.elements = elements;
        elementsNumberLabel.setText("" + elements);
    }
    public void setComparisons(int comparisons) {
        this.comparisons = comparisons;
        comparisionNumberLabel.setText("" + comparisons);
    }
    public void setConversions(int conversions) {
        this.conversions = conversions;
        conversionsNumberLabel.setText("" + conversions);
    }
    public void setTime(float time) {
        this.time = time;
        timeNumberLabel.setText("" + time);
    }
    public void setDelay(float delay) {
        this.delay = delay;
        delayNumberLabel.setText("" + delay + " ms");
    }
}
