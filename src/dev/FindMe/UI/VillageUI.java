package dev.FindMe.UI;

import dev.FindMe.Handler;
import dev.FindMe.Tiles.Tile;
import dev.FindMe.gamePlay.Item;
import dev.FindMe.gamePlay.Questions;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class VillageUI extends UIObject {

    private String priority;
    private float distance;
    private ArrayList<String> vQuestions = new ArrayList<>();
    private ArrayList<String> vAnswers = new ArrayList <>();
    private Questions villageQuestions;

    public VillageUI(Handler handler, float x, float y, String priority, Item requested, Item given){
        super(handler, x, y, Tile.TILEWIDTH*2, Tile.TILEHEIGHT*2);
        this.priority = priority;
        setQandAns();
        villageQuestions = new Questions(handler, this.vQuestions, this.vAnswers, requested, given, priority);
    }

    public void setQandAns(){
        for(String [] s : handler.getGame().getCurrentCelebrity().getQAList()){
            if(s[2].equals(priority)) {
                vQuestions.add(s[0]);
                vAnswers.add(s[1]);
                /*handler.getGame().getCurrentCelebrity().getQAList().remove(s);
                return;*/
            }
        }
    }

    @Override
    public void update() {
        float playerX = handler.getGame().getGameState().getWorld().getEntityManager().getPlayer().getX();
        float playerY = handler.getGame().getGameState().getWorld().getEntityManager().getPlayer().getY();
        distance = (float) Math.sqrt((playerY-y)*(playerY-y) + (playerX-x)*(playerX-x));
        villageQuestions.update();
    }

    @Override
    public void render(Graphics g) {

    }

    public void onClick() {
        if(villageQuestions.getAnswer().isAlreadyAsked())
            return;
        if (distance < 100) {
            villageQuestions.setActive(true);
            handler.getGame().getGameState().getWorld().getEntityManager().getPlayer().setLocked(true);
        }
    }

    @Override
    public void onMouseMove(MouseEvent e) {
        super.onMouseMove(e);
    }

    @Override
    public void onMouseRelease(MouseEvent e) {
        super.onMouseRelease(e);
    }


    public Questions getVillageQuestions() {
        return villageQuestions;
    }
}
