package core.panels;

import components.buttons.AlgorithmButton;
import components.listeners.NumberListener;
import components.TextLabel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class AlgoPanel extends JPanel implements ActionListener {
    private int width, height;
    private Color bgColor;

    private List<AlgorithmButton> buttons;

    private NumberListener algorithmListener;

    public AlgoPanel(int width, int height, Color bgColor) {
        this.width = width;
        this.height = height;
        this.bgColor = bgColor;

        JPanel layout = new JPanel();
        layout.setBackground(bgColor);
        layout.setLayout(new GridLayout(8,1,10,20));

        TextLabel title = new TextLabel("Algorithm:",24,bgColor);
        title.setPreferredSize(new Dimension(150,40));

        // Creating buttons and setting index of every button
        buttons = new ArrayList<>();
        for (int i = 0; i < 8; i++)
            buttons.add(new AlgorithmButton(i));

        buttons.get(0).setText("Bubble Sort");
        buttons.get(1).setText("Selection Sort");
        buttons.get(2).setText("Insertion Sort");
        buttons.get(3).setText("Quick Sort");
        buttons.get(4).setText("Marge Sort");
        buttons.get(5).setText("Bubble Sort");
        buttons.get(6).setText("Bubble Sort");
        buttons.get(7).setText("Bubble Sort");

        // Adding action listener and to layout
        for (AlgorithmButton button : buttons) {
            button.addActionListener(this);
            layout.add(button);
        }

        add(title, BorderLayout.NORTH);
        add(layout,BorderLayout.CENTER);
        setAlgorithmPanel();
    }
    private void setAlgorithmPanel() {
        Dimension dim = new Dimension(width,height);
        setOpaque(true);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setMinimumSize(dim);
        setBackground(bgColor);
        setBorder(new LineBorder(Color.black, 4));
    }
    public void setAlgorithmListener(NumberListener listener) {
        this.algorithmListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {                        //send number of clicked button to sort panel, ONLY when it hasn't been clicked before
        AlgorithmButton clicked = (AlgorithmButton) e.getSource();
        if(algorithmListener !=null) {
            for (AlgorithmButton button : buttons) {
                if (clicked == button && !button.getClicked())
                    algorithmListener.numberEmitted(button.getIndex());
                if (clicked != button) //button.mouseClicked();
                    System.out.println("kek");
            }
        }
        // try to unclicked rest of buttons
        // it has to be repaid!

        /*for (AlgorithmButton button : buttons) {
            if (clicked != button)  {
                if(button.getClicked()) {
                    button.setClicked(false);
                    //button.paintComponent(g);
                }
            }
        }*/
    }
}