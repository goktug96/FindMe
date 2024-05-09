package dev.FindMe.Entity;

import dev.FindMe.Handler;
import dev.FindMe.Tiles.Tile;
import dev.FindMe.graphics.Assets;

import java.awt.*;

public class Tree extends StaticEntity {
    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH*2, Tile.TILEHEIGHT*2);
        bounds.x = 58;
        bounds.y = 110;
        bounds.width = 25;
        bounds.height = 15;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int) (x - handler.getGameCam().getxOffset()),
                (int) (y - handler.getGameCam().getyOffset()), width, height, null);
        g.setColor(Color.RED);
        /*g.fillRect((int) (x + bounds.x - handler.getGameCam().getxOffset()),
                (int) (y + bounds.y - handler.getGameCam().getyOffset()), bounds.width, bounds.height);*/
    }
}
