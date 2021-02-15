package sorting.algrorithms;

import components.Column;
import core.panels.SortPanel;
import sorting.Algorithm;

import java.util.List;

public class MargeSort extends Algorithm {
    public MargeSort(SortPanel sortPanel, List<Column> columns, int sleepTime, int moduloSleep) {
        super(sortPanel, columns, sleepTime, moduloSleep);
    }

    @Override
    public void sort() {
        mergeSort(0, columns.size()-1);

    }
    // l = left
    // r = right
    // m = medium
    public void mergeSort(int l, int r) {
        if( l < r) {
            int m = (l+r)/2;

            mergeSort(l, m);
            mergeSort(m+1, r);

            countingSort(l, r, m);
        }
    }
    private void countingSort(int l, int r, int m) {
        int lengthL = m - l + 1;
        int lengthR = r - m;

        int[] L = new int[lengthL];
        int[] R = new int[lengthR];

        for (int i = 0; i < lengthL; i++)
            L[i] = columns.get(l + i).getHeight();

        for (int i = 0; i < lengthR; i++)
            R[i] = columns.get(m+1+i).getHeight();

        int     i = 0,      // L index
                j = 0,      // R index
                k = l;      // arr index

        while(i < lengthL && j < lengthR) {

            if (L[i] <= R[j]) {
                columns.get(k).setHeight(L[i]);

                //non algorithm
                countConversions();                                         // conversions

                i++;
            } else {
                columns.get(k).setHeight(R[j]);

                //non algorithm
                countConversions();                                         // conversions

                j++;
            }
            k++;

            //non algorithm {
            soundHeight = columns.get(k-1).getHeight();                   // SOUND, soundEffect object gets know witch sound it should play
            changeColumnsColor(comparedColor, k-1);                   // change color
            tickSleep(getComparisons());                                    // sleep
            checkIfPauseAndReset();
            countComparisons();                                             // comparisons
            changeColumnsColor(normalColor, k-1);                     // change color back
            // }
        }

        while(i < lengthL) {
            columns.get(k).setHeight(L[i]);

            //non algorithm {
            soundHeight = columns.get(k).getHeight();                   // SOUND, soundEffect object gets know witch sound it should play
            changeColumnsColor(comparedColor, k);                           // change color
            tickSleep(getComparisons());
            checkIfPauseAndReset();
            countComparisons();
            countConversions();
            changeColumnsColor(normalColor, k);                             // change color back
            // }
            i++;
            k++;
        }

        while(j < lengthR) {
            columns.get(k).setHeight(R[j]);

            //non algorithm {
            soundHeight = columns.get(j).getHeight();                   // SOUND, soundEffect object gets know witch sound it should play
            changeColumnsColor(comparedColor, k);                           // change color
            tickSleep(getComparisons());
            checkIfPauseAndReset();
            countComparisons();
            countConversions();
            changeColumnsColor(normalColor, k);                             // change color back
            // }
            j++;
            k++;
        }
    }
}
