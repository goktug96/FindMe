package dev.FindMe.graphics;

import java.awt.image.BufferedImage;

public class Animation{

    private int speed, index;
    private BufferedImage[] frames;

    private long previousTime, timer;

    public Animation(int speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = frames;
        previousTime = System.currentTimeMillis();
        timer = 0;
        index = 0;
    }

    public void update(){
        timer += System.currentTimeMillis() - previousTime;
        previousTime = System.currentTimeMillis();

        if(timer > speed){
            index++;
            timer = 0;
            if(index >= frames.length){
                index = 0;
            }
        }
    }

    public BufferedImage getCurrent(){
        return frames[index];
    }

}
