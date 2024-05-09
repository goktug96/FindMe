package dev.FindMe.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean [] keys, pressed, notPressable;
    public boolean up, down, left, right, delete, enter;
    private char pressedChar;

    public KeyManager(){
        keys = new boolean[256];
        pressed = new boolean[keys.length];
        notPressable = new boolean[keys.length];
    }

    public void update(){
        for(int i = 0; i<keys.length; i++){
            if(notPressable[i] && !keys[i]){
                notPressable[i] = false;
            }else if(pressed[i]){
                notPressable[i] = true;
                pressed[i] = false;
            }
            if(!notPressable[i] && keys[i]){
                pressed[i] = true;
            }
        }

        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        delete = keys[KeyEvent.VK_BACK_SPACE];
        enter = keys[KeyEvent.VK_ENTER];
    }

    public boolean keyJustPressed(int keyCode){
        if(keyCode < 0 || keyCode >= keys.length)
            return false;
        return pressed[keyCode];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        pressedChar = e.getKeyChar();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public char getPressedChar(){
        char temp = pressedChar;
        pressedChar = (char) 0;
        return temp;
    }
}
