package components.timers;

import components.observer_pattern.Observer;
import core.panels.SortPanel;

import java.util.Timer;
import java.util.TimerTask;

public class RealTimer implements MyTimer, Observer {

    private Timer timer;
    private TimerTask task;
    private double time;
    private boolean timerRunning = false;
    private SortPanel sortPanel;

    private int sleepTime, comparisons;

    public RealTimer(SortPanel sortPanel) {
        this.sortPanel = sortPanel;

        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                if(timerRunning) {
                    time++;
                } else {
                    // sleep, to
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        start();

    }

    @Override
    public void start() {
        timer.schedule(task, 1,1);

    }

    @Override
    public void reset() {
        time = 0;
        timerRunning = false;
    }

    ////    Getters and Setters     ////
    @Override
    public float getTime() {
        return ((float) time-(comparisons*sleepTime));
    }

    @Override
    public void updateRunning() {
        if (sortPanel.isSortReset())
            reset();
        else
            timerRunning = sortPanel.isSortRunning();
    }
    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }
    public void setComparisons(int comparisons) {
        this.comparisons = comparisons;
    }
}

