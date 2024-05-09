package dev.FindMe.Input;

import dev.FindMe.UI.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

    private UIManager uiManager;

    public void setUiManager(UIManager uiManager){
        this.uiManager = uiManager;
    }

    public UIManager getUiManager(){
        return uiManager;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if(uiManager!=null)
            uiManager.onMouseRelease(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        if(uiManager!=null)
            uiManager.onMouseMove(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }


}
