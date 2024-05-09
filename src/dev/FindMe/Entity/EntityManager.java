package dev.FindMe.Entity;

import dev.FindMe.Handler;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

public class EntityManager {
    private Handler handler;
    private Player player;
    private ArrayList <Entity> entities;
    private Comparator<Entity> renderSorter = (a, b) -> {
        if(a.getY() + a.getHeight() < b.getY()+ b.getHeight() )
            return -1;
        return 1;
    };

    public EntityManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<>();
        entities.add(player);
    }

    public void update(){
        for(int i=0; i<entities.size(); i++){
            Entity e = entities.get(i);
            e.update();
        }
        entities.sort(renderSorter);
    }

    public void render(Graphics g){
        for(Entity e: entities){
            e.render(g);
        }
    }
    public void addEntity(Entity e){
        entities.add(e);
    }

    //Getters and Setters
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
}
