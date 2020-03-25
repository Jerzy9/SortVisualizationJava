package components.buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OnOffButton extends JButton implements MouseListener {
    private int width = 180, height = 70;
    private String text;
    private boolean hover = false, pressed = false;
    private Color color = new Color(242,108,74),
            hoverColor = new Color (192,66,33),
            pressedColor = new Color (49,183,83);

    public OnOffButton(String text) {
        this.text = text;

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
        if(pressed) {
            g.setColor(pressedColor);
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
        int border = 5;
        g.setColor(Color.black);
        g.fillRect(0,0, width, border);
        g.fillRect(0,height - border, width, border);
        g.fillRect(0, 0, border, height);
        g.fillRect(width - border, 0, border, height);

        //// Text ////
        g.setColor(Color.black);
        g.setFont(Font.decode("arial-BOLD-26"));
        FontMetrics fontMetrics = g.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);
        g.drawString(text, width/2 - textWidth/2,height/2+7);
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent){

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        pressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        pressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        hover = true;
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        hover = false;
    }
}
