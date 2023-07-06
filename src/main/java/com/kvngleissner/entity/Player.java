package com.kvngleissner.entity;

import com.kvngleissner.GamePanel;
import com.kvngleissner.handler.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler handler;

    public Player(GamePanel gamePanel, KeyHandler handler) {
        this.gamePanel = gamePanel;
        this.handler = handler;
        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void update() {
        // Checks for player input before updating player sprite
        if(handler.upKeyPressed || handler.rightKeyPressed || handler.leftKeyPressed || handler.downKeyPressed) {
            if(handler.upKeyPressed == true) {
                direction = "up";
                y -= speed;
            } else if (handler.downKeyPressed == true) {
                direction = "down";
                y += speed;
            } else if(handler.rightKeyPressed  == true) {
                direction = "right";
                x += speed;
            } else if (handler.leftKeyPressed) {
                direction = "left";
                x -= speed;
            }
            updateSprite(15);
        }
    }
    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(spriteNumber == 1) {
                    image = up1;
                }
                if(spriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNumber == 1) {
                    image = down1;
                }
                if(spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNumber == 1) {
                    image = left1;
                }
                if(spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNumber == 1) {
                    image = right1;
                }
                if(spriteNumber == 2) {
                    image = right2;
                }
                break;
        }
        graphics2D.drawImage(image, x,y, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public void updateSprite(int refresh) {
        // Updates the Sprite counter to change the player Image every 15 Frames
        spriteCounter++;
        if (spriteCounter > refresh) {
            if(spriteNumber == 1) {
                spriteNumber = 2;
            } else if(spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }
}
