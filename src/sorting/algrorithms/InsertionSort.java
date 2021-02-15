package sorting.algrorithms;

import components.Column;
import core.panels.SortPanel;
import sorting.Algorithm;

import java.util.List;

public class InsertionSort extends Algorithm {

    public InsertionSort(SortPanel sortPanel, List<Column> columns, int sleepTime, int moduloSleep) {
        super(sortPanel, columns, sleepTime, moduloSleep);
    }

    @Override
    public void sort() {

        for (int i = 1; i < columns.size(); i++) {

            int key = columns.get(i).getHeight();
            int j = i-1;

            //non algorithm
            countComparisons();
            changeColumnsColor(comparedColor, i);
            countComparisons();     // double comparison, because if while(false), it doesn't switch columns, but still compare

            while( j >= 0 && columns.get(j).getHeight() > key) {
                columns.get(j+1).setHeight(columns.get(j).getHeight());

                //non algorithm
                soundHeight = columns.get(j).getHeight();                   // SOUND, soundEffect object gets know witch sound it should play
                countComparisons();
                countConversions();
                changeColumnsColor(comparedColor, j);
                tickSleep(j);
                checkIfPauseAndReset();
                changeColumnsColor(normalColor, j);


                j--;
            }
            columns.get(j+1).setHeight(key);

            //non algorithm
            countConversions();
            changeColumnsColor(normalColor, i);
        }
    }
}
