package com.kozyrski.diamondFury;//com.kozyrski.diamondFury.com.kozyrski.diamondFury.MusicAndSound thanks to teknoaxe.
//Title: Chiptune_Arctic_Odyssey
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicAndSound {
    Clip clip;
    AudioInputStream audioInputStream;

    public void playMusic() {

        try {
            audioInputStream = AudioSystem
                    .getAudioInputStream(new File("src/com.kozyrski.diamondFury.com.kozyrski.diamondFury.MusicAndSound/Chiptune_Artic_Odyssey.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
    public Clip playSound(int soundNo) {
        clip = null;
        switch (soundNo) {
            case 1:
                try {
                    audioInputStream = AudioSystem
                            .getAudioInputStream(new File("src/com.kozyrski.diamondFury.com.kozyrski.diamondFury.MusicAndSound/Pickup_Coin7.wav").getAbsoluteFile());
                    clip = AudioSystem.getClip();

                    clip.open(audioInputStream);
                    clip.start();
                    break;
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
            case 2:
                try {
                    audioInputStream = AudioSystem
                            .getAudioInputStream(new File("src/com.kozyrski.diamondFury.com.kozyrski.diamondFury.MusicAndSound/Hit_Hurt.wav").getAbsoluteFile());
                    clip = AudioSystem.getClip();

                    clip.open(audioInputStream);
                    clip.start();
                    break;

                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
            case 3:
                try {
                    audioInputStream = AudioSystem
                            .getAudioInputStream(new File("src/com.kozyrski.diamondFury.com.kozyrski.diamondFury.MusicAndSound/Powerup3.wav").getAbsoluteFile());
                    clip = AudioSystem.getClip();

                    clip.open(audioInputStream);
                    clip.start();
                    break;

                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
        }
        return clip;
    }
}