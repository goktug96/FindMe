package dev.FindMe.gamePlay;

import dev.FindMe.Handler;
import dev.FindMe.graphics.Assets;
import dev.FindMe.graphics.TextDrawer;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Questions {
    private int xPos, yPos, width, height;
    private int questionFirstPosX, questionFirstPosY, questionListWidth;
    private Handler handler;
    private boolean active, reqGranted = false, itemNotExist = false;
    private Item requested;
    private ArrayList<String> questions;
    private ArrayList<String> answers;
    private int selectedQuestion = 0, numofRequest;
    private Answer answer;

    public Questions(Handler handler, ArrayList<String> questions, ArrayList<String> answers, Item requested, Item granted, String priority){
        this.handler = handler;
        this.questions = questions;
        this.answers = answers;
        this.requested = requested;
        width = 500;
        height = 300;
        xPos = handler.getGame().getWidth() / 2 - width/2;
        yPos = handler.getGame().getWidth() / 2 - height;
        questionFirstPosX = xPos + 250;
        questionFirstPosY = yPos + 160;
        questionListWidth = 70;
        numofRequest = 1;
        if(priority.equals("10"))
            numofRequest = 2;
        answer = new Answer(handler, "", granted);
    }

    public void update() {

        if (answer.isActive()) {
            answer.update();
        }
        if (!active)
            return;

        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
            selectedQuestion++;
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
            selectedQuestion--;

        if (selectedQuestion < 0)
            selectedQuestion = 0;
        if (selectedQuestion > questions.size())
            selectedQuestion = questions.size() - 1;

        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)){
            active = false;
            handler.getGame().getGameState().getWorld().getEntityManager().getPlayer().setLocked(false);
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && itemNotExist){
            itemNotExist = false;
            active = false;
            handler.getGame().getGameState().getWorld().getEntityManager().getPlayer().setLocked(false);
        }
        else if(!reqGranted && requested!=null && handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
            Inventory inventory = handler.getGame().getGameState().getInventory();
            if(inventory.haveItem(requested)){
                inventory.deleteItem(requested);
                numofRequest--;
                if(numofRequest == 0)
                    reqGranted = true;
            }else{
                itemNotExist = true;
            }
        }
        else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
            answer.setAnswer(answers.get(selectedQuestion));
            active = false;
            reqGranted = false;
            answer.setActive(true);
        }
    }

    public void render(Graphics g){

        if(answer.isActive()){
            answer.render(g);
        }
        if(!active)
            return;

        if(!reqGranted && requested!=null){
            g.drawImage(Assets.rect, xPos, yPos, width, height, null);
            requested.render(g, xPos + width/2 - 15, yPos + 10);
            TextDrawer.drawText(g, "Soru sormak icin bize " + numofRequest + " adet "+ requested.getName() + " getirmelisin.", 400, 195, true, Color.WHITE, Assets.pixelFont8);
            TextDrawer.drawText(g, "> " + requested.getName() + " ver <", 400, 250, true, Color.YELLOW, Assets.pixelFont8);
            TextDrawer.drawText(g, "Press ESC to close.", 400, 375, true, Color.RED, Assets.pixelFont6);
            if(itemNotExist)
                TextDrawer.drawText(g, "You don't have that item.", 400, 275, true, Color.RED, Assets.pixelFont6);
        }
        else {
            g.drawImage(Assets.questionScreen, xPos, yPos, width, height, null);
            TextDrawer.drawText(g, "Ne ogrenmek istiyorsun", 400, 165, true, Color.WHITE, Assets.pixelFont8);
            int i = 0;
            for (String s : questions) {
                if (i == selectedQuestion) {
                    s = ">" + s + "<";
                    TextDrawer.drawText(g, s, questionFirstPosX, questionFirstPosY + i * questionListWidth, true, Color.YELLOW, Assets.pixelFont8);
                } else {
                    TextDrawer.drawText(g, s, questionFirstPosX, questionFirstPosY + i * questionListWidth, true, Color.WHITE, Assets.pixelFont8);
                }
                i++;
            }
        }
    }


    public Handler getHandler() {
        return handler;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Answer getAnswer() {
        return answer;
    }
}
