package com.kvngleissner.tile;

import com.kvngleissner.game.GamePanel;
import com.kvngleissner.handler.UtilityHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tiles;
    public int mapTileNumber[][];

    public TileManager(GamePanel panel) {
        this.gamePanel = panel;
        tiles = new Tile[10];
        mapTileNumber = new int[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];
        getTileImage();
        loadMapData("/maps/world01.txt");

    }

    /**
     * Loads tiles from the Resources folder
     * And sets if they are solid or not
     */
    public void getTileImage() {
        setup(0, "grass", false);
        setup(1, "wall", true);
        setup(2, "water", true);
        setup(3, "earth", false);
        setup(4, "tree", true);
        setup(5, "sand", false);
    }

    /**
     * Draws the World Map dynamically inside the Camera Bounds
     * @param graphics2D
     */
    public void draw(Graphics2D graphics2D) {
        int worldColumn = 0;
        int worldRow = 0;

        while (worldColumn < gamePanel.maxWorldColumn && worldRow < gamePanel.maxWorldRow) {
            int tileNumber = mapTileNumber[worldColumn][worldRow];
            int worldX = worldColumn * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            double screenX = worldX - gamePanel.player.worldXPos + gamePanel.player.screenX;
            double screenY = worldY - gamePanel.player.worldYPos + gamePanel.player.screenY;
            if(worldX + gamePanel.tileSize > gamePanel.player.worldXPos -gamePanel.player.screenX &&
                    worldX - gamePanel.tileSize < gamePanel.player.worldXPos + gamePanel.player.screenX &&
                    worldY + gamePanel.tileSize > gamePanel.player.worldYPos -gamePanel.player.screenY &&
                    worldY - gamePanel.tileSize < gamePanel.player.worldYPos + gamePanel.player.screenY) {
                graphics2D.drawImage(tiles[tileNumber].image,(int) screenX,(int) screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }
            worldColumn++;
            if(worldColumn == gamePanel.maxWorldColumn) {
                worldColumn = 0;
                worldRow++;
            }
        }
    }


    /**
     * Loads the Map Data from a Text file and readys it for Drawing
     * @param path
     */
    public void loadMapData(String path) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            int column = 0;
            int row = 0;
            while (column < gamePanel.maxWorldColumn && row < gamePanel.maxWorldRow) {
                String line = reader.readLine();
                while (column < gamePanel.maxWorldColumn) {
                    String numbers[] = line.split(" ");
                    int number = Integer.parseInt(numbers[column]);
                    mapTileNumber[column][row] = number;
                    column++;
                }
                if(column == gamePanel.maxWorldColumn) {
                    row++;
                    column = 0;
                }
            }
            reader.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void setup(int index, String path, boolean collision) {
        UtilityHandler utilityHandler = new UtilityHandler();
        try {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + path + ".png"));
            tiles[index].image = utilityHandler.scaleImage(tiles[index].image, gamePanel.tileSize, gamePanel.tileSize);
            tiles[index].collision = collision;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
