package com.kvngleissner.collision;

import com.kvngleissner.entity.Entity;
import com.kvngleissner.game.GamePanel;

public class CollisionHandler {
    GamePanel panel;
    public CollisionHandler(GamePanel gamePanel) {
        this.panel = gamePanel;
    }

    /**
     * Checks for Collisionsbetween entities in each direction
     * @param entity
     */
    public void checkTileCollision(Entity entity) {
        int entityLeftWorldX = entity.worldXPos + entity.solidArea.x;
        int entityRightWorldX = entity.worldXPos + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = (int) (entity.worldYPos + entity.solidArea.y);
        int entityBottomWorldY = (int) (entity.worldYPos + entity.solidArea.y + entity.solidArea.height);

        int entityLeftCol = (entityLeftWorldX / panel.tileSize);
        int entityRightCol =  (entityRightWorldX / panel.tileSize);
        int entityTopRow = entityTopWorldY / panel.tileSize;
        int entityBottomRow = entityBottomWorldY / panel.tileSize;

        int tileNumber1, tileNumber2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / panel.tileSize;
                tileNumber1 = panel.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                tileNumber2 = panel.tileManager.mapTileNumber[entityRightCol][entityTopRow];
                if(panel.tileManager.tiles[tileNumber1].collision || panel.tileManager.tiles[tileNumber2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / panel.tileSize;
                tileNumber1 = panel.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
                tileNumber2 = panel.tileManager.mapTileNumber[entityRightCol][entityBottomRow];
                if(panel.tileManager.tiles[tileNumber1].collision || panel.tileManager.tiles[tileNumber2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (int) ((entityLeftWorldX - entity.speed) / panel.tileSize);
                tileNumber1 = panel.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                tileNumber2 = panel.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
                if(panel.tileManager.tiles[tileNumber1].collision || panel.tileManager.tiles[tileNumber2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (int) ((entityRightWorldX + entity.speed) / panel.tileSize);
                tileNumber1 = panel.tileManager.mapTileNumber[entityRightCol][entityTopRow];
                tileNumber2 = panel.tileManager.mapTileNumber[entityRightCol][entityBottomRow];
                if(panel.tileManager.tiles[tileNumber1].collision || panel.tileManager.tiles[tileNumber2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
