package model;

import java.awt.*;
import java.awt.event.InputEvent;

public class ClickerModel {
    Robot robot;

    public ClickerModel(){
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
            //TODO what to do when this happens?
        }
    }

    public void moveMouse(int x, int y){
        robot.mouseMove(x ,y);
    }

    /**
     * Makes a Mouse click in its position!
     */
    public void makeMouseClick(){
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void makeMouseClick(int x, int y){
        moveMouse(x, y);
        makeMouseClick();
    }

    public Point getMousePos(){
        return MouseInfo.getPointerInfo().getLocation();
    }

}
