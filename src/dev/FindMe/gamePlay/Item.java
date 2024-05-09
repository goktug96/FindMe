package dev.FindMe.gamePlay;

import dev.FindMe.Handler;
import dev.FindMe.graphics.Assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Item {


    public static Item strawberryItem = new Item(Assets.strawberry, "Strawberry", 0);
    public static Item cherryItem = new Item(Assets.cherry, "Cherry", 1);
    public static Item bananaItem = new Item(Assets.banana, "Banana", 2);
    public static Item goldItem = new Item(Assets.gold, "Gold", 3);
    public static Item diamondItem = new Item(Assets.diamond, "Diamond", 4);

    public static final int WIDTH = 45, HEIGHT = 45;

    private Handler handler;
    private BufferedImage image;
    private String name;
    private final int id;
    private int x, y;

    public Item(BufferedImage image, String name, int id){
        this.image = image;
        this.name = name;
        this.id = id;
    }

    public void update(){}

    public void render(Graphics g, int x, int y){
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }



    //Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
