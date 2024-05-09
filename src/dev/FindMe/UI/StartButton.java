package dev.FindMe.UI;

import dev.FindMe.Handler;
import dev.FindMe.graphics.Assets;
import dev.FindMe.states.GameState;


import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class StartButton extends UIObject {

    private BufferedImage image;

    public StartButton(Handler handler, float x, float y, int width, int height, BufferedImage image){
        super(handler, x, y, width, height);
        this.image = image;
    }

    @Override
    public void update(){

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, width, height, null);
    }

    @Override
    public void onClick(){
        GameState gameState = new GameState(handler);
        handler.getGame().setGameState(gameState);
        handler.changeState();
    }

    @Override
    public void onMouseMove(MouseEvent e) {
        if(bounds.contains(e.getX(), e.getY())){
            onObject = true;
            handler.getGame().getDisplay().getFrame().setCursor(Assets.handCursor);
        }
        else{
            onObject = false;
            handler.getGame().getDisplay().getFrame().setCursor(Assets.defCursor);
        }
    }

    @Override
    public void onMouseRelease(MouseEvent e) {
        super.onMouseRelease(e);
    }
}
