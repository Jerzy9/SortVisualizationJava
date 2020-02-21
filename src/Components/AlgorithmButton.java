package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AlgorithmButton extends JButton implements MouseListener {
    private int width = 200, height = 50;
    private String text = "empty";
    private int index;
    private Dimension dim;
    private boolean hover = false, clicked = false;
    private Color color = new Color(242,108,74),
            hoverColor = new Color (192,66,33),
            clickedColor = new Color (49,183,83),
            clickedHoverColor = new Color (30,120,50);

    public AlgorithmButton(int index) {
        this.index = index;
        addMouseListener(this);
        setAlgorithmButton();
    }
    private void setAlgorithmButton() {
        Dimension dim = new Dimension(width,height);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setMinimumSize(dim);
        setOpaque(true);
        setVisible(true);
        setFocusable(true);
        setBorderPainted(false);
        setContentAreaFilled(false);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //// Background ////
        if(clicked) {
            if (hover) {
                g.setColor(clickedHoverColor);
                g.fillRect(0,0,width,height);
            } else
            g.setColor(clickedColor);
            g.fillRect(0,0,width,height);
        } else {
            if (hover) {
                g.setColor(hoverColor);
                g.fillRect(0, 0, width, height);
            } else {
                g.setColor(color);
                g.fillRect(0, 0, width, height);
            }
        }

        ///// Border ////
        int borderWidth = 5;
        g.setColor(Color.black);
        g.fillRect(0,0, width, borderWidth);
        g.fillRect(0,height - borderWidth, width, height);
        g.fillRect(0, 0, borderWidth, height);
        g.fillRect(width - borderWidth, 0, width, height);

        //// Text ////
        g.setColor(Color.black);
        g.setFont(Font.decode("arial-BOLD-24"));
        FontMetrics fontMetrics = g.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);
        g.drawString(text, width/2 - textWidth/2,height/2+7);
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        clicked = !clicked;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        hover = true;
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        hover = false;
    }
    public boolean getClicked() {
        return clicked;
    }
    public void setText(String text) {
        this.text = text;
    }
    public int getIndex() {
        return index;
    }
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
