package dev.FindMe.Entity;

import dev.FindMe.Handler;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {

    protected float x, y; //positions of entities
    protected int width, height;
    protected Handler handler;
    protected Rectangle bounds;

    public Entity(Handler handler, float x, float y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public boolean checkCollision(float xOffset, float yOffset){
        for(Entity e: handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this)) {
                continue;
            }
            if(e.getCollisionBounds(0,0).intersects(getCollisionBounds(xOffset, yOffset)))
                return true;
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int)(x + bounds.x + xOffset), (int)(y + bounds.y + yOffset), bounds.width, bounds.height);
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
}
