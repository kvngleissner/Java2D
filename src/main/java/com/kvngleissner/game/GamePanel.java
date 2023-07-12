package com.kvngleissner.game;

import com.kvngleissner.collision.CollisionHandler;
import com.kvngleissner.entity.Player;
import com.kvngleissner.handler.AssetHandler;
import com.kvngleissner.handler.KeyHandler;
import com.kvngleissner.object.ObjectBase;
import com.kvngleissner.sound.Sound;
import com.kvngleissner.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16 x 16 Pixels
    final int scale = 3;
    final int FPS = 60;

    public  int tileSize = originalTileSize * scale; // 64 x 64 tile
    public  int maxScreenColumn = 24;
    public  int maxScreenRow = 18;

    public int screenWidth = tileSize * maxScreenColumn; // 1152 Pixels
    public int screenHeight = tileSize * maxScreenRow; // 768 Pixls

    KeyHandler keyHandler = new KeyHandler(this);
    Sound soundManager = new Sound();
    Thread gameThread;
    public Ui ui = new Ui(this);
    public Player player = new Player(this, keyHandler);
    public AssetHandler assetHandler = new AssetHandler(this);
    public TileManager tileManager = new TileManager(this);
    public ObjectBase base[] = new ObjectBase[10];
    public CollisionHandler collisionHandler = new CollisionHandler(this);

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
                // UPDATE : updating information such a character position
                    update();
                    // DRAW : Drawing the screen with updated Information
                    repaint();
                    delta--;
            }
        }
    }

    public void setUpGame() {
        assetHandler.setObject();
        playMusic(0);
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

        for(int i = 0; i < base.length; i++) {
            if(base[i] != null) {
                base[i].drawObject(graphics2D, this);
            }
        }

        player.draw(graphics2D);
        ui.draw(graphics2D);
        graphics2D.dispose();
    }


    public void playMusic(int i) {
        soundManager.setFile(i);
        soundManager.playSound();
        soundManager.loopSound();
    }
    public void stopMusic() {
        soundManager.stopSound();
    }
    public void playSoundEffect(int i) {
        soundManager.setFile(i);
        soundManager.playSound();
    }
}
