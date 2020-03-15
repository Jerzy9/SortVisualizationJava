package components;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SoundEffect  implements Runnable {
    private boolean running = false;
    private Thread thread;
    private float frequency = 94100;        // originally here was 44100
    private byte[] buf;
    private AudioFormat af;
    private int height = 0, heightAfter = 0;

    private SourceDataLine sdl;

    // temporary
    private boolean addHarmonic = false;
    private int volume = 30;

    public SoundEffect() {
        buf = new byte[1];
        af = new AudioFormat(frequency,8,1,true,false);

        try {
            sdl = AudioSystem.getSourceDataLine(af);
            sdl.open(af);
            sdl.start();
        } catch (LineUnavailableException ex) {
            System.out.println("kek cos sie zepsulo");
        }
    }
    public void generateTone(int hz, int time) {
        for(int i=1; i<time*frequency/1000; i++){
            double angle = i/(frequency/hz)*2.0*Math.PI;
            buf[0]=(byte)(Math.sin(angle)*volume);

            sdl.write(buf,0,1);

        }
    }
    public void closeChanel() {
        sdl.drain();
        sdl.stop();
        sdl.close();
    }
    private void tick() {
        //if(heightAfter != height) {
            generateTone(heightAfter*4, 30);
            heightAfter = height;
            System.out.println("tone has been generated");
       // }
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
    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    ////    Getters and Setters     ////
    public void setRunning(boolean running) {
        this.running = running;
    }
    public void setHeight(int height) {
        this.height = height;
        System.out.println("new height set");
    }
}
