package com.kvngleissner.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends ObjectBase{
    public OBJ_Door() {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        collision = true;
    }
}
