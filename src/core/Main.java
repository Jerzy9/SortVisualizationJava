package core;

import javax.swing.*;

public class Main {
    //private JFrame jFrame;
    public static void main(String arg[]) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}