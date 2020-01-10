package SortComponents;

import Sort.SortPanel;

import java.awt.*;

public class Brick {
    private int x, y, width, height;
    private Color color;

    public Brick(int x, int y,int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void drawBrick(Graphics g) {
        //brick
        g.setColor(color);
        g.fillRect(x,y,width,height);

        //border lines
       /* g.setColor(Color.black);
        g.drawLine(x,y,x+width,y);
        g.drawLine(x,y,x, y+height);*/
    }
}