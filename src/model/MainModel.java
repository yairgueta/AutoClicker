package model;

import java.awt.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public class MainModel {
    public static void main(String[] args) throws ModelException {
        MainModel model = new MainModel();
        model.setClicker(1, 3, null);
        model.start();
        TimerModel timerModel = new TimerModel(11, 700, () -> {
            System.out.print("Performing Values changes!");
            model.setDx(10);
            model.setDy(20);
            System.out.println();
        });
        timerModel.start();

    }

    private final ClickerModel clickerModel;
    private final WindowsModel windowsModel;
    TimerModel timerModel;

    public MainModel(){
        clickerModel = new ClickerModel();
        windowsModel = new WindowsModel();
    }


    // Clicker and Timer methods

    public void setClicker(int initialWait, int interval, Runnable onTick){
        if (timerModel != null){
            timerModel.stop();
        }
        timerModel = new TimerModel(initialWait, interval, this::makeClick);
        timerModel.setOnTickAction(onTick);
    }

    private void makeClick(){
        clickerModel.makeMouseClick(getWindowX() + dx, getWindowY() + dy);
    }

    public void start() throws ModelException {
        if (timerModel == null){
            throw new ModelException("No Timer was initialized!");
        }
        timerModel.start();
    }

    public void pause() throws ModelException {
        if (timerModel == null){
            throw new ModelException("No Timer was initialized!");
        }
        timerModel.pause();
    }

    public void stop() throws ModelException {
        if (timerModel == null){
            throw new ModelException("No Timer was initialized!");
        }
        timerModel.stop();
    }

    public void resume() throws ModelException {
        if (timerModel == null){
            throw new ModelException("No Timer was initialized!");
        }
        timerModel.resume();
    }

    public int getRemainingSeconds(){
        return this.timerModel.getRemainingSeconds();
    }

    public Point getMousePos(){
        return this.clickerModel.getMousePos();
    }

    // dx and dy are the offsets withing the window.
    private int dx, dy;

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }


    // Windows Methods

    public List<String> getAllWindowsNames(String filter){
        this.windowsModel.filterApps(filter);
        return this.windowsModel.getApplicationsNames();
    }

    public void selectWindow(int i){
        this.windowsModel.selectWindow(i);
    }

    private int getWindowX(){
        return this.windowsModel.getX();
    }

    private int getWindowY(){
        return this.windowsModel.getY();
    }

    public void moveMouse(Point p) {
        clickerModel.moveMouse(p.x, p.y);
    }
}
