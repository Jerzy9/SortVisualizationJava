package components;

import java.util.Timer;
import java.util.TimerTask;

public class SimpleTimer {

    private Timer timer;
    private TimerTask task;
    private double time;
    private boolean timerRunning = false;

    public SimpleTimer() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                if(timerRunning) {
                    time++;
                } else {
                    // sleep, to
                    try {
                        Thread.sleep(10);
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
    public void returnTicking() {       // think of a better name
        timerRunning = true;
    }
    public float getTime() {
        return ((float) time/100);
    }
    public void stop() {
        timerRunning = false;
    }
    public void reset() {
        time = 0;
    }
}
