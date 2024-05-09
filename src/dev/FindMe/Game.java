package dev.FindMe;

import dev.FindMe.Input.KeyManager;
import dev.FindMe.Input.MouseManager;
import dev.FindMe.display.Display;
import dev.FindMe.gamePlay.Celebrity;
import dev.FindMe.graphics.Assets;
import dev.FindMe.states.GameState;
import dev.FindMe.states.MenuState;
import dev.FindMe.states.State;
import dev.FindMe.graphics.gameCamera;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game implements Runnable{ //make game class a thread

    private Display display;

    private int width, height;
    private String title;
    private boolean running;

    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    private State menuState;
    private GameState gameState;

    private KeyManager keyManager;
    private MouseManager mouseManager;

    private gameCamera gameCam;

    private Handler handler;

    private ArrayList <Celebrity> celebrities = new ArrayList<>();
    private Celebrity currentCelebrity;


    public Game(String title, int width, int height){
        display = new Display(title, width, height);
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        readCelebrities(celebrities);
        this.width = width;
        this.height = height;
        this.title = title;
    }
    public synchronized void start(){ //synchronized used for threads
        if(running)
            return;
        running = true;
        thread = new Thread(this); //start new thread with this game
        thread.start(); //calls run automatically
    }
    @Override
    public void run() {
        init();
        int fps = 60;
        double reqTimePerCall = (double) 1000000000 / fps;
        long passedTime;
        long currentTime;
        long previousTime = System.nanoTime();

        while (running){
            currentTime = System.nanoTime();
            passedTime = (currentTime - previousTime);
            if(passedTime >= reqTimePerCall) {
                render();
                update();
                previousTime = currentTime;
            }
        }
        stop();
    }
    public void init(){
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getCanvas().addKeyListener(keyManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        Assets.init();
        handler = new Handler(this);
        gameCam = new gameCamera(handler);
        menuState = new MenuState(handler);
        gameState = new GameState(handler);
        State.setCurrentState(menuState);
    }
    private void update(){
        keyManager.update();
        if(State.getCurrentState() != null)
            State.getCurrentState().update();
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3); //if null create bs
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0,0, width, height);

        //Drawings here
        if(State.getCurrentState() != null)
            State.getCurrentState().render(g);
        //Drawings ends

        bs.show();
        g.dispose();
    }
    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        display.getFrame().dispose();
    }

    public void readCelebrities(ArrayList <Celebrity> celebrityList) {
        Celebrity c;
        final int numOfQuestions = 12;
        try {
            BufferedReader in = new BufferedReader(new FileReader("res/Celebrities/Celebrities.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                c = new Celebrity();
                c.setName(str);
                for(int i=0; i<numOfQuestions; i++){
                    str = in.readLine();
                    String[] ar = str.split(",");
                    c.addString(ar[0], ar[1], ar[2]);
                }
                celebrityList.add(c);
            }
            in.close();
        } catch(IOException e){
            System.out.println("File Read Error");
        }
    }

    public void setCurrentCelebrity(){
        Random rand = new Random();
        int r = rand.nextInt(celebrities.size());
        currentCelebrity = celebrities.get(r);
        celebrities.remove(r);
    }

    //Getters and Setters

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public gameCamera getGameCam() {
        return gameCam;
    }

    public Display getDisplay(){return display;}

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }


    public State getMenuState() {
        return menuState;
    }

    public Celebrity getCurrentCelebrity() {
        return currentCelebrity;
    }

}
