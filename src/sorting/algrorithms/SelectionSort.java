package sorting.algrorithms;

import components.Column;
import core.panels.SortPanel;
import sorting.Algorithm;

import java.util.List;

public class SelectionSort extends Algorithm {
    public SelectionSort(SortPanel sortPanel, List<Column> columns, int sleepTime, int moduloSleep) {
        super(sortPanel, columns, sleepTime, moduloSleep);
    }

    @Override
    public void sort() {
        for (int i = 0; i < columns.size(); i++) {
            int min = columns.get(i).getHeight();
            int index = i;



            for (int j = i + 1; j < columns.size(); j++) {

                changeColumnsColor(comparedColor, j);               // change color
                changeColumnsColor(comparedColor, i);                   // change color

                countComparisons();
                if(min > columns.get(j).getHeight()) {
                    min = columns.get(j).getHeight();
                    index = j;
                    //currentHeight = min; //SOUND
                }

                tickSleep(j);
                checkIfPauseAndReset();

                changeColumnsColor(normalColor, j);                 // change color back

            }

            if(columns.get(i).getHeight() != min) {
                int temp = columns.get(i).getHeight();
                columns.get(i).setHeight(min);
                columns.get(index).setHeight(temp);



                countConversions();
            }
            changeColumnsColor(normalColor, i);                     // change color back
        }
    }
}
