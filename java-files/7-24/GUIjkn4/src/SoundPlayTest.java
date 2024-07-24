import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayTest {
    public static void main(String[] args) throws Exception {
        Clip clip = createClip(new File("グー.wav"));
        clip.start();
        Thread.sleep(3000); // Sleep to allow the clip to play for 3 seconds
    }

    public static Clip createClip(File path) {
        try (AudioInputStream ais = AudioSystem.getAudioInputStream(path)) {
            // Get the audio format
            AudioFormat format = ais.getFormat();
            
            // Construct data line info from the audio format
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            // Get a clip that matches the data line info
            Clip clip = (Clip) AudioSystem.getLine(info);

            // Open the clip with the audio input stream
            clip.open(ais);

            return clip;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }
}