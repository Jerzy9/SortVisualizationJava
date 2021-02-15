package sorting.algrorithms;

import components.Column;
import core.panels.SortPanel;
import sorting.Algorithm;

import java.util.List;

public class QuickSort extends Algorithm {
    public QuickSort(SortPanel sortPanel, List<Column> columns, int sleepTime, int moduloSleep) {
        super(sortPanel, columns, sleepTime, moduloSleep);
    }


    @Override
    public void sort() {
        sort(0,columns.size()-1);
    }
    private void sort(int low, int high) {
       if(low < high) {
           //non algorithm
           countComparisons();

           int pi = partition(low, high);
           sort(low, pi - 1);
           sort(pi + 1, high);
       }
    }
    private int partition(int low, int high) {
        int pivot = columns.get(high).getHeight();

        int i = low-1;
        int index = 0;                                                  // only to change color back
        for (int j = low; j < high; j ++) {
            //non algorithm
            soundHeight = columns.get(j).getHeight();                   // SOUND, soundEffect object gets know witch sound it should play
            countComparisons();
            changeColumnsColor(comparedColor, j);                       // change color


            if(columns.get(j).getHeight() < pivot) {
                i++;
                swapAndCountConversions(i, j);

                //non algorithm
                changeColumnsColor(comparedColor, i);
                index = i;

            }
            //non algorithm
            tickSleep(getComparisons());                                // pause, it takes comparisons, because this method needs int to modulo
            checkIfPauseAndReset();
            changeColumnsColor(normalColor, j);                         // change color back
            changeColumnsColor(normalColor, index);                     // change color back
        }
        swapAndCountConversions(i+1, high);

        //non algorithm
        changeColumnsColor(comparedColor, i+1);                   // change color
        changeColumnsColor(comparedColor, high);                        // change color
        tickSleep(getComparisons());
        checkIfPauseAndReset();
        changeColumnsColor(normalColor,i+1);                      // change color back
        changeColumnsColor(normalColor, high);                          // change color back

        return(i + 1);
    }
}
