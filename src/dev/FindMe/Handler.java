package dev.FindMe;

import dev.FindMe.Input.KeyManager;
import dev.FindMe.Input.MouseManager;
import dev.FindMe.Tiles.World;
import dev.FindMe.graphics.gameCamera;
import dev.FindMe.states.State;

public class Handler {

    private Game game;
    private World world;
    private Launcher launcher;

    public Handler(Game game){
        this.game = game;
    }

    public gameCamera getGameCam(){
        return game.getGameCam();
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public MouseManager getMouseManager(){ return game.getMouseManager();}

    public int getWidth(){
        return game.getWidth();
    }

    public int getHeight(){
        return game.getHeight();
    }

    //Getters and Setters
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void changeState(){
        if(State.getCurrentState() == game.getGameState()){
            getMouseManager().setUiManager(game.getMenuState().getUiManager());
            State.setCurrentState(game.getMenuState());
        }else{
            getMouseManager().setUiManager(game.getGameState().getUiManager());
            State.setCurrentState(game.getGameState());
        }
    }
}
