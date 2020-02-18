package Core;

import Sort.SortPanel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel implements Runnable {

    /////////////       Variables       /////////////

    public static Thread thread;
    private boolean running = false;
    private int ticks = 0;

    private SortPanel sortPanel;

    private int fieldMaxWidth, fieldMaxHeight;
    final private int marginWidth = 30, marginHeight = 60;
    private int columnsMaxWidth, columnsMaxHeight;    //width 1440 - 30(margin) = 1420 // height 860 - 60(margin) = 800
    private int size;
    private int brickWidth = 2, brickHeight;
    private Color backgroundColor = Color.darkGray;

    private int sleepTime = 5, numOfAlgorithm = 1;

    /////////////       MainPanel Constructor       /////////////

    public MainPanel(int fieldMaxWidth, int fieldMaxHeight) {
        this.fieldMaxWidth = fieldMaxWidth;
        this.fieldMaxHeight = fieldMaxHeight;

        columnsMaxWidth = fieldMaxWidth - marginWidth;
        columnsMaxHeight = fieldMaxHeight - marginHeight;

        sortPanel = new SortPanel(brickWidth, brickHeight, sleepTime, numOfAlgorithm, columnsMaxWidth, columnsMaxHeight);

        start();

    }

    /////////////       Thread Functions        /////////////

    public void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public void stop() {
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.fillInStackTrace();
            System.out.println("Thread.join error");
        }
    }
    public void tick() {
        if(ticks > 5) {
            ticks = 0;
        }
        ticks++;
    }
    @Override
    public void run() {
        while(running) {
            tick();
            repaint();
            try {
                thread.sleep(1000/30);
            } catch (InterruptedException ex) {
                ex.fillInStackTrace();
                System.out.println("Thread.sleep error");
            }
        }
    }

    /////////////       Graphics        /////////////

    public void paint(Graphics g) {
        //background
        g.setColor(backgroundColor);
        g.fillRect(0,0, fieldMaxWidth, fieldMaxHeight);
        //columns
        sortPanel.draw(g);
    }

    /////////////       Functions       /////////////
}