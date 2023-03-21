import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Sound {
   // Class testClass = getClass();

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() throws MalformedURLException {
        //soundURL[0] = getClass().getResource("/TPP Game Project/res/sound/BlueBoyAdventure.wav");
        File file = new File("TPP Game Project/res/sound/BlueBoyAdventure.wav");//Alternative way to get sound
        soundURL[0] = file.toURI().toURL();
        File file1 =new File("TPP Game Project/res/sound/impact.wav");
        soundURL[1] = file1.toURI().toURL();
        File file2 = new File("TPP Game Project/res/sound/boss_theme2.wav");
        soundURL[2] = file2.toURI().toURL();
        File file3 = new File("TPP Game Project/res/sound/powerup.wav");
        soundURL[3] = file3.toURI().toURL();
        File file4 = new File("TPP Game Project/res/sound/unlock.wav");
        soundURL[4] = file4.toURI().toURL();
        File file5 = new File("TPP Game Project/res/sound/hitmonster.wav");
        soundURL[5] = file5.toURI().toURL();
        File file6 = new File("TPP Game Project/res/sound/receivedamage.wav");
        soundURL[6] = file6.toURI().toURL();
        File file7 = new File("TPP Game Project/res/sound/fanfare.wav");
        soundURL[7] = file7.toURI().toURL();
        File file8 = new File("TPP Game Project/res/sound/shootprojectile.wav");
        soundURL[8] = file8.toURI().toURL();
        File file9 = new File("TPP Game Project/res/sound/gameover.wav");
        soundURL[9] = file9.toURI().toURL();
//        File file10 = new File("TPP Game Project/res/sound/suspence.wav");
//        soundURL[10] = file10.toURI().toURL();
    }

    public void setFile(int i){
        try{

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (UnsupportedAudioFileException e) {

        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
