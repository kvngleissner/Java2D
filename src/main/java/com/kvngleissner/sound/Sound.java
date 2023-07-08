package com.kvngleissner.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundUrl[] = new URL[10];
    FloatControl floatControl;
    public Sound() {
        soundUrl[0] = getClass().getResource("/sound/music/Town.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(-25.0f);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void playSound() {
        clip.start();
    }
    public void loopSound() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stopSound() {
        clip.stop();
    }
}
