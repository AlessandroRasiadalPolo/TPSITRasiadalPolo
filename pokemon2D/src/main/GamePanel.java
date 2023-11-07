package main;

import Entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Screen settings
    final int ORIGINALTILESIZE = 16; //16X16 tile
    final int SCALE = 3;
    public final int TILESIZE = ORIGINALTILESIZE * SCALE; //48x48 tile
    final int MAXSCREENCOL = 16;
    final int MAXSCREENROW = 12;
    final int SCREENWIDTH = TILESIZE * MAXSCREENCOL; //768 pixels
    final int SCREENHEIGHT = TILESIZE * MAXSCREENROW; //576 pixels

    //FPS
    int FPS = 60;

    Thread gameThread; //we need to keep the game executing
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);

    //Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel(){
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); //improve game rendering
        this.addKeyListener(keyH);
        this.setFocusable(true); //It can receive key input
    }

    public void startGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / FPS; //1billion nanoseconds 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;


        //Let's make a game loop core

        while(gameThread != null){

            //First I need to update information such as character position
            update();


            //Then I need to draw the screen with the updated information
            repaint(); //It keeps calling paintComponent

            try {
                double remainingTime = nextDrawTime - System.nanoTime(); //How much time is remaining?
                remainingTime = remainingTime / 1000000;

                if(remainingTime < 0)
                    remainingTime = 0;

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){
       player.update();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; //We change the graphic to 2D which has more functions

        player.draw(g2);
        g2.dispose();
    }
}
