package dev.FindMe.states;

import dev.FindMe.Handler;
import dev.FindMe.Tiles.World;
import dev.FindMe.UI.*;
import dev.FindMe.gamePlay.Celebrity;
import dev.FindMe.gamePlay.Inventory;
import dev.FindMe.gamePlay.Item;
import dev.FindMe.gamePlay.Timer;
import dev.FindMe.graphics.Assets;

import java.awt.Graphics;
import java.util.Random;


public class GameState extends State{

    private World world;
    private UIManager uiManager;
    private Inventory inventory;
    private Timer timer;
    private MenuButton menuButton;
    private ExitButton exitButton;
    private int askedQuestions = 0;

    public GameState(Handler handler){
        super(handler);
        handler.getGame().setCurrentCelebrity();
        int [][] positions = {{832, 0}, {1152, 64}, {1152, 576}, {832, 448}, {640,704},
                {0, 960}, {0,1152}, {192, 192}, {576,256}, {576, 1024}, {348, 448}};
        int [] temp = {0, 1, 2, 3, 4, 5 ,6 ,7, 8, 9, 10};
        shuffleArray(temp);
        inventory = new Inventory(handler);
        world = new World(handler,"res/World/world1.txt");
        handler.setWorld(world);
        uiManager = new UIManager(handler);
        timer = new Timer(handler);
        menuButton = new MenuButton(handler, (float) 375 , (float) 250, 100, 40, Assets.orangeButton);
        exitButton = new ExitButton(handler, (float) 375 , (float) 300, 100, 40, Assets.orangeButton);

        if(handler.getMouseManager().getUiManager() == null) {
            handler.getMouseManager().setUiManager(uiManager);
        }

        uiManager.addObject(new VillageUI(handler, (float)positions[temp[0]][0] , (float)positions[temp[0]][1], "1", null, Item.strawberryItem));
        uiManager.addObject(new VillageUI(handler, (float)positions[temp[1]][0] , (float)positions[temp[1]][1], "1", null, Item.strawberryItem));
        uiManager.addObject(new VillageUI(handler, (float)positions[temp[2]][0] , (float)positions[temp[2]][1], "2", Item.strawberryItem, Item.cherryItem));
        uiManager.addObject(new VillageUI(handler, (float)positions[temp[3]][0] , (float)positions[temp[3]][1], "2", Item.strawberryItem, Item.cherryItem));
        uiManager.addObject(new VillageUI(handler, (float)positions[temp[4]][0] , (float)positions[temp[4]][1], "3",  Item.cherryItem, Item.bananaItem));
        uiManager.addObject(new VillageUI(handler, (float)positions[temp[5]][0] , (float)positions[temp[5]][1], "3", Item.cherryItem, Item.bananaItem));
        uiManager.addObject(new VillageUI(handler, (float)positions[temp[6]][0] , (float)positions[temp[6]][1], "4", Item.bananaItem, Item.goldItem));
        uiManager.addObject(new VillageUI(handler, (float)positions[temp[7]][0] , (float)positions[temp[7]][1], "4", Item.bananaItem, Item.goldItem));
        uiManager.addObject(new VillageUI(handler, (float)positions[temp[8]][0] , (float)positions[temp[8]][1], "5", Item.goldItem, Item.diamondItem));
        uiManager.addObject(new VillageUI(handler, (float)positions[temp[9]][0] , (float)positions[temp[9]][1], "5", Item.goldItem, Item.diamondItem));
        uiManager.addObject(new VillageUI(handler, (float)positions[temp[10]][0] , (float)positions[temp[10]][1], "10", Item.diamondItem, null));

        uiManager.addObject(new GuessButton(handler, (float) 700 , (float) 15, 100, 40, Assets.orangeButton));
        uiManager.addObject(menuButton);
        uiManager.addObject(exitButton);
    }

    @Override
    public void update() {
        world.update();
        uiManager.update();
        inventory.update();
        timer.update();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        uiManager.render(g);
        inventory.render(g);
        timer.render(g);
        if(!timer.isActive()){
            timer.setPreviousTime(System.currentTimeMillis());
            timer.setActive(true);
        }
    }

    public void increaseQNumber(){
        askedQuestions++;
    }

    private void shuffleArray(int[] array) {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public Timer getTimer(){
        return timer;
    }

    public UIManager getUiManager() {
        return uiManager;
    }

    public World getWorld() {
        return world;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getAskedQuestions() {
        return askedQuestions;
    }

    public MenuButton getMenuButton() {
        return menuButton;
    }

    public ExitButton getExitButton() {
        return exitButton;
    }
}
