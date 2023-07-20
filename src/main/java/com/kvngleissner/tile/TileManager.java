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
        tiles = new Tile[50];
        mapTileNumber = new int[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];
        getTileImage();
        loadMapData("/maps/world01.txt");

    }

    /**
     * Loads tiles from the Resources folder
     * And sets if they are solid or not
     */
    public void getTileImage() {
        // Placeholder
        setup(0, "grass00", false);
        setup(1, "grass00", false);
        setup(2, "grass00", false);
        setup(3, "grass00", false);
        setup(4, "grass00", false);
        setup(5, "grass00", false);
        setup(6, "grass00", false);
        setup(7, "grass00", false);
        setup(8, "grass00", false);
        setup(9, "grass00", false);

        setup(10, "grass00", false);
        setup(11, "grass01", false);
        setup(12, "water00", true);
        setup(13, "water01", true);
        setup(14, "water02", true);
        setup(15, "water03", true);
        setup(16, "water04", true);
        setup(17, "water05", true);
        setup(18, "water06", true);
        setup(19, "water07", true);
        setup(20, "water08", true);
        setup(21, "water09", true);
        setup(22, "water10", true);
        setup(23, "water11", true);
        setup(24, "water12", true);
        setup(25, "water13", true);
        setup(26, "road00", false);
        setup(27, "road01", false);
        setup(28, "road02", false);
        setup(29, "road03", false);
        setup(30, "road04", false);
        setup(31, "road05", false);
        setup(32, "road06", false);
        setup(33, "road07", false);
        setup(34, "road08", false);
        setup(35, "road09", false);
        setup(36, "road10", false);
        setup(37, "road11", false);
        setup(38, "road12", false);
        setup(39, "earth", false);
        setup(40, "wall", true);
        setup(41, "tree", true);
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
