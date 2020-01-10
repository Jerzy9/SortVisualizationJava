package Core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JPanel{
    static final int height = 40;
    public ToolBar() {
        Dimension dim = getPreferredSize();
        dim.height = 40;
        setPreferredSize(dim);
    }
}