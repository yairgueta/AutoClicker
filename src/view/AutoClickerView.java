package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AutoClickerView extends JFrame {
    public static void main(String[] args) {
        AutoClickerView view = new AutoClickerView();
    }

    MainPanel mainPanel;


    public AutoClickerView(){
        mainPanel = new MainPanel();

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
        this.setFocusable(true);
        this.setAutoRequestFocus(true);
    }

    public void throwErrorMessage(String msg){
        JOptionPane.showMessageDialog(this, msg, "Something is wrong!", JOptionPane.ERROR_MESSAGE);
    }



    public void updateTimer(String newTime){
        mainPanel.timer.setText(newTime);
    }

    // Getters

    public int getInitTime(){
        return mainPanel.acSettings.initialTimeField.getValue();
    }

    public int getIntervalTime(){
        return mainPanel.acSettings.intervalTimeField.getValue();
    }

    public Point getUserMouseInput(){
        return new Point(mainPanel.mouseSettings.xField.getRawValue(), mainPanel.mouseSettings.yField.getRawValue());
    }

    // Add Buttons Listeners

    public void addStartListener(ActionListener l){
        mainPanel.acSettings.startBtn.addActionListener(l);
    }

    public void addPauseListener(ActionListener l){
        mainPanel.acSettings.pauseBtn.addActionListener(l);
    }

    public void addStopListener(ActionListener l){
        mainPanel.acSettings.stopBtn.addActionListener(l);
    }

    public void addResumeListener(ActionListener l){
        mainPanel.acSettings.resumeBtn.addActionListener(l);
    }

    public void addTestMouseListener(ActionListener l) {
        mainPanel.mouseSettings.testMouseBtn.addActionListener(l);
    }

    // Enables

    public void setEnabledStart(boolean b){
        mainPanel.acSettings.startBtn.setEnabled(b);
    }

    public void setEnabledStop(boolean b){
        mainPanel.acSettings.stopBtn.setEnabled(b);
    }

    public void setEnabledPause(boolean b){
        mainPanel.acSettings.pauseBtn.setEnabled(b);
    }

    public void setEnabledResume(boolean b){
        mainPanel.acSettings.resumeBtn.setEnabled(b);
    }

    public void setEnabledTimeField(boolean b){
        mainPanel.acSettings.intervalTimeField.setEnabled(b);
        mainPanel.acSettings.initialTimeField.setEnabled(b);
    }

    public void updateMousePosition(int x, int y){
        mainPanel.mouseSettings.updateMousePos(x, y);
    }
}
