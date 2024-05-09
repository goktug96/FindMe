package dev.FindMe.Tiles;

import dev.FindMe.Entity.*;
import dev.FindMe.Handler;
import dev.FindMe.Input.Utils;

import java.awt.Graphics;

public class World {

    private int width, height, spawnX, spawnY;
    private int[][] tiles;
    private Handler handler;
    private EntityManager entityManager;


    public World(Handler handler, String path){

        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));
        entityManager.addEntity(new VillageEntity(handler, 0, 960));
        entityManager.addEntity(new VillageEntity(handler, 0, 1152));
        entityManager.addEntity(new VillageEntity(handler, 192, 192));
        entityManager.addEntity(new VillageEntity(handler, 384, 448));
        entityManager.addEntity(new VillageEntity(handler, 576, 256));
        entityManager.addEntity(new VillageEntity(handler, 576, 1024));
        entityManager.addEntity(new VillageEntity(handler, 640, 704));
        entityManager.addEntity(new VillageEntity(handler, 832, 0));
        entityManager.addEntity(new VillageEntity(handler, 832, 448));
        entityManager.addEntity(new VillageEntity(handler, 1152, 64));
        entityManager.addEntity(new VillageEntity(handler, 1152, 576));
        //entityManager.addEntity(new Tree(handler, 190, 350));

        loadWorld(path);
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    public void update(){
        entityManager.update();
    }

    public void render(Graphics g){
        int xStart, xEnd, yStart, yEnd;
        xStart = (int)Math.max(0, handler.getGameCam().getxOffset() / Tile.TILEWIDTH);
        xEnd = (int)Math.min(width, (handler.getGameCam().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        yStart = (int)Math.max(0, handler.getGameCam().getyOffset() / Tile.TILEHEIGHT);
        yEnd = (int)Math.min(width, (handler.getGameCam().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for(int y = yStart; y < yEnd; y++){
            for(int x = xStart; x < xEnd; x++)
                getTile(x, y).render(g, (int) (x*Tile.TILEWIDTH - handler.getGameCam().getxOffset()),
                        (int) (y*Tile.TILEHEIGHT - handler.getGameCam().getyOffset()));
        }

        entityManager.render(g);
    }

    public Tile getTile(int x, int y){
        if(x<0 || y<0 || x>=width || y>=height)
            return Tile.grassTile;
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
            return Tile.soilTile;
        return t;
    }

    private void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        String [] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for(int y=0; y<height ; y++){
            for(int x=0; x<width; x++){
                tiles[x][y] = Utils.parseInt(tokens[(x + y*width) + 4]);
            }
        }
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
