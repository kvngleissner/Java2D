package com.kvngleissner.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends ObjectBase{

    public OBJ_Chest() {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
