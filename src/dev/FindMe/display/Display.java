package dev.FindMe.display;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Color;

public class Display {

    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    public Display(String title, int width, int height){ //constructor
        this.title = title;
        this.width = width;
        this.height = height;
        initDisplay();
    }

    private void initDisplay(){  //initialize display
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        canvas = new Canvas();   //to draw objects to the frame
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setBackground(Color.red);
        canvas.setFocusable(true);

        frame.add(canvas);
        frame.pack();

        frame.setVisible(true);
    }


    //Getters and Setter
    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
