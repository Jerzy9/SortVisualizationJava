package sorting.algrorithms;

import components.Column;
import sorting.Algorithm;

import java.util.List;

public class InsertionSort extends Algorithm {

    public InsertionSort(List<Column> columns, int sleepTime, int moduloSleep) {
        super(columns, sleepTime, moduloSleep);
    }

    @Override
    public void sort() {

        for (int i = 1; i < columns.size(); i++) {

            int key = columns.get(i).getHeight();
            int j = i-1;

            countComparisons();

            changeColumnsColor(comparedColor, i);

            countComparisons();     // double comparison, because if while(false), it doesn't switch columns, but still compare
            while( j >= 0 && columns.get(j).getHeight() > key) {
                columns.get(j+1).setHeight(columns.get(j).getHeight());


                countComparisons();
                countConversions();

                changeColumnsColor(comparedColor, j);

                tickSleep(j);
                checkIfPauseAndReset();

                changeColumnsColor(normalColor, j);

                //sound
                //currentHeight = columns.get(j).getHeight(); // SOUND
                j--;
            }
            columns.get(j+1).setHeight(key);

            countConversions();

            changeColumnsColor(normalColor, i);
        }
    }
}
