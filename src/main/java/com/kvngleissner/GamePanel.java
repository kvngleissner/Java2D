package com.kvngleissner;

import com.kvngleissner.entity.Player;
import com.kvngleissner.handler.KeyHandler;
import com.kvngleissner.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16 x 16 Pixels
    final int scale = 3;
    final int FPS = 60;

    public final int tileSize = originalTileSize * scale; // 64 x 64 tile
    public final int maxScreenColumn = 24;
    public final int maxScreenRow = 18;

    public final int screenWidth = tileSize * maxScreenColumn; // 1152 Pixels
    public final int screenHeight = tileSize * maxScreenRow; // 768 Pixls

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyHandler);
    TileManager tileManager = new TileManager(this);

    // WORLD SETTINGS
    public final int maxWorldColumn = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldColumn;
    public final int worldHeight = tileSize * maxWorldRow;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLUE);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        // Passing Gamepanel in the Constructor
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Updates the Game with the delta Time
     */
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lasttime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lasttime) /drawInterval;
            lasttime = currentTime;
            if(delta >= 1) {
                // UPDATE : updating information such a character pos
                update();
                // DRAW : Drawing the screen with updated Information
                repaint();
                delta--;
            }
        }
    }

    /**
     * Calls player Update
     */
    public void update() {
        player.update();
    }

    /**
     * Draws the Player and Tiles
     * @param graphics the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        tileManager.draw(graphics2D);
        player.draw(graphics2D);
        graphics2D.dispose();
    }
}
