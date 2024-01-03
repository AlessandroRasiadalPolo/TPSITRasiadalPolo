import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class MusicPlayer implements Runnable{

    private Clip clip;
    public MusicPlayer() {
        // upload and prepare the file audio
        try (InputStream inputStream = getClass().getResourceAsStream("/BackgroundMusic.wav")) {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        // Waiting for the thread's ending
        while (true) {
            try {
                Thread.sleep(1000); // Regola la frequenza di controllo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
