package sorting;

import components.Column;
import components.listeners.NumberListener;
import components.observer_pattern.Observer;
import core.panels.SortPanel;


import java.awt.*;
import java.util.List;

public abstract class Algorithm implements Runnable, Observer {
    private Thread thread;
    private int sleepTime, moduloSleep;
    protected List<Column> columns;

    private int comparisons, conversions = 0;

    private boolean runningSort = true, resetSort = false;
    private boolean sorted = false;

    protected Color sortedColor = new Color(12,123,23),
                    comparedColor = new Color(242,80,100),
                    normalColor = Color.pink;
    private NumberListener columnHeightListener;
    protected int soundHeight = 0;
    private int countSleeps = 0;


    private SortPanel sortPanel;

    public Algorithm(SortPanel sortpanel, List<Column> columns, int sleepTime, int moduloSleep) {
        this.sortPanel = sortpanel;
        this.columns = columns;
        this.sleepTime = sleepTime;
        this.moduloSleep = moduloSleep;

        start();
    }
    public abstract void sort();
    private void start() {
        thread = new Thread(this);
        thread.start();
    }
    public void stop() {
        sorted = true;
        // sets false running variables
        resetSort = false;
        runningSort = false;
        // kill thread
        try {
            this.thread.join();
        } catch (Exception ex) {
            System.out.println("Thread join error");
        }
    }

    @Override
    public void run() {
        if(runningSort) {
            sort();
            stop();                         // stop when sort is over
        }
    }

    protected void checkIfPauseAndReset() {
        while (!runningSort) {              // user can reset program only if running == false
            if (resetSort) {
                stop();
            }
            try {
                Thread.sleep(1000/120);  // it checks four times per second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    protected void tickSleep(int j) {
        if(j%moduloSleep==0) {
            countSleeps++;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void changeColumnsColor(Color color, int index) {
        int size = 0;                       // it's responsible for how many columns are colored form both sides,
        if(sleepTime == 1) {                // depends how fast sorting goes
            size = 3;
        } else if (sleepTime > 1 && sleepTime < 5) {
            size = 2;
        } else {
            size = 1;
        }
        for (int i = 0; i <= size; i++) {
            if (index-i > 0) columns.get(index-i).setColor(color);
            if (index+i < columns.size()) columns.get(index+i).setColor(color);
        }
    }
    @Override
    public void updateRunning() {
        this.runningSort = sortPanel.isSortRunning();
        this.resetSort = sortPanel.isSortReset();

    }
    protected void swapAndCountConversions(int i, int j) {
        int temp = columns.get(i).getHeight();
        columns.get(i).setHeight(columns.get(j).getHeight());
        columns.get(j).setHeight(temp);

        countConversions();
    }

    protected void countComparisons() {
        ++comparisons;
    }
    protected void countConversions() {
        ++conversions;
    }

    ////    Getters and Setters     ////
    public void setResetSort(boolean resetSort) {
        this.resetSort = resetSort;
    }
    public int getComparisons() {
        return comparisons;
    }
    public int getConversions() {
        return conversions;
    }
    public boolean isSorted() {
        return sorted;
    }
    public int getCurrentColumnHeight() {
        return soundHeight;
    }
    public int getCountSleeps() {
        return countSleeps;
    }
    public void setCountSleeps(int number) {
        this.countSleeps = number;
    }
}
