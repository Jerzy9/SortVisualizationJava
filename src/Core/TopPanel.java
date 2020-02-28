package Core;

import Components.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopPanel extends JPanel implements ActionListener, ChangeListener {
    private int width, height;
    private Color bgColor;
    private OnOffButton startButton, stopButton, resetButton;
    private Slider sizeSlider, speedSlider;
    private NumberListener  buttonsListener,
                            sizeListener,
                            speedListener;

    public TopPanel(int width, int height, Color bgColor) {
        this.width = width;
        this.height = height;
        this.bgColor = bgColor;

        setLayout(new FlowLayout(FlowLayout.RIGHT,20,10));

        // label size,speed
        int fontSize = 16;
        TextLabel sizeText = new TextLabel("size: ", fontSize, bgColor);
        TextLabel speedText = new TextLabel("speed: ", fontSize, bgColor);

        // creating sliders and adding Change listener
        speedSlider = new Slider(bgColor,7,1,6);
        sizeSlider = new Slider(bgColor,7,1,2);
        sizeSlider.addChangeListener(this);
        speedSlider.addChangeListener(this);

        // creating buttons and adding Action listener
        startButton = new OnOffButton("START");
        stopButton = new OnOffButton("STOP");
        resetButton = new OnOffButton("RESET");
        startButton.addActionListener(this);
        stopButton.addActionListener(this);
        resetButton.addActionListener(this);

        // adding components
        add(sizeText);
        add(sizeSlider);
        add(speedText);
        add(speedSlider);
        add(resetButton);
        add(stopButton);
        add(startButton);

        setTopPanel();
    }
    private void setTopPanel() {
        Dimension dim = new Dimension(width,height);
        setOpaque(true);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setMinimumSize(dim);
        setBackground(bgColor);
        setBorder(new LineBorder(Color.black,4));
    }
    public void setButtonListener(NumberListener listener) {
        this.buttonsListener = listener;
    }
    public void setColumnsWidthListener(NumberListener listener) {
        this.sizeListener = listener;
    }
    public void setSpeedListener(NumberListener listener) {
        this.speedListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        OnOffButton clicked = (OnOffButton) e.getSource();
        if(buttonsListener != null) {
            if(clicked == startButton) {
                buttonsListener.numberEmitted(1);
            } else if(clicked == stopButton) {
                buttonsListener.numberEmitted(2);
            } else if(clicked == resetButton) {
                buttonsListener.numberEmitted(3);
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Slider slider = (Slider)e.getSource();
        if (slider == sizeSlider) {
            if (sizeListener != null) {
                sizeListener.numberEmitted(sizeSlider.getValue());
            }
        } else if(slider == speedSlider) {
            if (sizeListener != null) {
                speedListener.numberEmitted(speedSlider.getValue());
            }
        }
    }
}
