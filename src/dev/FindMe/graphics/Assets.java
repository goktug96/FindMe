package dev.FindMe.graphics;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage tree, village, grassTile, stoneTile, soilTile, inventory,
            questionScreen, answerScreen, orangeButton, rect, strawberry, cherry, banana, gold, diamond;
    public static BufferedImage [] player_down, player_up, player_right, player_left, player_stands;
    public static BufferedImage start_btn;
    public static Cursor handCursor;
    public static Cursor defCursor;

    public static Font pixelFont6, pixelFont8;

    public static void init(){

        handCursor = new Cursor(Cursor.HAND_CURSOR);
        defCursor = new Cursor(Cursor.DEFAULT_CURSOR);

        player_down = new BufferedImage[2];
        player_up = new BufferedImage[2];
        player_right = new BufferedImage[3];
        player_left = new BufferedImage[3];
        player_stands = new BufferedImage[1];

        player_down[0] = ImageLoader.loadImage("res/Walking/new/front1.png");
        player_down[1] = ImageLoader.loadImage("res/Walking/new/front3.png");
        player_up[0] = ImageLoader.loadImage("res/Walking/new/back1.png");
        player_up[1] = ImageLoader.loadImage("res/Walking/new/back3.png");
        player_right[0] = ImageLoader.loadImage("res/Walking/new/right1.png");
        player_right[1] = ImageLoader.loadImage("res/Walking/new/right2.png");
        player_right[2] = ImageLoader.loadImage("res/Walking/new/right3.png");
        player_left[0] = ImageLoader.loadImage("res/Walking/new/left1.png");
        player_left[1] = ImageLoader.loadImage("res/Walking/new/left2.png");
        player_left[2] = ImageLoader.loadImage("res/Walking/new/left3.png");
        player_stands[0] = ImageLoader.loadImage("res/Walking/new/front2.png");

        tree = ImageLoader.loadImage("res/tree2.png");
        village = ImageLoader.loadImage("res/village2.png");
        grassTile = ImageLoader.loadImage("res/grass.png");
        stoneTile = ImageLoader.loadImage("res/stone.png");
        soilTile = ImageLoader.loadImage("res/soil.png");

        inventory = ImageLoader.loadImage("res/inv.png");
        strawberry = ImageLoader.loadImage("res/items/strawberry.png");
        cherry = ImageLoader.loadImage("res/items/cherry.png");
        banana = ImageLoader.loadImage("res/items/banana.png");
        gold = ImageLoader.loadImage("res/items/gold.png");
        diamond = ImageLoader.loadImage("res/items/diamond.png");

        questionScreen = ImageLoader.loadImage("res/QuestionArea.png");
        answerScreen = ImageLoader.loadImage("res/AnswerArea.png");
        rect = ImageLoader.loadImage("res/rect.png");

        start_btn = ImageLoader.loadImage("res/start.png");
        orangeButton = ImageLoader.loadImage("res/OrangeButton.png");

        pixelFont8 = FontLoader.loadFont("res/font/ARCADE_N.TTF", 8);
        pixelFont6 = FontLoader.loadFont("res/font/ARCADE_N.TTF", 6);
    }
}
