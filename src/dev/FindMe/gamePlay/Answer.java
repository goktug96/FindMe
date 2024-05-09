package dev.FindMe.gamePlay;

import dev.FindMe.Handler;
import dev.FindMe.graphics.Assets;
import dev.FindMe.graphics.TextDrawer;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class Answer {
    private int xPos, yPos, width, height;
    private int answerPosX, answerPosY;
    private Item granted;
    private Handler handler;
    private boolean active, grantActive = false, alreadyAsked = false;
    private String answer;

    public Answer(Handler handler, String answer, Item granted){
        this.handler = handler;
        this.answer = answer;
        this.granted = granted;
        width = 500;
        height = 300;
        xPos = handler.getGame().getWidth() / 2 - width/2;
        yPos = handler.getGame().getWidth() / 2 - height;
        answerPosX = xPos + 250;
        answerPosY = yPos + 147;
    }

    public void update(){
        if(!active)
            return;

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && grantActive){
            grantActive = false;
            if(granted!=null)
                handler.getGame().getGameState().getInventory().addItem(granted);
            active = false;
            handler.getGame().getGameState().getWorld().getEntityManager().getPlayer().setLocked(false);
        }
        else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
            grantActive = true;
            handler.getGame().getGameState().increaseQNumber();
            alreadyAsked = true;
        }

    }

    public void render(Graphics g){
        if(!active)
            return;
        if(grantActive && granted!=null){
            g.drawImage(Assets.rect, xPos, yPos, width, height, null);
            TextDrawer.drawText(g, "Ziyaret icin tesekkurler. Bu hediyeyi kabul et.", answerPosX, answerPosY - 50, true, Color.WHITE, Assets.pixelFont8);
            granted.render(g, answerPosX - 30, answerPosY);
        }else{
            g.drawImage(Assets.answerScreen, xPos, yPos, width, height, null);
            TextDrawer.drawText(g, answer, answerPosX, answerPosY, true, Color.WHITE, Assets.pixelFont8);
            TextDrawer.drawText(g, "Press Enter to Continue", answerPosX, answerPosY + 25, true, Color.WHITE, Assets.pixelFont6);
        }
    }


    public Handler getHandler() {
        return handler;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isAlreadyAsked() {
        return alreadyAsked;
    }
}
