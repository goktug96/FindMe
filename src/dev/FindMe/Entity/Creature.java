package dev.FindMe.Entity;

import dev.FindMe.Handler;
import dev.FindMe.Tiles.Tile;

import java.awt.Graphics;

public abstract class Creature extends Entity{

    public static final float DEF_SPEED = 3.0f;
    public static final int DEF_WIDTH = 64;
    public static final int DEF_HEIGHT = 64;

    protected float speed;
    protected float xMove, yMove;
    protected boolean isLocked = false;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEF_SPEED;
        xMove = 0; yMove = 0;
    }

    protected void move(){
        if(isLocked)
            return;
        if(!checkCollision(xMove, 0f))
            moveX();
        if(!checkCollision(0f, yMove))
            moveY();
    }

    private void moveX(){
        if(xMove > 0){ //moving right
            int xAfterMove = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if(!collisionDetection(xAfterMove, (int) (y + bounds.y) / Tile.TILEHEIGHT) && //if upper right or lower right coordinate is not in a solid tile
                    !collisionDetection(xAfterMove, (int)(y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            }
        }else if(xMove < 0){
            int xAfterMove = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if(!collisionDetection(xAfterMove, (int) (y + bounds.y) / Tile.TILEHEIGHT) && //if upper right or lower right coordinate is not in a solid tile
                    !collisionDetection(xAfterMove, (int)(y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            }
        }
    }

    public void moveY(){
        if(yMove > 0){ //moving down
            int yAfterMove = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            if(!collisionDetection((int) (x + bounds.x) / Tile.TILEWIDTH, yAfterMove) && //if upper right or lower right coordinate is not in a solid tile
                    !collisionDetection( (int)(x + bounds.x + bounds.width) / Tile.TILEWIDTH, yAfterMove)){
                y += yMove;
            }
        }else if(yMove < 0){  //moving up
            int yAfterMove = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            if(!collisionDetection((int) (x + bounds.x) / Tile.TILEWIDTH, yAfterMove) &&
                    !collisionDetection((int)(x + bounds.x + bounds.width) / Tile.TILEWIDTH, yAfterMove)){
                y += yMove;
            }
        }
    }

    private boolean collisionDetection(int x, int y){
        return handler.getWorld().getTile(x,y).isSolid();
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }


    //Getters and Setters

    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}
