package sorting.algrorithms;

import components.Column;
import sorting.Algorithm;

import java.util.List;

public class SelectionSort extends Algorithm {
    public SelectionSort(List<Column> columns, int sleepTime, int moduloSleep) {
        super(columns, sleepTime, moduloSleep);
    }

    @Override
    public void sort() {
        for (int i = 0; i < columns.size(); i++) {
            int min = columns.get(i).getHeight();
            int index = i;

            for (int j = i + 1; j < columns.size(); j++) {

                comparisons++;
                if(min > columns.get(j).getHeight()) {
                    min = columns.get(j).getHeight();
                    index = j;
                }

                tickSleep(j);
                checkIfPauseAndReset();

            }
            if(columns.get(i).getHeight() != min) {
                int temp = columns.get(i).getHeight();
                columns.get(i).setHeight(min);
                columns.get(index).setHeight(temp);

                conversions++;
            }
        }
    }
}
