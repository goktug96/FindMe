package dev.FindMe.Entity;

import dev.FindMe.Handler;

import java.awt.Graphics;

public abstract class StaticEntity extends Entity{
    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }
}
