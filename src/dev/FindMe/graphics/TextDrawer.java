package dev.FindMe.graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.FontMetrics;

public class TextDrawer {

    public static void drawText(Graphics g, String text, int x, int y, boolean center, Color c, Font f){
        g.setColor(c);
        g.setFont(f);
        int xPos = x;
        int yPos = y;
        if(center){
            FontMetrics fm = g.getFontMetrics(f);
            xPos = x - fm.stringWidth(text) / 2;
            yPos = (y - fm.getHeight() / 2) + fm.getAscent();
        }
        g.drawString(text, xPos, yPos);
    }

}
