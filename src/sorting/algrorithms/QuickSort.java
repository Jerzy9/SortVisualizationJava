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

           countComparisons();                              //comparison

           int pi = partition(low, high);
           sort(low, pi - 1);
           sort(pi + 1, high);
       }
    }
    private int partition(int low, int high) {
        int pivot = columns.get(high).getHeight();

        int i = low-1;

        for (int j = low; j < high; j ++) {

            countComparisons();                                 // comparison
            changeColumnsColor(comparedColor, j);               // change color


            if(columns.get(j).getHeight() < pivot) {
                // swap
                i++;
                //changeColumnsColor(comparedColor, i);               // change color
                int temp = columns.get(j).getHeight();
                columns.get(j).setHeight(columns.get(i).getHeight());
                columns.get(i).setHeight(temp);

                countConversions();                              // conversions
            }
            tickSleep(getComparisons());                         // pause, it takes comparisons, because this method needs int to modulo
            checkIfPauseAndReset();                              // reset
            changeColumnsColor(normalColor, j);                  // change color back
            //changeColumnsColor(comparedColor, i);               // change color back
        }

        changeColumnsColor(comparedColor, i+1);               // change color
        changeColumnsColor(comparedColor, high);               // change color

        tickSleep(getComparisons());

        int temp = columns.get(i+1).getHeight();
        columns.get(i+1).setHeight(columns.get(high).getHeight());
        columns.get(high).setHeight(temp);

        changeColumnsColor(normalColor,i+1);               // change color back
        changeColumnsColor(normalColor, high);               // change color back

        countConversions();                              // conversions

        return(i + 1);
    }
}
