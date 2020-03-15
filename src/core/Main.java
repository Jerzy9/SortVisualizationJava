package core;

import components.SoundEffect;

import javax.swing.*;

public class Main {
    public static void main(String arg[]) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}