package Components;

import java.awt.*;

public class Column {
    private final int yStartPosition = 800;
    private final int xStartPosition = 20;
    private int width, height;
    private int index;
    private Color color;

    public Column(int index, int width, int height, Color color) {
        this.index = index;
        this.width = width;
        this.height = height;
        this.color = color;

    }
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(xStartPosition, yStartPosition, width, height);
    }
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}