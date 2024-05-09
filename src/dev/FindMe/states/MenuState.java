package dev.FindMe.states;

import dev.FindMe.Handler;
import dev.FindMe.UI.StartButton;
import dev.FindMe.UI.UIManager;
import dev.FindMe.graphics.Assets;

import java.awt.Graphics;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        if(handler.getMouseManager().getUiManager() == null) {
            handler.getMouseManager().setUiManager(uiManager);
        }
        uiManager.addObject(new StartButton(handler, (float) handler.getWidth()/2-75 , (float) handler.getHeight()/2-25, 150, 50, Assets.start_btn));
    }

    @Override
    public void update() {
        uiManager.update();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }

    @Override
    public UIManager getUiManager() {
        return uiManager;
    }


}
