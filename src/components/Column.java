package components;

import java.awt.*;

public class Column {
    private final int yStartPosition = 731;
    private final int xStartPosition = 30;
    private int width, height, x;
    private int index;
    private Color color = Color.pink;

    public Column(int index, int x,int width, int height) {
        this.index = index;
        this.x =x;
        this.width = width;
        this.height = height;
    }
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(xStartPosition+x, yStartPosition-height, width, height);
    }

    ////    Getters and Setters     ////
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setColor(Color color) {
        this.color = color;
    }
}