package Entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{

            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/SpriteUpMove1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/SpriteUpMove2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/SpriteDownMove1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/SpriteDownMove2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/SpriteLeftMove1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/SpriteLeftMove2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/SpriteRightMove1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/SpriteRightMove2.png")));

            stayUp = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/SpriteStayUp.png")));
            stayDown = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/SpriteStayDown.png")));
            stayRight = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/SpriteStayRight.png")));
            stayLeft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/SpriteStayLeft.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed) {
            if (keyH.upPressed) {
                direction = "up";
                y -= speed;
            } else if (keyH.downPressed) {
                direction = "down";
                y += speed;
            } else if (keyH.leftPressed) {
                direction = "left";
                x -= speed;
            } else if (keyH.rightPressed) {
                direction = "right";
                x += speed;
            }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1)
                spriteNum = 2;
            else if (spriteNum == 2)
                spriteNum = 1;
            spriteCounter = 0;
        }
        }
        else {
            if(direction.equals("up"))
                direction = "stayUp";
            else if(direction.equals("down"))
                direction = "stayDown";
            else if(direction.equals("left"))
                direction = "stayLeft";
            else if(direction.equals("right"))
                direction = "stayRight";
        }

    }
    public void draw( Graphics2D g2){
        //g2.setColor(Color.WHITE);
        //g2.fillRect(x, y, gp.TILESIZE, gp.TILESIZE);

        BufferedImage image = null;
        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2)
                    image = down2;
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2)
                    image = left2;
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2)
                    image = right2;
                break;
            case "stayUp":
                image = stayUp;
                break;
            case "stayDown":
                image = stayDown;
                break;
            case "stayLeft":
                image = stayLeft;
                break;
            case "stayRight":
                image = stayRight;
                break;

        }

        g2.drawImage(image, x, y, gp.TILESIZE, gp.TILESIZE, null);

    }
}
