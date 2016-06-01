package com.kozyrski.diamondFury;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class PowerUpTimer {
    Toolkit toolkit;
    boolean isStillOn = false;
    Timer timer;
    public void timer(int seconds) {
        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds * 1000);
        isStillOn = true;
    }
    class RemindTask extends TimerTask {
        public void run() {
            isStillOn = false;
        }
    }
}