package sorting;

import components.Column;

import java.util.List;

public abstract class Algorithm implements Runnable{
    private Thread thread;
    int sleepTime, moduloSleep;
    List<Column> columns;
    int comparisons = 0,conversions = 0;
    boolean runningSort = true, resetSort = false;

    public Algorithm(List<Column> columns, int sleepTime, int moduloSleep) {
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
        // sets false running variables
        resetSort = false;
        runningSort = false;
        // clear stats variables
        comparisons = 0;
        conversions = 0;
        // kill thread
        try {
            this.thread.join();
        } catch (Exception ex) {
            System.out.println("Thread join error");
        }
    }

    @Override
    public void run() {
        sort();
        stop();
    }

    public void checkIfPauseAndReset() {
        // user can reset program only if running == false
        while (!runningSort) {
            if (resetSort) {
                stop();
            }
            // it checks four times per second
            try {
                Thread.sleep(1000/4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public int getComparisons() {
        return comparisons;
    }
    public void setComparisons(int comparisons) {
        this.comparisons = comparisons;
    }
    public int getConversions() {
        return conversions;
    }
    public void setConversions(int conversions) {
        this.conversions = conversions;
    }
    public boolean isRunningSort() {
        return runningSort;
    }
    public void setRunningSort(boolean runningSort) {
        this.runningSort = runningSort;
    }
    public boolean isResetSort() {
        return resetSort;
    }
    public void setResetSort(boolean resetSort) {
        this.resetSort = resetSort;
    }

}
