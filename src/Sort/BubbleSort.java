package Sort;

import Components.Column;

import java.util.List;

public class BubbleSort extends Algorithm {

    public BubbleSort(List<Column> columns,  int sleepTime, int moduloSleep) {
        super(columns, sleepTime, moduloSleep);
    }

    public void sort() {
        for (int i = 0; i < columns.size(); i++) {
            for (int j = 0; j < columns.size()-i-1; j++) {

                comparisons++;

                if (columns.get(j).getHeight() > columns.get(j+1).getHeight()) {
                    int temp = columns.get(j).getHeight();
                    columns.get(j).setHeight(columns.get(j+1).getHeight());
                    columns.get(j+1).setHeight(temp);

                    conversions++;
                }
                //sleep after every single tick
                if(j%moduloSleep==0) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //sleep until on = true, so until user will press START button again
                checkIfPauseAndReset();
            }
        }
    }
}
