package metier;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandle implements KeyListener{

    public static boolean upPressed, leftPressed, downPressed, rightPressed;

    public KeyHandle() {
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        

        int code = e.getKeyCode();

        // Déplacements avec flèches directionnelles
        if(code == KeyEvent.VK_UP || code == KeyEvent.VK_Z) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_Q) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_UP || code == KeyEvent.VK_Z) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_Q) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

    
}