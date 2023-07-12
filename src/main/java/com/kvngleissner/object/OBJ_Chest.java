package com.kvngleissner.object;

import com.kvngleissner.game.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends ObjectBase{
    GamePanel panel;

    public OBJ_Chest(GamePanel panel) {
        this.panel = panel;
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
            utilityHandler.scaleImage(image, panel.tileSize, panel.tileSize);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        collision = true;
    }

}
