package components;

import components.observer_pattern.Observer;
import core.panels.SortPanel;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SoundEffect  implements Runnable, Observer {
    private boolean running = false, runningSort = false;
    private Thread thread;
    private float frequency = 44100;        // originally here was 44100(1 second)
    private byte[] buf;
    private AudioFormat af;
    private SourceDataLine sdl;

    private int height = 0;
    private int volume = 20;
    private int time;

    private SortPanel sortPanel;

    public SoundEffect(SortPanel sortPanel) {
        this.sortPanel = sortPanel;
        buf = new byte[1];
        af = new AudioFormat(frequency,8,1,true,false);

        openChanel();
        start();
    }
    public void generateTone(int hz, int time) {
        //openChanel();

        for(int i=1; i<time*frequency/1000; i++){
            double angle = i/(frequency/hz)*2.0*Math.PI;
            buf[0]=(byte)(Math.sin(angle)*volume);

            sdl.write(buf,0,1);

        }
        //closeChanel();
    }
    public void openChanel() {
        try {
            sdl = AudioSystem.getSourceDataLine(af);
            sdl.open(af);
            sdl.start();
        } catch (LineUnavailableException ex) {
            System.out.println("kek cos sie zepsulo");
        }
    }
    public void closeChanel() {
        sdl.drain();
        sdl.stop();
        sdl.close();
    }
    private void tick() {
        if(runningSort && sortPanel.isSoundOn()) {
            generateTone(height*3, time);
        }
    }
    @Override
    public void run() {
        while(running) {
            tick();

            try {
                Thread.sleep(1000/120);
            } catch (InterruptedException ex) {
                ex.fillInStackTrace();
            }
        }
        closeChanel();
        stop();
    }
    public void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    private void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateRunning() {
        this.runningSort = sortPanel.isSortRunning();
        if(runningSort){
            openChanel();
        } else {
            closeChanel();
        }
        if(sortPanel.isSortReset()) {
            reset();
        }
    }
    public void reset() {
        closeChanel();
        openChanel();
    }

    ////    Getters and Setters     ////
    public void setHeight(int height) {
        this.height = height;
    }
    public void setTime(int time) {
        this.time = time;
    }
}
