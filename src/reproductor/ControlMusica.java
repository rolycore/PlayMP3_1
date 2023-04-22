package reproductor;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class ControlMusica {

    FileInputStream FIS;
    BufferedInputStream BIS;

    public Player player;

    public long pauseLocation;
    public long songtotalLength;

    public String fileLocation;

    public void Stop() {
        player.close();

        pauseLocation = 0;
        songtotalLength = 0;
    }

    public void Pause() {
            try {
                pauseLocation = FIS.available();
                player.close();
            } catch (IOException ex) {
            }
    }

    public void Resume() {

        try {
            FIS = new FileInputStream(fileLocation);
            BIS = new BufferedInputStream(FIS);

            player = new Player(BIS);

            FIS.skip(songtotalLength - pauseLocation);
        } catch (FileNotFoundException | JavaLayerException ex) {

        } catch (IOException ex) {
        }
        new Thread() {
            @Override
            public void run() {

                try {
                    player.play();
                } catch (JavaLayerException ex) {
                }
            }
        }.start();
    }

    public void Play(String path) {

        try {
            FIS = new FileInputStream(path);
            BIS = new BufferedInputStream(FIS);

            player = new Player(BIS);

            songtotalLength = FIS.available();

            fileLocation = path + "";
        } catch (FileNotFoundException | JavaLayerException ex) {
        } catch (IOException ex) {
        }
        new Thread() {
            @Override
            public void run() {

                try {
                    player.play();
                } catch (JavaLayerException ex) {
                }
            }
        }.start();
    }
}
