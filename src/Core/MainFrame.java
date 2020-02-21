package Core;

import Components.NumberListener;
import Sort.SortPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainFrame extends JFrame{
    private int WIDTH = 1600, HEIGHT = 900;

    Color c2 = new Color(160,233,210);

    private Container mainContainer;

    private Color bgColor = Color.white;

    private TopPanel topPanel;
    private int topPanelWidth = WIDTH - 300,
                topPanelHeight = 100;

    private SortPanel sortPanel;
    private int sortPanelWidth = WIDTH - 300,
                sortPanelHeight = HEIGHT - topPanelHeight;

    private AlgorithmPanel algorithmPanel;
    private int sidePanelWidth = 300,
                sidePanelHeight = HEIGHT - 250;

    private StatsPanel statsPanel;
    private int statsPanelWidth = 300,
                statsPanelHeight = 250;

    /////////////       MainFrame Constructor       /////////////

    public MainFrame() {
        ///// MAIN CONTAINER /////
        mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(8,6));
        mainContainer.setBackground(Color.black);
        //this.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.black));     //border na około wszystkiego

        ///// MIDDLE CONTAINER /////
        Container middleContainer = new Container();
        middleContainer.setLayout(new BoxLayout(middleContainer, BoxLayout.Y_AXIS));

        // Top Panel
        topPanel = new TopPanel(topPanelWidth, topPanelHeight, bgColor);
        topPanel.setBorder(new LineBorder(Color.black,4));

        topPanel.setButtonListener(new NumberListener() {
            @Override
            public void numberEmitted(int number) {
                if(number == 1) {
                    sortPanel.startOn();
                } else if (number == 2) {
                    sortPanel.startOff();
                } else if (number == 3) {
                    sortPanel.reset();
                }
            }
        });
        topPanel.setSizeListener(new NumberListener() {
            @Override
            public void numberEmitted(int number) {
                sortPanel.setSize(number);
            }
        });
        topPanel.setSpeedListener(new NumberListener() {
            @Override
            public void numberEmitted(int number) {
                sortPanel.setSpeed(number);
            }
        });

        // Sort Panel
        sortPanel = new SortPanel(sortPanelWidth, sortPanelHeight, bgColor);
        sortPanel.setBorder(new LineBorder(Color.black, 4));

        ///// SIDE CONTAINER /////
        Container sideContainer = new Container();
        sideContainer.setLayout(new BoxLayout(sideContainer, BoxLayout.Y_AXIS));

        //Algorithm Panel
        algorithmPanel = new AlgorithmPanel(sidePanelWidth, sidePanelHeight, bgColor);
        algorithmPanel.setBorder(new LineBorder(Color.black, 4));

        algorithmPanel.setNumberListener(new NumberListener() {
            @Override
            public void numberEmitted(int number) {
                sortPanel.setNumOfAlgorithm(number);
            }
        });

        //Stats Panel
        statsPanel = new StatsPanel(statsPanelWidth, statsPanelHeight, bgColor);
        statsPanel.setBorder(new LineBorder(Color.black, 4));

        ///// Adding Components /////
        middleContainer.add(topPanel);
        middleContainer.add(sortPanel);

        sideContainer.add(algorithmPanel);
        sideContainer.add(statsPanel);

        mainContainer.add(middleContainer, BorderLayout.EAST);
        mainContainer.add(sideContainer, BorderLayout.WEST);

        setMainFrame();
    }

    /////////////       Graphics        /////////////

    public void paint(Graphics g) {
        super.paintComponents(g);
    }

    private void setMainFrame() {
        setTitle("Sort Visualization");
        //window location's just for current device
        setLocation(150, 80);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}