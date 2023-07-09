package com.kvngleissner.object;

import com.kvngleissner.game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjectBase {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldXPosition, worldYPosition;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void drawObject(Graphics2D graphics2D, GamePanel panel) {
        int screenX = worldXPosition - panel.player.worldXPos + panel.player.screenX;
        int screenY = worldYPosition - panel.player.worldYPos + panel.player.screenY;
        if(worldXPosition + panel.tileSize > panel.player.worldXPos -panel.player.screenX &&
                worldXPosition - panel.tileSize < panel.player.worldXPos + panel.player.screenX &&
                worldYPosition + panel.tileSize > panel.player.worldYPos -panel.player.screenY &&
                worldXPosition - panel.tileSize < panel.player.worldYPos + panel.player.screenY) {
            graphics2D.drawImage(image, screenX,screenY, panel.tileSize, panel.tileSize, null);
        }
    }
}