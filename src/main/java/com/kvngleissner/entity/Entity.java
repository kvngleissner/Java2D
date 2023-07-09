package com.kvngleissner.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldXPos, worldYPos;
    public int speed;

    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNumber = 1;

    public Rectangle solidArea;
    public boolean collisionOn = false;
    public int solidAreaDefaultX, solidAreaDefaultY;
}
