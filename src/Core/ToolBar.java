package Core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JPanel {
    static int height;
    public ToolBar(int height) {
        Dimension dim = getPreferredSize();
        dim.height = height;
        setPreferredSize(dim);
    }
    public void draw(Graphics g) {
        //background
        g.setColor(Color.green);
        g.fillRect(0, MainFrame.HEIGHT-height, MainFrame.WIDTH,height);
    }
}