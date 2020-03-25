package components;

import components.observer_pattern.Observer;
import core.panels.SortPanel;

import java.util.Timer;
import java.util.TimerTask;

public class SimpleTimer implements Observer {


    private Timer timer;
    private TimerTask task;
    private double time;
    private boolean timerRunning = false;
    private SortPanel sortPanel;

    public SimpleTimer(SortPanel sortPanel) {
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
    private void start() {
        timer.schedule(task, 10,10);
    }
    public void reset() {
        time = 0;
        timerRunning = false;
    }
    ////    Getters and Setters     ////
    public float getTime() {
        return ((float) time/100);
    }

    @Override
    public void updateRunning() {
        if (sortPanel.isSortReset()) {
            reset();
        }
        else
            timerRunning = sortPanel.isSortRunning();
    }
}
