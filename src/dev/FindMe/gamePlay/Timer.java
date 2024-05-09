package dev.FindMe.gamePlay;

import dev.FindMe.Handler;
import dev.FindMe.graphics.Assets;
import dev.FindMe.graphics.TextDrawer;


import java.awt.Graphics;
import java.awt.Color;

public class Timer {

    private boolean active = false, timeOver = false, stopTimer = false;
    private Integer minute, second;
    private String secondString, minuteString;
    private long currentTime, passedTime, previousTime;
    Handler handler;

    public Timer(int minute, int second, Handler handler){
        this.handler = handler;
        this.minute = minute;
        if(minute < 0)
            minuteString = "0" + this.minute.toString();
        else
            minuteString = this.minute.toString();

        this.second = second;
        if(second < 10)
            secondString = "0" + this.second.toString();
        else
           secondString = this.second.toString();
    }

    public Timer(Handler handler){
        this.handler = handler;
        minute = 15;
        minuteString = minute.toString();
        second = 0;
        secondString = "0" + second.toString();
    }

    public void update(){
        if(!active)
            return;
        countDown();

    }

    public void render(Graphics g){
        if(!active){
            return;
        }
        g.drawImage(Assets.orangeButton, 600, 15, 100, 40, null);
        if(active) {
            String time = minuteString + ":" + secondString;
            TextDrawer.drawText(g, time, 647, 32, true, Color.BLACK, Assets.pixelFont8);
        }

        if(timeOver){
            g.drawImage(Assets.rect, 300, 200, 350, 100, null);
            TextDrawer.drawText(g, "Game Over", 475,250, true, Color.BLACK, Assets.pixelFont8);
            stopTimer = true;
        }
    }

    public void countDown(){
        currentTime = System.currentTimeMillis();
        passedTime = currentTime - previousTime;

        if(passedTime >= 1000 && !stopTimer){
            if(minute == 0 && second == 0)
                timeOver = true;
            if(second < 0){
                second = 59;
            }

            if(second < 10){
                secondString = "0" + second.toString();
            }else{
                secondString = second.toString();
            }
            if(second == 59){
                minute--;
                if(minute < 10) {
                    minuteString = "0" + minute.toString();
                }
            }
            second--;
            minuteString = minute.toString();

            previousTime = currentTime;
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getMinute() {
        return minute;
    }

    public Integer getSecond() {
        return second;
    }

    public void setPreviousTime(long previousTime) {
        this.previousTime = previousTime;
    }

    public void setStopTimer(boolean stopTimer) {
        this.stopTimer = stopTimer;
    }
}
