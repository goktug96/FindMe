package dev.FindMe.UI;

import dev.FindMe.Handler;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {

    private Handler handler;
    private ArrayList<UIObject> objects;
    private UIObject  activeObject;

    public UIManager(Handler handler){
        this.handler = handler;
        objects = new ArrayList<>();
        activeObject = null;
    }

    public void update(){
        for(UIObject o: objects){
            o.update();
        }
    }

    public void render(Graphics g){
        for(UIObject o: objects){
            o.render(g);
        }
        for(UIObject o: objects){
            if(o instanceof VillageUI){
                VillageUI v = (VillageUI) o;
                v.getVillageQuestions().render(g);
            }
        }
    }

    public void onMouseMove(MouseEvent e){
        if(activeObject!=null){
            activeObject.onMouseMove(e);
            if (!activeObject.onObject) {
                activeObject = null;
            }
        }else {
            for (UIObject o : objects) {
                o.onMouseMove(e);
                if (o.onObject) {
                    activeObject = o;
                    return;
                }
            }
        }
    }

    public void onMouseRelease(MouseEvent e){
        for(UIObject o: objects){
            o.onMouseRelease(e);
        }
    }

    public void addObject(UIObject o){
        objects.add(o);
    }


    public Handler getHandler() {
        return handler;
    }

}
