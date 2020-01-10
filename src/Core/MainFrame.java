package Core;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame implements Runnable{
    private Thread thread;
    private boolean running;
    private int ticks;
    private MainPanel mainPanel;
    private ToolBar toolBar;
    private FormPanel formPanel;
    static final int WIDTH = 1600, HEIGHT = 900;

    public MainFrame() {
        super("Sort Visualization");
        setLayout(new BorderLayout());

        mainPanel = new MainPanel();
        toolBar = new ToolBar();
        formPanel = new FormPanel();

        add(mainPanel, BorderLayout.CENTER);
        add(toolBar, BorderLayout.SOUTH);
        add(formPanel, BorderLayout.WEST);

        //window location's just for current device
        setLocation(150, 80);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
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

    }

}