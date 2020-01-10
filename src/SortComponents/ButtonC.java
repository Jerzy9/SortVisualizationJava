package SortComponents;

import java.awt.*;

public class ButtonC {
    private int width = 120;
    private int height = 40;
    private int x,y;
    private String name;

    public ButtonC(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics g) {
        //border
        g.setColor(Color.black);
        g.fillRect(x,y, width,height);
        g.clearRect(x+5,y+5,width-10,height-10);

        //in button
        g.setColor(Color.gray);
        g.fillRect(x+5,y+5,width-10,height-10);
    }
}
