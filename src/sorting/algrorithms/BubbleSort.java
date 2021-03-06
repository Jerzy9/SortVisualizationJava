package sorting.algrorithms;

import components.Column;
import core.panels.SortPanel;
import sorting.Algorithm;

import java.util.List;

public class BubbleSort extends Algorithm {

    public BubbleSort(SortPanel sortPanel, List<Column> columns, int sleepTime, int moduloSleep) {
        super(sortPanel, columns, sleepTime, moduloSleep);
    }

    public void sort() {

        for (int i = 0; i < columns.size(); i++) {

            for (int j = 0; j < columns.size()-i-1; j++) {
                countComparisons();

                changeColumnsColor(comparedColor, j+1);
                changeColumnsColor(comparedColor, j);

                if (columns.get(j).getHeight() > columns.get(j+1).getHeight()) {
                    swapAndCountConversions(j, j +1);

                    //non algorithm
                    soundHeight = columns.get(j).getHeight();                   // SOUND, soundEffect object gets know witch sound it should play
                }

                //non algorithm
                // sleep after every single tick
                tickSleep(j);
                // sleep until on = true, so until user will press START button again
                checkIfPauseAndReset();
                changeColumnsColor(normalColor, j+1);
                changeColumnsColor(normalColor, j);

            }

        }
    }
}
