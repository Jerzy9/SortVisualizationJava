package Sort;

import Core.MainPanel;
import SortComponents.Column;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SortPanel {
    private Random r = new Random();
    static List<Column> columns;

    private Color columnColor = Color.blue;

    private int size;
    private int brickWidth, brickHeight;
    private int sleepTime;
    private int numOfAlgorithm;
    private int columnsMaxWidth,columnsMaxHeight;

    public SortPanel(int brickWidth, int brickHeight, int sleepTime, int numOfAlgorithm, int columnsMaxWidth, int columnsMaxHeight) {
        this.brickWidth = brickWidth;
        this.brickHeight = brickHeight;
        this.sleepTime = sleepTime;
        this.numOfAlgorithm = numOfAlgorithm;
        this.columnsMaxWidth = columnsMaxWidth;
        this.columnsMaxHeight = columnsMaxHeight;

        columns = new ArrayList<>();
        createColumns();
        //shuffle();

    }
    public void draw(Graphics g) {
        for (Column column : columns)
            column.drawColumn(g);
    }
    public void selectAlgorithm() {
        switch (numOfAlgorithm) {
            case 1: new BubbleSort();
            break;
        }
    }
    public void createColumns() {
        //draw Columns
        size = (columnsMaxWidth / brickWidth);
        System.out.println(size);
        brickHeight = columnsMaxHeight / size;
        int space = 10; //(MainFrame.WIDTH - fieldMaxWidth - 160)/2; try to center automatically

        for (int i = 0; i < size; i++)
            columns.add(new Column(i, (space + (i * brickWidth)), (i * brickHeight), brickWidth, brickHeight, columnColor));

        //help sout
        System.out.println("size " + size);
        System.out.println("width: " + brickWidth);
        System.out.println("height: " + brickHeight);
        System.out.println("space: " + space);
    }
    public void shuffle() {
        for (int i = 0; i < size ; i++) {
            columns.get(i).setHeight(r.nextInt(size));
        }
    }
}
