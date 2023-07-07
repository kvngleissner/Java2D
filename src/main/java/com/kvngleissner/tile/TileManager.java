package com.kvngleissner.tile;

import com.kvngleissner.game.GamePanel;

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
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tiles[2].collision = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tiles[4].collision = true;

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
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
            int screenX = worldX - gamePanel.player.worldXPos + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldYPos + gamePanel.player.screenY;
            if(worldX + gamePanel.tileSize > gamePanel.player.worldXPos -gamePanel.player.screenX &&
                    worldX - gamePanel.tileSize < gamePanel.player.worldXPos + gamePanel.player.screenX &&
                    worldY + gamePanel.tileSize > gamePanel.player.worldYPos -gamePanel.player.screenY &&
                    worldY - gamePanel.tileSize < gamePanel.player.worldYPos + gamePanel.player.screenY) {
                graphics2D.drawImage(tiles[tileNumber].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
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
}
