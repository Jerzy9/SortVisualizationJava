package core.panels;

import components.Column;
import components.SoundEffect;
import components.listeners.FloatListener;
import components.listeners.NumberListener;
import components.SimpleTimer;
import components.observer_pattern.Observable;
import components.observer_pattern.Observer;
import sorting.Algorithm;
import sorting.algrorithms.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SortPanel extends JPanel implements Runnable, Observable {

    private int width, height;
    private Color bgColor;
    private int numOfAlgorithm;
    private boolean running = false, sortRunning = false, sortReset = false;
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

    private boolean SoundOn = true;

    private List<Observer> observers;

    public SortPanel(int width, int height, Color bgColor) {
        this.width = width;
        this.height = height;
        this.bgColor = bgColor;

        columns = new ArrayList<>();
        observers = new ArrayList<>();

       setSortPanel();
       start();
       createColumns();
       timer = new SimpleTimer(this);
       sound = new SoundEffect(this);

       observers.add(sound);
       observers.add(timer);

    }
    // Reactions on pressed Button
    public void startButton() {
        if (currentAlgorithm == null) {
            setSpeedAlgoAndStats();                             // take variables like brickWidth, speed, algorithm and create currentAlgorithm and start it
        }
        if(currentAlgorithm != null) {
            if(!currentAlgorithm.isSorted()) {
                sortRunning = true;
                inform();
            }
        }
    }
    public void stopButton() {
        if(currentAlgorithm != null)  {
            sortRunning = false;
            inform();
        }
    }
    public void resetButton() {                                 // it creates new Columns and clears currentAlgorithm
        if(currentAlgorithm != null) {
            if (!sortRunning) {
                sortReset = true;                               // it join() sortThread
                inform();                                       // it informs all observers
                sortReset = false;
                currentAlgorithm = null;                        // it clears currentAlgorithm variable
                createColumns();                                // creates new Columns with different variables, like brickWidth, speed, algorithm
            }
        } else {
            createColumns();
        }
    }

    @Override
    public void run() {
        while (running) {
            if (sortRunning) {
                tick();
                repaint();
            }
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
        if(currentAlgorithm != null) {
            if(currentAlgorithm.isSorted()) {
                sendStatsToStatsPanel();                 // it corrects actual value of stats
                sortRunning = false;                     // sorting off
                inform();
            } else {
                 sendStatsToStatsPanel();               // sends stats while it's sorting
             }
            if(isSoundOn()) {
                sound.setHeight(currentAlgorithm.getCurrentColumnHeight());
            }
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
        repaint();                                                      // repaint, because it won't repaint if sortRunning = false
    }
    private void setSpeedAlgoAndStats() {
        int sleepTime = 1;                                              //sleep time
        int sleepModulo = 64;                                           // every X(sleepModulo) loop ticks go to sleep
        int soundTime = 10;                                             //time of single tune
        switch (sortSpeed) {
            case 1:
                sleepTime = 50;
                sleepModulo = 1;
                soundTime = 50;
                break;
            case 2:
                sleepTime = 10;
                sleepModulo = 1;
                soundTime = 25;
                break;
            case 3:
                sleepTime = 3;
                sleepModulo = 1;
                soundTime = 18;
                break;
            case 4:
                sleepTime = 1;
                sleepModulo = 1;
                soundTime = 14;
                break;
            case 5:
                sleepTime = 1;
                sleepModulo = 2;
                soundTime = 11;
                break;
            case 6:
                sleepTime = 1;
                sleepModulo = 8;
                soundTime = 8;
                break;
            case 7:
                sleepTime = 1;
                sleepModulo = 128;
                soundTime = 7;
                break;
        }
        switch (numOfAlgorithm) {
            case 0:                                                     // Bubble sort
                currentAlgorithm = new BubbleSort(this, columns, sleepTime, sleepModulo);
                break;
            case 1:                                                     // Selection sort
                currentAlgorithm = new SelectionSort(this, columns, sleepTime, sleepModulo);
                break;
            case 2:                                                     // Insertion sort
                currentAlgorithm = new InsertionSort(this, columns, sleepTime, sleepModulo);
                break;
            case 3:                                                     // Quick sort
                currentAlgorithm = new QuickSort(this,columns, sleepTime, sleepModulo);
                break;
            case 4:                                                     // Merge sort
                currentAlgorithm = new MargeSort(this,columns, sleepTime, sleepModulo);
                break;
            case 5:                                                     // Radix sort
                currentAlgorithm = new RadixSort(this,columns, sleepTime, sleepModulo);
                break;
            case 6:                                                     // Shell sort
                currentAlgorithm = new ShellSort(this,columns, sleepTime, sleepModulo);
                break;
            case 7:                                                     // Cocktail sort
                currentAlgorithm = new CocktailSort(this,columns, sleepTime, sleepModulo);

        }
        observers.add(currentAlgorithm);
        ////    StatsPanel stats:  ////                                 // it's here, not in sendStatsToStatsPanel, because it has to be send ONLY ONCE

        elementsListener.numberEmitted(columns.size());                 // elements

        float delay = (float) sleepTime/sleepModulo;                    // Delay
        delay = (float) Math.round(delay * 100) / 100;
        delayListener.floatEmitted(delay);

        // SOUND
        sound.setTime(soundTime);

    }
    private void setSound(int sleepTime, int sleepModulo) {

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
    public boolean isSoundOn() {
        return SoundOn;
    }
    public void setSoundOn(boolean soundOn) {
        SoundOn = soundOn;
    }
    public boolean isSortRunning() {
        return sortRunning;
    }
    public boolean isSortReset() {
        return sortReset;
    }
    @Override
    public void add(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void inform() {
        for (Observer ob : observers) {
            ob.updateRunning();
        }
    }
}
