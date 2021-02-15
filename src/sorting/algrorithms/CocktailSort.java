package sorting.algrorithms;

import components.Column;
import core.panels.SortPanel;
import sorting.Algorithm;

import java.util.List;

public class CocktailSort extends Algorithm {
    public CocktailSort(SortPanel sortPanel, List<Column> columns, int sleepTime, int moduloSleep) {
        super(sortPanel, columns, sleepTime, moduloSleep);
    }

    @Override
    public void sort() {
        int     end = columns.size(),
                start = 0;

        //int countSwaps = 0;                                                       //help for understanding
        boolean swapped = true;

        while (swapped) {
            swapped = false;

            for (int i = start; i < end - 1 ; ++i) {
                changeColumnsColor(comparedColor, i);                               // change color
                if(columns.get(i).getHeight() > columns.get(i+1).getHeight()) {
                    swapAndCountConversions(i, i+1);
                    swapped = true;

                    //non algorithm
                    soundHeight = columns.get(i).getHeight();                   // SOUND, soundEffect object gets know witch sound it should play
                }
                //non algorithm
                countComparisons();
                tickSleep(getComparisons());
                checkIfPauseAndReset();
                changeColumnsColor(normalColor, i);                                 // change color back
                //System.out.println(i + " first  swaps: " + countSwaps);

            }

            if(!swapped) break;
            swapped = false;
            end--;

            for (int i = end - 1; i >= start; i--) {
                changeColumnsColor(comparedColor, i);                               // change color
                if(columns.get(i).getHeight() > columns.get(i+1).getHeight()) {
                    swapAndCountConversions(i, i+1);
                    swapped = true;

                    soundHeight = columns.get(i+1).getHeight();                   // SOUND, soundEffect object gets know witch sound it should play
                }
                //non algorithm
                countComparisons();
                tickSleep(getComparisons());
                checkIfPauseAndReset();
                changeColumnsColor(normalColor, i);                                 // change color back
                //System.out.println(i + " second  swaps: " + countSwaps);

            }
            start++;
            //countSwaps = 0;
            //System.out.println("start: " + start + " end: " + end);
        }
        //non algorithm
        countComparisons();
        tickSleep(getComparisons());
        checkIfPauseAndReset();
    }

}
