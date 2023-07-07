package com.kvngleissner.handler;

import com.kvngleissner.game.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upKeyPressed, rightKeyPressed, downKeyPressed, leftKeyPressed, escapeKeyPressed;
    GamePanel panel;
    public KeyHandler(GamePanel gamePanel) {
        this.panel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Checks for the currently pressed key
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            upKeyPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downKeyPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            leftKeyPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            rightKeyPressed = true;
        }
    }


    /**
     * Checks if the key is releases
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            upKeyPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downKeyPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftKeyPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightKeyPressed = false;
        }
        if(code == KeyEvent.VK_ESCAPE) {
            escapeKeyPressed = false;
        }
    }
}
