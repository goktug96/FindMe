package dev.FindMe.graphics;

import dev.FindMe.Entity.Entity;
import dev.FindMe.Handler;
import dev.FindMe.Tiles.Tile;

public class gameCamera {

    private float xOffset, yOffset;
    private Handler handler;

    public gameCamera(Handler handler){
        this.handler = handler;
        this.xOffset = 0;
        this.yOffset = 0;
    }

    public void centerOnPlayer(Entity e){
        xOffset =  e.getX() - (float) (handler.getWidth()/2 - e.getWidth()/2);
        yOffset = e.getY() - (float) (handler.getHeight()/2 - e.getHeight()/2);
        if(xOffset < 0)
            xOffset = 0;
        else if(xOffset > handler.getWorld().getWidth()*Tile.TILEWIDTH - handler.getWidth())
            xOffset = handler.getWorld().getWidth()*Tile.TILEWIDTH - handler.getWidth();
        if(yOffset < 0)
            yOffset = 0;
        else if(yOffset > handler.getWorld().getHeight()*Tile.TILEHEIGHT - handler.getHeight()){
            yOffset = handler.getWorld().getHeight()*Tile.TILEHEIGHT - handler.getHeight();
        }
    }

    public void move(float xAmt, float yAmt){
        xOffset += xAmt;
        yOffset += yAmt;
    }


    //Getters and Setters
    public float getxOffset() {
        return xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

}
