package com.kvngleissner.entity;

import com.kvngleissner.game.GamePanel;
import com.kvngleissner.handler.KeyHandler;
import com.kvngleissner.handler.UtilityHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler handler;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    public Player(GamePanel gamePanel, KeyHandler handler) {
        this.gamePanel = gamePanel;
        this.handler = handler;
        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);
        setDefaultValues();
        getPlayerImage();

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
    }


    /**
     * Gets the Player Sprites from the resources folder
     */
    public void getPlayerImage() {
        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");
    }

    public void setDefaultValues() {
        worldXPos = gamePanel.tileSize * 23;
        worldYPos = gamePanel.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    /**
     * Handles player movement and sets direction for the Sprite Animation
     */
    public void update() {
        // Checks for player input before updating player sprite

        if(handler.upKeyPressed || handler.rightKeyPressed || handler.leftKeyPressed || handler.downKeyPressed) {
            if(handler.upKeyPressed == true) {
                direction = "up";
            } else if (handler.downKeyPressed == true) {
                direction = "down";
            } else if(handler.rightKeyPressed  == true) {
                direction = "right";
            } else if (handler.leftKeyPressed) {
                direction = "left";
            }
            collisionOn = false;
            gamePanel.collisionHandler.checkTileCollision(this);
            int objectIndex = gamePanel.collisionHandler.checkObjectCollison(this, true);
            pickUpObject(objectIndex);
            updateSprite(15);

            if(!collisionOn) {
                switch (direction) {
                    case "up":
                    worldYPos -= speed;
                    break;
                    case "down":
                        worldYPos += speed;
                        break;
                    case "left":
                        worldXPos -= speed;
                        break;
                    case "right":
                        worldXPos += speed;
                        break;
                }
            }
        }
    }

    /**
     * Draws individual Sprites and Switches between them for Animation
     * @param graphics2D
     */
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
        graphics2D.drawImage(image, screenX,screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public void pickUpObject(int index) {
        if(index != 999) {
            String objectName = gamePanel.base[index].name;

            switch (objectName) {
                case "Key":
                    gamePanel.playSoundEffect(1);
                    hasKey++;
                    gamePanel.base[index] = null;
                    break;
                case "Door":
                    if(hasKey > 0) {
                        gamePanel.base[index] = null;
                        hasKey--;
                    }
            }
        }
    }

    /**
     * Updates the Sprite every 15 frames currently
     * @param refresh sets how many frames it takes for the sprite to get changed
     */
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

    public BufferedImage setup(String imageName) {
        UtilityHandler handler = new UtilityHandler();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
            handler.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return  image;
    }
}
