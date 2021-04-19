package view;

import javax.swing.*;
import java.awt.*;

public class AutoClickSettingsView extends JPanel{
    JTimeField initialTimeField, intervalTimeField;
    JButton startBtn, stopBtn, pauseBtn, resumeBtn;
    Dimension size;

    public AutoClickSettingsView(){
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JPanel timeFieldsPanel = new JPanel(new GridLayout(2, 1));
        this.addTimeFields(timeFieldsPanel);
        this.add(timeFieldsPanel);

        layout.putConstraint(SpringLayout.WEST, timeFieldsPanel, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, timeFieldsPanel, 0, SpringLayout.EAST, this);

        JPanel btnsPanel = new JPanel(new GridBagLayout());
        this.addButtons(btnsPanel);
        this.add(btnsPanel);
        layout.putConstraint(SpringLayout.NORTH, btnsPanel, 3, SpringLayout.SOUTH, timeFieldsPanel);
        layout.putConstraint(SpringLayout.WEST, btnsPanel, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, btnsPanel, 0, SpringLayout.EAST, this);

        size = Utils.getCumulativeWidthHeight(new JComponent[]{initialTimeField},
                                              new JComponent[]{initialTimeField, intervalTimeField, startBtn, stopBtn, resumeBtn});
    }

    @Override
    public Dimension getPreferredSize(){
        return size;
    }

    private void addTimeFields(JPanel parent){
        initialTimeField = new JTimeField("Initial Time: ");
        intervalTimeField = new JTimeField("Interval Time: ");
        parent.add(initialTimeField);
        parent.add(intervalTimeField);
    }

    private void addButtons(JPanel parent){
        GridBagConstraints constraints = new GridBagConstraints();

        startBtn = new JButton("Start!");
        stopBtn = new JButton("Stop");
        pauseBtn = new JButton("Pause");
        resumeBtn = new JButton("Resume");

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.weightx = 1f;
        parent.add(startBtn, constraints);

        constraints.gridy = 1;
        constraints.gridwidth = 1;
        parent.add(stopBtn, constraints);

        constraints.gridx = 1;
        parent.add(pauseBtn, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        parent.add(resumeBtn, constraints);
    }



}
