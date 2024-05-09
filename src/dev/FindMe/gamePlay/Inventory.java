package dev.FindMe.gamePlay;

import dev.FindMe.Handler;
import dev.FindMe.graphics.Assets;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Inventory {

    private Handler handler;
    private ArrayList<Item> items;
    private boolean active = false;

    public Inventory(Handler handler){
        this.handler = handler;
        items = new ArrayList<>();
    }

    public void update() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_I)){
            active = !active;
        }
    }

    public void render(Graphics g) {
        if(active) {

            int x = 500,y = 300;
            int width = 300, height = 300;
            int invStepSizex = 62, invStepSizey = 50, invXSlot = 4;

            g.drawImage(Assets.inventory, x, y, width, height, null);
            int a = 0;
            int b = 0;
            for(Item i: items) {
                i.render(g, 540 + a * invStepSizex, 330 + b * invStepSizey);
                a++;
                if(a==invXSlot){
                    b++;
                    a = 0;
                }
            }
        }
    }

    public void addItem(Item item){
        items.add(item);
    }

    public boolean haveItem(Item item){
        for(Item i: items){
            if(i.getId() == item.getId())
                return true;
        }
        return false;
    }

    public void deleteItem(Item item){
        items.remove(item);
    }

}
