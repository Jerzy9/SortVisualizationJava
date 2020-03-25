package sorting.algrorithms;

import components.Column;
import core.panels.SortPanel;
import sorting.Algorithm;

import java.util.List;

public class RadixSort extends Algorithm {
    public RadixSort(SortPanel sortPanel, List<Column> columns, int sleepTime, int moduloSleep) {
        super(sortPanel, columns, sleepTime, moduloSleep);
    }

    @Override
    public void sort() {
        int m = getMax(columns.size());

        for (int exp = 1; m/exp > 0; exp *= 10) {
            countSort(columns.size(), exp);
            countComparisons();                                         // comparisons
        }
    }
    private int getMax(int n) {
        int mx = 0;
        for (int i = 0; i < n ; i++) {
            countComparisons();                                         // comparisons
            if(mx < columns.get(i).getHeight())
                mx = columns.get(i).getHeight();
        }

        return mx;
    }
    private void countSort(int n, int exp) {
        int range = 800;


        int[] count = new int[range];                                   // 0 - 799
        int[] output = new int[n];                                      // columns size

        for (Column column:columns) {
            count[(column.getHeight()/exp)%10]++;
            countComparisons();                                         // comparisons
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i-1];
            countComparisons();                                         // comparisons
        }
        int min = 0;
        for (int i = columns.size() - 1; i >= 0; i--)
        {
            output[count[ (columns.get(i).getHeight()/exp)%10] - 1 ] = columns.get(i).getHeight();
            count[(columns.get(i).getHeight()/exp)%10]--;
            countComparisons();                                         // comparisons
        }

        for (int i = 0; i < columns.size(); i++)
        {
            columns.get(i).setHeight(output[i]);

            //non algorithm
            changeColumnsColor(comparedColor, i);                       // change color
            countComparisons();
            countConversions();
            tickSleep(i);                                               // sleep
            checkIfPauseAndReset();
            changeColumnsColor(normalColor, i);                         // change color back
        }
    }
}
