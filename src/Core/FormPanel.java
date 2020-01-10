package Core;

import SortComponents.ButtonC;

import javax.swing.*;
import java.awt.*;

public class FormPanel extends JPanel {
    static final int width = 160;
    private ButtonC btn;

    public FormPanel() {
        //set side-form-bar's width
        Dimension dim = getPreferredSize();
        dim.width = width;
        setPreferredSize(dim);

        btn = new ButtonC("hehe", 10,150);
    }
}