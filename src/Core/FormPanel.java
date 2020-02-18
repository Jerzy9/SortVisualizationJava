package Core;

import SortComponents.ButtonC;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FormPanel extends JPanel {
    static final int width = 160;
    private int buttonWidth = 120;
    private int buttonHeight = 40;
    private int yButtonsStartPosition = 50, xButtonPosition = 25;
    private List<ButtonC> buttonList;
    private int numberOfButtons = 10;

    public FormPanel() {
        //set side-form-bar's width
        Dimension dim = getPreferredSize();
        dim.width = width;
        setPreferredSize(dim);

        buttonList = new ArrayList<>();
        createButtonList();
    }
    public void draw(Graphics g) {
        //background
        g.setColor(Color.red);
        g.fillRect(0,0,width, MainFrame.HEIGHT - ToolBar.height);
        for (int i = 0; i < buttonList.size(); i++) {
            buttonList.get(i).draw(g);
        }
    }
    public void createButtonList() {

        for (int i = 0; i < numberOfButtons; i++) {
            buttonList.add(new ButtonC("Bubble Sort", xButtonPosition, yButtonsStartPosition+(i*buttonHeight), buttonWidth, buttonHeight));
        }
    }
}