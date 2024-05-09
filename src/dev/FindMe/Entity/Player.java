package dev.FindMe.Entity;

import dev.FindMe.Handler;
import dev.FindMe.graphics.Animation;
import dev.FindMe.graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature{

    private Animation animDown, animUp, animLeft, animRight, animStands;

    public Player(Handler handler, float x, float y) {

        super(handler, x, y, Creature.DEF_WIDTH, Creature.DEF_HEIGHT);
        bounds.x = 25;
        bounds.y = 35;
        bounds.width = 15;
        bounds.height = 22;

        animDown = new Animation(300, Assets.player_down);
        animUp = new Animation(300, Assets.player_up);
        animLeft = new Animation(200, Assets.player_left);
        animRight = new Animation(200, Assets.player_right);
        animStands = new Animation(150, Assets.player_stands);
    }

    public void getInput(){
        if(isLocked)
            return;
        xMove = 0;
        yMove = 0;
        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().right)
            xMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
    }

    public void update(){
        if(xMove < 0)
            animLeft.update();
        else if(xMove > 0)
            animRight.update();
        else if(yMove < 0)
            animUp.update();
        else if(yMove > 0)
            animDown.update();
        else
            animStands.update();
        getInput();
        if(xMove != 0 || yMove != 0)
            move();
        handler.getGameCam().centerOnPlayer(this);
    }

    @Override
    public void render(Graphics g){
        g.drawImage(getCurrentAnim(), (int) (x - handler.getGameCam().getxOffset()),
                (int) (y - handler.getGameCam().getyOffset()), width, height, null);
        g.setColor(Color.RED);
        /*g.fillRect((int) (x + bounds.x - handler.getGameCam().getxOffset()),
                (int) (y + bounds.y - handler.getGameCam().getyOffset()), bounds.width, bounds.height);*/
    }

    private BufferedImage getCurrentAnim(){
        if(xMove < 0)
            return animLeft.getCurrent();
        else if(xMove > 0)
            return animRight.getCurrent();
        else if(yMove < 0)
            return animUp.getCurrent();
        else if(yMove > 0)
            return animDown.getCurrent();
        else
            return animStands.getCurrent();
    }
}
