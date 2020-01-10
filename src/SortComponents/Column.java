package SortComponents;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Column {
    private final int yStartPosition = 800;
    private int x;
    private int height;
    private int brickWidth;
    private int brickHeight;
    private int size;
    private int index;
    private Color color;

    public Column(int index, int x, int height, int brickWidth, int brickHeight, Color color) {
        this.index = index;
        this.x = x;
        this.height = height;
        this.brickWidth = brickWidth;
        this.brickHeight = brickHeight;
        this.size = height/brickHeight;
        this.color = color;

    }
    public void drawColumn(Graphics g) {
        g.setColor(color);
        for (int i = 0; i < height/brickHeight; i++) {
            g.fillRect(x, yStartPosition - (i * brickHeight), brickWidth, brickHeight);
        }
    }
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}