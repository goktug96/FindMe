package dev.FindMe.states;

import dev.FindMe.Handler;
import dev.FindMe.UI.UIManager;

import java.awt.Graphics;

public abstract class State {

    private static State currentState = null;

    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract UIManager getUiManager();



    //Getters and Setters
    public static State getCurrentState(){
        return currentState;
    }

    public static void setCurrentState(State currentState){
        State.currentState = currentState;
    }

}
