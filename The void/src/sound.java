import java.io.File;
import java.net.URL;
import javax.swing.*;
import javax.sound.sampled.*;
import java.util.concurrent.TimeUnit;
public class sound {

    public sound() throws Exception {
        Clip clip = AudioSystem.getClip();
        // getAudioInputStream() also accepts a File or InputStream
        File filde = new File("src/Main.wav");
    	String absolutePathd = filde.getAbsolutePath();
        AudioInputStream ais = AudioSystem.
            getAudioInputStream(new File(absolutePathd));
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // A GUI element to prevent the Clip's daemon Thread
                // from terminating at the end of the main()
            }
        });
    }
}