package view;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

/**
 * 播放音乐及停止音乐
 */
public class Music implements Runnable {
    private Player player = null;
    private Thread thread = null;
    private String music;
    public Music(String file) {
        this.music = file;
    }

    public void run() {
        try {
            play();
        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }
    }
    public void play() throws FileNotFoundException, JavaLayerException {
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
        player = new Player(buffer);
        player.play();
    }

    public void start() {
        thread = new Thread(this, "Player thread");
        thread.start();
    }


    public void stop() {
        player.close();
        thread = null;
    }

    public Player getPlayer() {
        return player;
    }
}
