package com.kvngleissner.tile;

import com.kvngleissner.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;
    int mapTileNumber[][];

    public TileManager(GamePanel panel) {
        this.gamePanel = panel;
        tiles = new Tile[10];
        mapTileNumber = new int[gamePanel.maxScreenColumn][gamePanel.maxScreenRow];
        getTileImage();
        loadMapData();

    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));


            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public void draw(Graphics2D graphics2D) {
        int column = 0;
        int row = 0;
        int xPosition = 0;
        int yPosition = 0;
        while (column < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow) {
            int tileNumber = mapTileNumber[column][row];
          graphics2D.drawImage(tiles[tileNumber].image, xPosition, yPosition, gamePanel.tileSize, gamePanel.tileSize, null);
          column++;
          xPosition += gamePanel.tileSize;
          if(column == gamePanel.maxScreenColumn) {
              column = 0;
              xPosition = 0;
              row++;
              yPosition += gamePanel.tileSize;
          }
        }
    }

    public void loadMapData() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/maps/map.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            int column = 0;
            int row = 0;
            while (column < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow) {
                String line = reader.readLine();
                while (column < gamePanel.maxScreenColumn) {
                    String numbers[] = line.split(" ");
                    int number = Integer.parseInt(numbers[column]);
                    mapTileNumber[column][row] = number;
                    column++;
                }
                if(column == gamePanel.maxScreenColumn) {
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
