package core.panels;

import components.Column;
import components.SoundEffect;
import components.listeners.FloatListener;
import components.listeners.NumberListener;
import components.SimpleTimer;
import sorting.Algorithm;
import sorting.algrorithms.BubbleSort;
import sorting.algrorithms.InsertionSort;
import sorting.algrorithms.SelectionSort;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SortPanel extends JPanel implements Runnable{

    private int width, height;
    private Color bgColor;
    private int numOfAlgorithm;
    private boolean running = false, ticking;
    private int columnsWidth = 2, sortSpeed;

    private static List<Column> columns;

    private Thread thread;
    private Algorithm currentAlgorithm;

    private NumberListener  elementsListener,
                            comparisonsListener,
                            conversionsListener;
    private FloatListener   delayListener,
                            timeListener;

    private SimpleTimer timer;
    private SoundEffect sound;

    public SortPanel(int width, int height, Color bgColor) {
        this.width = width;
        this.height = height;
        this.bgColor = bgColor;

        columns = new ArrayList<>();

       setSortPanel();
       start();
       createColumns();
       timer = new SimpleTimer();
       //sound = new SoundEffect();

    }
    // Reactions on pressed Button
    public void startButton() {
        if (currentAlgorithm == null){
            setSpeedAlgoAndStats();                             // take variables like brickWidth, speed, algorithm and create currentAlgorithm and start it
        }
        if(currentAlgorithm != null) {
            if(!currentAlgorithm.isSorted()) {
                currentAlgorithm.setRunningSort(true);          // start sorting
                timer.returnTicking();                          // it's running
                ticking = true;
            }
        }
    }
    public void stopButton() {
        if(currentAlgorithm != null)  {
            timer.pause();                               // timer Thread sleep
            currentAlgorithm.setRunningSort(false);     // pause sorting
            //sound.setRunning(false);                    // kill sound effect's thread
        }
    }
    public void resetButton() {                                 // it creates new Columns and clears currentAlgorithm
        if(currentAlgorithm != null) {
            if (!currentAlgorithm.isRunningSort()) {
                currentAlgorithm.setResetSort(true);            // it join() sortThread
                currentAlgorithm = null;                        // it clears currentAlgorithm variable
                createColumns();                                // creates new Columns with different variables, like brickWidth, speed, algorithm
                timer.reset();                                  // it clears time variable
            }
        } else {
            createColumns();
        }
    }

    @Override
    public void run() {
        while (running) {
            tick();
            repaint();
            try {
                Thread.sleep(1000/120);
            } catch (InterruptedException ex) {
                ex.fillInStackTrace();
                System.out.println("Thread.sleep error");
            }
        }
    }
    public void paint(Graphics g) {
        super.paintComponents(g);
        // background
        g.setColor(bgColor);
        g.fillRect(0,0,width,height);


        int tempWidth = 1300;   // real width = 1300 px
        int tempHeight = 761;    // real height = 761 px

        // border
        int border = 4;
        g.setColor(Color.black);
        //g.fillRect(0,0,width,border);                               //top
        g.fillRect(0,tempHeight-border,width,border);           //bottom
        g.fillRect(0, 0, border, height);                       //left
        g.fillRect(width - border, 0, border, tempHeight);      //right

        // second border
        int margin = 22;
        g.fillRect(margin+border,margin,tempWidth-(2*margin)-(2*border),border);                                      //top
        g.fillRect(margin+border,tempHeight-margin-(2*border),tempWidth-(2*margin)-(2*border), border);               //bottom
        g.fillRect(margin+border, margin, border, tempHeight - (2*margin)- border);                                //left
        g.fillRect(tempWidth - margin - (2*border), margin, border, tempHeight - (2*margin) - border);        //right

        for (Column column:columns) {
            column.draw(g);
        }
    }
    private void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.fillInStackTrace();
            System.out.println("Thread join error");
        }
    }
    private void tick() {
        if(ticking && currentAlgorithm != null) {

            if(currentAlgorithm.isSorted()) {
                sendStatsToStatsPanel();                 // it corrects actual value of stats
                ticking = false;                         // ticking off
            } else {
                 sendStatsToStatsPanel();               // sends stats while sorting
             }
            //sound.setHeight(currentAlgorithm.getCurrentColumnHeight());
            //sound.generateTone(currentAlgorithm.getCurrentColumnHeight()*2,10);
        }
    }
    private void createColumns() {
        // picking size
        int brickLength = 0;
        switch (columnsWidth) {
            case 1: brickLength = 1;
                break;
            case 2: brickLength = 2;
                break;
            case 3: brickLength = 4;
                break;
            case 4: brickLength = 8;
                break;
            case 5: brickLength = 10;
                break;
            case 6: brickLength = 20;
                break;
            case 7: brickLength = 40;
                break;
        }
        // creating random height of each column
        Random r = new Random();
        columns.clear();
        for (int i = 0; i < 1240/brickLength; i ++) {
            // always higher than 0 px
            int randomHeight = r.nextInt(700/brickLength)+1;
            columns.add(new Column(i,i*brickLength, brickLength,randomHeight*brickLength));
        }
    }
    private void setSpeedAlgoAndStats() {
        int sleepTime = 1;
        int moduloSleep = 64;                                           //think more of better name
        switch (sortSpeed) {                                            // set Speed
            case 1:
                sleepTime = 40;
                moduloSleep = 1;
                break;
            case 2:
                sleepTime = 15;
                moduloSleep = 1;
                break;
            case 3:
                sleepTime = 10;
                moduloSleep = 1;
                break;
            case 4:
                sleepTime = 3;
                moduloSleep = 1;
                break;
            case 5:
                sleepTime = 1;
                moduloSleep = 1;
                break;
            case 6:
                sleepTime = 1;
                moduloSleep = 32;
                break;
            case 7:
                sleepTime = 1;
                moduloSleep = 128;
                break;
        }
        switch (numOfAlgorithm) {
            case 0:                                                     // Bubble sort
                currentAlgorithm = new BubbleSort(columns, sleepTime, moduloSleep);
                break;
            case 1:                                                     // Selection sort
                currentAlgorithm = new SelectionSort(columns, sleepTime, moduloSleep);
                break;
            case 2:                                                     // Insertion sort
                currentAlgorithm = new InsertionSort(columns, sleepTime, moduloSleep);
        }

        ////    StatsPanel stats:  ////                                 // it's here, not in sendStatsToStatsPanel, because it has to be send ONLY ONCE

        elementsListener.numberEmitted(columns.size());                 // elements

        float delay = (float) sleepTime/moduloSleep;                    // Delay
        delay = (float) Math.round(delay * 100) / 100;
        delayListener.floatEmitted(delay);

        // SOUND
        //sound.start();

    }
    private void sendStatsToStatsPanel () {
        comparisonsListener.numberEmitted(currentAlgorithm.getComparisons());
        conversionsListener.numberEmitted(currentAlgorithm.getConversions());
        timeListener.floatEmitted(timer.getTime());
    }

    ////    Getters and Setters     ////
    public void setElementsListener(NumberListener listener) {
        this.elementsListener = listener;
    }
    public void setComparisonsListener(NumberListener listener) {
        this.comparisonsListener = listener;
    }
    public void setConversionsListener(NumberListener listener) {
        this.conversionsListener = listener;
    }
    public void setTimeListener(FloatListener listener) {
        this.timeListener = listener;
    }
    public void setDelayListener(FloatListener listener) {
        this.delayListener = listener;
    }
    private void setSortPanel() {
        Dimension dim = new Dimension(width,height);
        setOpaque(true);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setMinimumSize(dim);
        setBackground(bgColor);
        setBorder(new LineBorder(Color.black, 4));
    }
    public void setNumOfAlgorithm(int num) {
        this.numOfAlgorithm = num;
    }
    public void setColumnsWidth(int width) {
        this.columnsWidth = width;
        resetButton();      // if you're moving a size slider, it reset columns array
    }
    public int getColumnsWidth() {
        return columnsWidth;
    }
    public void setSortSpeed(int sortSpeed) {
        this.sortSpeed = sortSpeed;
    }
    public int getSortSpeed() {
        return sortSpeed;
    }
}
