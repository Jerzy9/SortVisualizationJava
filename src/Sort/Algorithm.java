package Sort;

import Components.Column;

import java.util.List;

public abstract class Algorithm implements Runnable{
    private Thread thread;
    int sleepTime, moduloSleep;
    List<Column> columns;
    int comparisons = 0,conversions = 0;

    Algorithm(List<Column> columns, int sleepTime, int moduloSleep) {
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
        SortPanel.setResetSortThread(false);
        SortPanel.setRunningSortThread(false);
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
        while (!SortPanel.isRunningSortThread()) {
            if (SortPanel.isResetSortThread()) {
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
}
