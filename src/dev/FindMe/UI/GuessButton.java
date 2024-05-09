package dev.FindMe.UI;

import dev.FindMe.Handler;
import dev.FindMe.graphics.Assets;
import dev.FindMe.graphics.TextDrawer;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GuessButton extends UIObject {

    private BufferedImage image;
    private boolean getInput=false, getResult=false, clearText=true, correctResult = false;
    private int GuessCount = 0;
    private String text = "Enter your guess";

    public GuessButton(Handler handler, float x, float y, int width, int height, BufferedImage image){
        super(handler, x, y, width, height);
        this.image = image;
    }

    @Override
    public void update(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && getResult){
            GuessCount++;
            getResult = false;
            handler.getGame().getGameState().getWorld().getEntityManager().getPlayer().setLocked(false);
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && getInput){
            getInput = false;
            getResult = true;
        }
        char c = handler.getKeyManager().getPressedChar();
        if(getInput && c != (char)0 && !handler.getKeyManager().enter){
            if(clearText) {
                text = "";
                clearText = false;
            }
            if(handler.getKeyManager().delete){
                if (text.length() > 0)
                    text = text.substring(0, text.length() - 1);
            }
            else
                text += c;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, width, height, null);
        TextDrawer.drawText(g, "Guess", (int)x + 47, (int) y + 17, true, Color.BLACK, Assets.pixelFont8);

        if(getResult){
            String result;
            g.drawImage(Assets.rect, 250, 100, 350, 300, null);
            text.replace("\n", "");
            if(text.toUpperCase().equals(handler.getGame().getCurrentCelebrity().getName().toUpperCase())){
                result = "Congratulations. Correct.";

                int minute = handler.getGame().getGameState().getTimer().getMinute() * 60;
                int second = handler.getGame().getGameState().getTimer().getSecond();
                int askedQ = handler.getGame().getGameState().getAskedQuestions();
                int score = 10000 - minute - second - (GuessCount-1)*1000 - askedQ*300;

                if(score < 0)
                    score = 100;
                TextDrawer.drawText(g, "Score: " + score, 425, 175, true, Color.BLACK, Assets.pixelFont8);

                handler.getGame().getGameState().getTimer().setStopTimer(true);
                handler.getGame().getGameState().getMenuButton().setVisible(true);
                handler.getGame().getGameState().getExitButton().setVisible(true);

            }else{
                result = "Wrong (Press ENTER to continue)";
                text = "Enter your guess";
                clearText = true;
                handler.getGame().getGameState().getTimer().setStopTimer(false);
            }
            TextDrawer.drawText(g, result, 425,150, true, Color.BLACK, Assets.pixelFont8);
        }

        if(getInput){
            g.drawImage(Assets.answerScreen, 300, 200, 200, 100, null);
            TextDrawer.drawText(g, text, 400, 250, true, Color.WHITE, Assets.pixelFont8);
        }
    }

    @Override
    public void onClick(){
        getInput = true;
        handler.getGame().getGameState().getWorld().getEntityManager().getPlayer().setLocked(true);
    }


    @Override
    public void onMouseMove(MouseEvent e){
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

