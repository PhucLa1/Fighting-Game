package Sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sound {
	private File soundFile;
	private Clip clip;
	
	
	//Khoi tao doi tuong sound
	public Sound(String path) {
        soundFile = new File(path);
        try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			clip.open(AudioSystem.getAudioInputStream(soundFile));
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Chay nhac
	public void playMusic() {
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	
	//Dung nhac
	public void pauseMusic() {
		clip.stop();
	}
	
	//Chinh am luong cua nhac
	public void setVolume(float vol) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(vol);
	}
}
