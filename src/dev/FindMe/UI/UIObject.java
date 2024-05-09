package dev.FindMe.UI;

import dev.FindMe.Handler;
import dev.FindMe.graphics.Assets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UIObject {

    protected float x, y;
    protected int width, height;
    protected boolean onObject = false;
    protected Rectangle bounds;
    protected Handler handler;

    public UIObject(Handler handler, float x, float y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void onClick();

    public void onMouseMove(MouseEvent e){
        if(bounds.contains(e.getX() + handler.getGame().getGameCam().getxOffset(), e.getY() + handler.getGame().getGameCam().getyOffset())) {
            onObject = true;
            handler.getGame().getDisplay().getFrame().setCursor(Assets.handCursor);
        }
        else {
            onObject = false;
            handler.getGame().getDisplay().getFrame().setCursor(Assets.defCursor);
        }
    }

    public void onMouseRelease(MouseEvent e){
        if(onObject){
            onClick();
        }
    }

    //Getters and Setters
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isonObject(MouseEvent e) {
        return bounds.contains(e.getX() + handler.getGame().getGameCam().getxOffset(), e.getY() + handler.getGame().getGameCam().getyOffset());
    }

    public void setonObject(boolean hovering) {
        this.onObject = hovering;
    }
}
