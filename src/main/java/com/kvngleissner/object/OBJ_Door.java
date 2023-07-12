package com.kvngleissner.object;

import com.kvngleissner.game.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends ObjectBase{
    GamePanel panel;
    public OBJ_Door(GamePanel panel) {
        this.panel = panel;
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
            utilityHandler.scaleImage(image, panel.tileSize, panel.tileSize);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        collision = true;
    }
}
