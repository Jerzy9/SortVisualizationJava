package Sort;

import Sort.SortPanel;

public class BubbleSort {

    void bubbleSort()
    {
        int n = SortPanel.columns.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (SortPanel.columns.get(j).getHeight() > SortPanel.columns.get(j+1).getHeight())
                {
                    // swap arr[j+1] and arr[i]
                    int temp = SortPanel.columns.get(j).getHeight();
                    SortPanel.columns.get(j).setHeight(SortPanel.columns.get(j+1).getHeight());
                    SortPanel.columns.get(j+1).setHeight(temp);
                }
    }
}
