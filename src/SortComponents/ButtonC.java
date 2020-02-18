package SortComponents;

import java.awt.*;

public class ButtonC {
    private int width;
    private int height;
    private int x,y;
    private String name;

    public ButtonC(String name, int x, int y, int width, int height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void draw(Graphics g) {
        g.clearRect(x,y,width,height);
        //border
        g.setColor(Color.black);
        g.fillRect(x,y, width,height);
        g.clearRect(x+5,y+5,width-10,height-10);

        //in button
        g.setColor(Color.gray);
        g.fillRect(x+5,y+5,width-10,height-10);

        //string
        g.setColor(Color.black);
        g.drawString(name,x+40,y+20);
    }
}
