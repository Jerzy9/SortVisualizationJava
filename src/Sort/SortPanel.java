package Sort;

import Components.Column;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SortPanel extends JPanel implements Runnable{

    private int width, height;
    private Color bgColor;
    private int numOfAlgorithm;
    private boolean on = false, running = false;
    private int size, speed;

    static List<Column> columns;

    private Thread thread;

    public SortPanel(int width, int height, Color bgColor) {
        this.width = width;
        this.height = height;
        this.bgColor = bgColor;

        columns = new ArrayList<>();

       setSortPanel();
      // start();
    }
    private void setSortPanel() {
        Dimension dim = new Dimension(width,height);
        setOpaque(true);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setMinimumSize(dim);
        setBackground(bgColor);
    }
    public void setNumOfAlgorithm(int num) {
        this.numOfAlgorithm = num;
    }
    public void setSize(int size) {
        this.size = size;
        System.out.println("Size: " + size);
    }
    public void setSpeed(int speed) {
        this.speed = speed;
        System.out.println("Speed: " + speed);
    }
    public void startOn() {
        on = true;
        System.out.println("START ON");
    }
    public void startOff() {
        on = false;
        System.out.println("START OFF");
    }
    public void reset() {
        System.out.println("RESET");
    }

    @Override
    public void run() {
        while (on) {
            tick();
           // repaint();
            try {
                thread.sleep(1000/20);
            } catch (InterruptedException ex) {
                ex.fillInStackTrace();
                System.out.println("Thread.sleep error");
            }
        }
    }
   /* public void paint(Graphics g) {
        super.paintComponents(g);
    }*/
    public void start() {
        thread = new Thread(this);
        thread.start();
        running = true;

    }
    public void stop() {
        try {
            thread.join();
        } catch (Exception ex) {
            System.out.println("Thread join error");
        }
    }
    public void tick() {

    }
}
