import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;


//This class must extend JPanel so we can use paintComponent and implement MouseMotionListener to track mouse
public class PongGame extends JPanel implements KeyListener {



    //Constants for window width and height, in case we want to change the width/height later
    static final int WINDOW_WIDTH = 640, WINDOW_HEIGHT = 480, SPEED = 3, PADDLE_HEIGHT = 75, MAX = WINDOW_HEIGHT - PADDLE_HEIGHT;
    private MusicPlayer player;
    private Ball gameBall;
    private Paddle userPaddle, pcPaddle;
    private int userScore, pcScore;

    //private int newPosition; //to track the user's mouse position
    private int bounceCount; //to count number of ball bounces between paddles

    /**
     * Standard constructor for a PongGame
     */
    public PongGame() {

        //Make the ball and paddles
        gameBall = new Ball(300, 200, 3, 3, SPEED, Color.YELLOW, 10);
        userPaddle = new Paddle(10, 200, PADDLE_HEIGHT, SPEED, Color.BLUE);
        pcPaddle = new Paddle(610, 200, PADDLE_HEIGHT, SPEED, Color.RED);

        //Set instance variables to zero to start
        //newPosition = 0;
        userScore = 0; pcScore = 0;
        bounceCount = 0;
        player = new MusicPlayer();

        //listen for motion events on this object
        setFocusable(true);
        addKeyListener(this);


        //requestFocus();

    }

    /**
     * resets the game to start a new round
     */
    public void reset(){

        //pause for a second
        try{
            Thread.sleep(1000);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        gameBall.setX(300);
        gameBall.setY(200);
        gameBall.setCx(3);
        gameBall.setCy(3);
        gameBall.setSpeed(3);
        bounceCount = 0;

    }

    /**
     * Updates and draws all the graphics on the screen
     */
    public void paintComponent(Graphics g) {

        //draw the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        //draw the ball
        gameBall.paint(g);

        //draw the paddles
        userPaddle.paint(g);
        pcPaddle.paint(g);

        //update score
        g.setColor(Color.WHITE);
        //the drawString method needs a String to print, and a location to print it at.
        g.drawString("Score - User [ " + userScore + " ]   PC [ " + pcScore + " ]", 250, 20   );

    }

    /**
     * Called once each frame to handle essential game operations
     */
    public void gameLogic() {

        Thread musicPlayer = new Thread(player);
        musicPlayer.start();
        //move the ball one frame
        gameBall.moveBall();

        //edge check/bounce
        gameBall.bounceOffEdges(0, WINDOW_HEIGHT);

        //userPaddle.moveTowards(newPosition);


        //move the PC paddle towards the ball y position
        pcPaddle.moveTowards(gameBall.getY());

        //check if ball collides with either paddle
        if(pcPaddle.checkCollision(gameBall, this) || userPaddle.checkCollision(gameBall, this)){
            //reverse ball if they collide
            gameBall.reverseX();
            //increase the bounce count
            bounceCount++;
        }

        //after 5 bounces
        if (bounceCount == 5){
            //reset counter
            bounceCount = 0;
            //increase ball speed
            gameBall.increaseSpeed();
        }

        //check if someone lost
        if(gameBall.getX() < 0){
            //player has lost
            pcScore++;
            reset();
        }
        else if(gameBall.getX() > WINDOW_WIDTH){
            //pc has lost
            userScore++;
            reset();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {



        //First I have to get the keyCode in order to see What the user has just typed
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            userPaddle.setY((userPaddle.getY() <= 0) ? 0 : userPaddle.getY() - SPEED);
        }
        else if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            userPaddle.setY((userPaddle.getY() >= MAX) ? MAX : userPaddle.getY() + SPEED);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}