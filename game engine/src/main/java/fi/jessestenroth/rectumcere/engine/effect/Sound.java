package fi.jessestenroth.rectumcere.engine.effect;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * This class take care how sound clip must play and play it
 * @author Jesse Stenroth
 * @version 0.1
 */
public class Sound {
    private Clip clip;
    private boolean Repeat = false;

    /**
     * This Constructor set path of sound clip
     * @param path path of clip support only wav format
     */
    public Sound(String path){
        File soundFile = new File(path);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile.getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * This Constructor set path of sound clip
     * @param path path of clip support only wav format
     * @param repeat repeat is boolean is sound repeat when clip end
     */
    public Sound(String path, boolean repeat){
        this.Repeat = repeat;
        File soundFile = new File(path);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile.getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method play sound
     */
    public void play(){
        if(this.Repeat){
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        clip.start();
    }

    /**
     * This method stop sound
     */
    public void stop(){
        clip.stop();
    }
}
