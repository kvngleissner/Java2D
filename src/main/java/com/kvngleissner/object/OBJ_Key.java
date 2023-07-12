package com.kvngleissner.object;

import com.kvngleissner.game.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends ObjectBase{
    GamePanel panel;
    public OBJ_Key(GamePanel panel) {
        this.panel = panel;
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            utilityHandler.scaleImage(image, panel.tileSize, panel.tileSize);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
