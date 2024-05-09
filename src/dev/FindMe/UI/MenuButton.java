package dev.FindMe.UI;


import dev.FindMe.Handler;
import dev.FindMe.graphics.Assets;
import dev.FindMe.graphics.TextDrawer;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MenuButton extends UIObject {

    private BufferedImage image;
    private boolean visible = false;

    public MenuButton(Handler handler, float x, float y, int width, int height, BufferedImage image){
        super(handler, x, y, width, height);
        this.image = image;
    }

    public void update(){}

    public void render(Graphics g) {
        if (visible) {
            g.drawImage(image, (int) x, (int) y, width, height, null);
            TextDrawer.drawText(g, "Main Menu", (int) x + 47, (int) y + 17, true, Color.BLACK, Assets.pixelFont8);
        }
    }

    public void onClick(){
        visible = false;
        onObject = false;
        handler.changeState();
    }

    @Override
    public void onMouseMove(MouseEvent e) {
        if(!visible)
            return;
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
        if(!visible)
            return;
        super.onMouseRelease(e);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
