package core;

import components.listeners.FloatListener;
import components.listeners.NumberListener;
import core.panels.AlgoPanel;
import core.panels.StatsPanel;
import core.panels.TopPanel;
import core.panels.SortPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private int WIDTH = 1600, HEIGHT = 900;

    private Container mainContainer;

    private Color bgColor = Color.white;

    private TopPanel topPanel;
    private int topPanelWidth = WIDTH - 300,
                topPanelHeight = 100;

    private SortPanel sortPanel;
    private int sortPanelWidth = WIDTH - 300,
                sortPanelHeight = HEIGHT - topPanelHeight;

    private AlgoPanel algoPanel;
    private int sidePanelWidth = 300,
                sidePanelHeight = HEIGHT - 250;

    private StatsPanel statsPanel;
    private int statsPanelWidth = 300,
                statsPanelHeight = 211; // real height

    /////////////       MainFrame Constructor       /////////////

    MainFrame() {
        ///// MAIN CONTAINER /////
        mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(8,6));
        mainContainer.setBackground(Color.black);

        ///// MIDDLE CONTAINER /////
        Container middleContainer = new Container();
        middleContainer.setLayout(new BoxLayout(middleContainer, BoxLayout.Y_AXIS));

        topPanel = new TopPanel(topPanelWidth, topPanelHeight, bgColor);
        sortPanel = new SortPanel(sortPanelWidth, sortPanelHeight, bgColor);
        algoPanel = new AlgoPanel(sidePanelWidth, sidePanelHeight, bgColor);
        statsPanel = new StatsPanel(statsPanelWidth, statsPanelHeight, bgColor);

        // Top Panel
        // All three buttons in TopPanel, Start, Stop and Reset
        topPanel.setButtonListener(new NumberListener() {
            @Override
            public void numberEmitted(int number) {
                if(number == 1) {
                    sortPanel.startButton();        // START
                } else if (number == 2) {
                    sortPanel.stopButton();       // STOP
                } else if (number == 3) {
                    sortPanel.resetButton();          // RESET
                }
            }
        });
        // size slider
        topPanel.setColumnsWidthListener(new NumberListener() {
            @Override
            public void numberEmitted(int number) {
                if(sortPanel.getColumnsWidth() != number) sortPanel.setColumnsWidth(number);
            }
        });
        // speed slider
        topPanel.setSpeedListener(new NumberListener() {
            @Override
            public void numberEmitted(int number) {
                if(sortPanel.getSortSpeed() != number) sortPanel.setSortSpeed(number);
            }
        });

        // Sort Panel
        sortPanel.setElementsListener(new NumberListener() {
            @Override
            public void numberEmitted(int number) {
                statsPanel.setElements(number);
            }
        });
        sortPanel.setComparisonsListener(new NumberListener() {
            @Override
            public void numberEmitted(int number) {
                statsPanel.setComparisons(number);
            }
        });
        sortPanel.setConversionsListener(new NumberListener() {
            @Override
            public void numberEmitted(int number) {
                statsPanel.setConversions(number);
            }
        });
        sortPanel.setTimeListener(new FloatListener() {
            @Override
            public void floatEmitted(float number) {
                statsPanel.setTime(number);
            }
        });
        sortPanel.setDelayListener(new FloatListener() {
            @Override
            public void floatEmitted(float number) {
                statsPanel.setDelay(number);
            }
        });

        ///// SIDE CONTAINER /////
        Container sideContainer = new Container();
        sideContainer.setLayout(new BoxLayout(sideContainer, BoxLayout.Y_AXIS));

        //Algorithm Panel
        algoPanel.setAlgorithmListener(new NumberListener() {
            @Override
            public void numberEmitted(int number) {
                sortPanel.setNumOfAlgorithm(number);
            }
        });

        //Stats Panel

        //Algorithm


        ///// Adding Components /////
        middleContainer.add(topPanel);
        middleContainer.add(sortPanel);

        sideContainer.add(algoPanel);
        sideContainer.add(statsPanel);

        mainContainer.add(middleContainer, BorderLayout.EAST);
        mainContainer.add(sideContainer, BorderLayout.WEST);

        setMainFrame();
    }

    private void setMainFrame() {
        setTitle("Sort Visualization");
        //window location's just for current device
        setLocation(150, 80);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}