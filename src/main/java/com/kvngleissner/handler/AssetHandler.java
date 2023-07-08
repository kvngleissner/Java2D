package com.kvngleissner.handler;

import com.kvngleissner.game.GamePanel;
import com.kvngleissner.object.OBJ_Chest;
import com.kvngleissner.object.OBJ_Door;
import com.kvngleissner.object.OBJ_Key;

public class AssetHandler {
    GamePanel gamePanel;

    public AssetHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.base[0] = new OBJ_Key();
        gamePanel.base[0].worldXPosition = 23 * gamePanel.tileSize;
        gamePanel.base[0].worldYPosition = 7 * gamePanel.tileSize;


        gamePanel.base[1] = new OBJ_Key();
        gamePanel.base[1].worldXPosition = 23 * gamePanel.tileSize;
        gamePanel.base[1].worldYPosition = 40 * gamePanel.tileSize;

        gamePanel.base[2] = new OBJ_Chest();
        gamePanel.base[2].worldXPosition = 24 * gamePanel.tileSize;
        gamePanel.base[2].worldYPosition = 40 * gamePanel.tileSize;

        gamePanel.base[3] = new OBJ_Door();
        gamePanel.base[3].worldXPosition = 7 * gamePanel.tileSize;
        gamePanel.base[3].worldYPosition = 40 * gamePanel.tileSize;
    }


}
