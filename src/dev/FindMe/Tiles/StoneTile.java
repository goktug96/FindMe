package dev.FindMe.Tiles;

import dev.FindMe.graphics.Assets;

import java.awt.Graphics;

public class StoneTile extends Tile {

    public StoneTile(int id) {
        super(Assets.stoneTile, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Graphics g, int x, int y) {
        super.render(g, x, y);
    }
}
