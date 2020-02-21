package Sort;

import Components.Column;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SortPanel extends JPanel {

    private int width, height;
    private Color bgColor;
    private int numOfAlgorithm;
    private boolean on = false;
    private int size, speed;

    static List<Column> columns;

    public SortPanel(int width, int height, Color bgColor) {
        this.width = width;
        this.height = height;
        this.bgColor = bgColor;

        columns = new ArrayList<>();

       setSortPanel();
    }
    private void setSortPanel() {
        Dimension dim = new Dimension(width,height);
        setOpaque(true);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setMinimumSize(dim);
        setBackground(bgColor);
    }
    public void setNumOfAlgorithm(int num) {
        this.numOfAlgorithm = num;
    }

    public void setSize(int size) {
        this.size = size;
        System.out.println("Size: " + size);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        System.out.println("Speed: " + speed);
    }
    public void startOn() {
        on = true;
        System.out.println("START ON");
    }
    public void startOff() {
        on = false;
        System.out.println("START OFF");
    }
    public void reset() {
        System.out.println("RESET");
    }
}
