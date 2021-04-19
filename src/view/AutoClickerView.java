package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AutoClickerView extends JFrame {
    public static void main(String[] args) {
        AutoClickerView view = new AutoClickerView();
    }

    private AutoClickSettingsView acSettings;
    private MouseSettingsView mouseSettings;
    private JLabel timer;

    public AutoClickerView(){
        JPanel mainPanel = new JPanel();

        SpringLayout layout = new SpringLayout();
        mainPanel.setLayout(layout);

        // Create Autoclicker Settings
        acSettings = new AutoClickSettingsView();

        // Create Timer Panel
        JPanel timerPanel = new JPanel();
        createTimer(timerPanel);
        timerPanel.setSize(new Dimension(130, 150));

        // Create Mouse Settings Panel
        mouseSettings = new MouseSettingsView();
        mouseSettings.setFocusable(true);

        // Add Widgets to Window
        mainPanel.add(mouseSettings);
        mainPanel.add(acSettings);
        mainPanel.add(timerPanel);
        mainPanel.setFocusable(true);
        mainPanel.setRequestFocusEnabled(true);


        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'), "a Clicked");
        mainPanel.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke('a'), "a Clicked");
        mainPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke('a'), "a Clicked");
        mainPanel.getActionMap().put("a Clicked", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hihihi");
            }
        });
        mainPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPanel.requestFocus();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //** Layout Settings **//
        layout.putConstraint(SpringLayout.WEST, mouseSettings, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, acSettings, 0, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.WEST, acSettings, 0, SpringLayout.EAST, mouseSettings);

        layout.putConstraint(SpringLayout.NORTH, timerPanel, 0, SpringLayout.SOUTH, acSettings);
        layout.putConstraint(SpringLayout.SOUTH, timerPanel, 0, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, timerPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);


        // Other Window Settings
        Dimension dims = new Dimension(500, 200);
        mainPanel.setPreferredSize(dims);
//        mainPanel.setSize(dims);

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
        this.setFocusable(true);
        this.setAutoRequestFocus(true);
    }

    public void throwErrorMessage(String msg){
        JOptionPane.showMessageDialog(this, msg, "Something is wrong!", JOptionPane.ERROR_MESSAGE);
    }

    private void createTimer(JPanel parent){
        JLabel timerLabel = new JLabel("Time till next Click: ");
        timer = new JLabel("00:00");
        parent.add(timerLabel);
        parent.add(timer);
    }

    public void updateTimer(String newTime){
        timer.setText(newTime);
    }

    // Getters

    public int getInitTime(){
        return acSettings.initialTimeField.getValue();
    }

    public int getIntervalTime(){
        return acSettings.intervalTimeField.getValue();
    }

    public Point getUserMouseInput(){
        return new Point(mouseSettings.xField.getRawValue(), mouseSettings.yField.getRawValue());
    }

    // Add Buttons Listeners

    public void addStartListener(ActionListener l){
        acSettings.startBtn.addActionListener(l);
    }

    public void addPauseListener(ActionListener l){
        acSettings.pauseBtn.addActionListener(l);
    }

    public void addStopListener(ActionListener l){
        acSettings.stopBtn.addActionListener(l);
    }

    public void addResumeListener(ActionListener l){
        acSettings.resumeBtn.addActionListener(l);
    }

    public void addTestMouseListener(ActionListener l) {
        mouseSettings.testMouseBtn.addActionListener(l);
    }

    // Enables

    public void setEnabledStart(boolean b){
        acSettings.startBtn.setEnabled(b);
    }

    public void setEnabledStop(boolean b){
        acSettings.stopBtn.setEnabled(b);
    }

    public void setEnabledPause(boolean b){
        acSettings.pauseBtn.setEnabled(b);
    }

    public void setEnabledResume(boolean b){
        acSettings.resumeBtn.setEnabled(b);
    }

    public void setEnabledTimeField(boolean b){
        acSettings.intervalTimeField.setEnabled(b);
        acSettings.initialTimeField.setEnabled(b);
    }

    public void updateMousePosition(int x, int y){
        this.mouseSettings.updateMousePos(x, y);
    }

}
