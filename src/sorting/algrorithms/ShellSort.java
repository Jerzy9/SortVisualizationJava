package sorting.algrorithms;

import components.Column;
import core.panels.SortPanel;
import sorting.Algorithm;

import java.util.List;

public class ShellSort extends Algorithm {

    public ShellSort(SortPanel sortPanel, List<Column> columns, int sleepTime, int moduloSleep) {
        super(sortPanel, columns, sleepTime, moduloSleep);
    }

    @Override
    public void sort() {
        int distance;
        int iteration = 0;
        do {
            iteration++;
            distance = columns.size() / (int) Math.pow(2, iteration);          // creating new distance
            insertionSort(distance);

            //non algorithm
            countComparisons();

        } while (distance >= 1);
    }

    private void insertionSort(int d) {

        for (int i = d; i < columns.size(); i++) {

            int key = columns.get(i).getHeight();
            int j = i;

            //non algorithm
            countComparisons();
            changeColumnsColor(comparedColor, i);
            countComparisons();     // double comparison, because if while(false), it doesn't switch columns, but still compare

            while(j >= d && columns.get(j - d).getHeight() > key ) {
                columns.get(j).setHeight(columns.get(j - d).getHeight());

                //non algorithm
                countComparisons();
                countConversions();
                changeColumnsColor(comparedColor, j);
                tickSleep(j);
                checkIfPauseAndReset();
                changeColumnsColor(normalColor, j);

                j -= d;
            }
            columns.get(j).setHeight(key);

            //non algorithm
            countConversions();
            changeColumnsColor(normalColor, i);
        }
    }
}
