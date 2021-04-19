package view;

import javax.swing.*;
import java.awt.event.*;

public class KeyListenerButton extends JButton implements KeyListener {
    Runnable f;
    public KeyListenerButton(){
        super("S");
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 's'){
            f.run();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setFunction(Runnable f){
        this.f = f;
    }
}
