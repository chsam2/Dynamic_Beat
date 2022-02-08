import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.File;
import java.io.IOException;

public class Music{
	private Clip clip;
	boolean isloop;
	private long startTime;
	public Music(String file, boolean isloop) {
		try {
			this.isloop = isloop;
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("src/Music/"+file));
			clip = AudioSystem.getClip();
			clip.open(ais);
			
			//소리설정
			FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			//볼륨조절
			gainControl.setValue(-10.0f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int timer(long startTime, boolean s) {
		while(s) {
		long endTime = System.currentTimeMillis();
		if(endTime-startTime > clip.getMicrosecondLength()) {
			return 0;
		}
			return (int) (endTime-startTime);
		}
		return 0;
	}
	public int getTime() {//재생중인 음악의 현재시간
		//getMicrosecondLength == 음악 전체 시간
		return timer(startTime, true);
	}
	
	public void start() {
		try {
			clip.start();
			startTime = System.currentTimeMillis();
			if(isloop) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void close() {
		clip.close();
		timer(0, false);
	}
}
