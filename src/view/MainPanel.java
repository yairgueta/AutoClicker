package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainPanel extends JPanel implements MouseListener {
    final AutoClickSettingsView acSettings;
    final MouseSettingsView mouseSettings;
    JLabel timer;

    public MainPanel(){
        this.setFocusable(true);
        this.setRequestFocusEnabled(true);

        String key = "a-kbd click key";
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'), key);
        this.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke('a'), key);
        this.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke('a'), key);
        this.addMouseListener(this);
        this.getActionMap().put(key, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mouseSettings.assignCurrentPixelsToInput();
            }
        });

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

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
        this.add(mouseSettings);
        this.add(acSettings);
        this.add(timerPanel);


        //** Layout Settings **//
        layout.putConstraint(SpringLayout.WEST, mouseSettings, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, acSettings, 0, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.WEST, acSettings, 0, SpringLayout.EAST, mouseSettings);

        layout.putConstraint(SpringLayout.NORTH, timerPanel, 0, SpringLayout.SOUTH, acSettings);
        layout.putConstraint(SpringLayout.SOUTH, timerPanel, 0, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, timerPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);


        // Other Window Settings
        Dimension dims = new Dimension(500, 200);
        this.setPreferredSize(dims);
    }

    private void createTimer(JPanel parent){
        JLabel timerLabel = new JLabel("Time till next Click: ");
        timer = new JLabel("00:00");
        parent.add(timerLabel);
        parent.add(timer);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.requestFocus();
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

}
