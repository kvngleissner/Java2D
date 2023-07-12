package com.kvngleissner.game;

import com.kvngleissner.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ui {

    GamePanel panel;
    Font arial_32;
    BufferedImage keyImage;
    public Ui(GamePanel panel) {
        this.panel = panel;
        arial_32 = new Font("Arial", Font.ITALIC, 32);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setFont(new Font("Arial", Font.ITALIC, 32));
        graphics2D.setColor(Color.cyan);
        graphics2D.drawImage(keyImage, panel.tileSize / 2, panel.tileSize/2, panel.tileSize, panel.tileSize, null);
        graphics2D.drawString("x = " + panel.player.hasKey, 74, 60);
    }
}
