package view;

import javax.swing.*;
import java.awt.*;

public class JTimeField extends JPanel  {
    private static final int DEFAULT_COLUMN_ARG = 5;
    private static final String DEFAULT_DEFTEXT_ARG = "0";

    private enum TimeType {Sec(1), Min(60), Hour(60*60), Day(24*60*60);
        private final int _timeToSec;
        TimeType(int timeToSecFactor) {
            _timeToSec = timeToSecFactor;
        }

        public int getTimeToSec() {
            return _timeToSec;
        }
    }

    private Dimension size;
    JLabel label;
    JIntField textField;
    JComboBox<TimeType> comboBox;

    public JTimeField(String _label, String defaultText, int columns){
        super();
        label = new JLabel(_label, JLabel.TRAILING);
        label.setHorizontalAlignment(JLabel.LEFT);

        textField = new JIntField(defaultText, columns);
        comboBox = new JComboBox<>(TimeType.values());

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        this.add(label);
        this.add(textField);
        this.add(comboBox);


        layout.putConstraint(SpringLayout.EAST, comboBox, 0, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.WEST, comboBox);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, label, 0, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, comboBox, 0, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, textField, 0, SpringLayout.VERTICAL_CENTER, this);

        layout.putConstraint(SpringLayout.EAST, label, 0, SpringLayout.WEST, textField);
        layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, this);
        size = Utils.getCumulativeWidthHeight(new JComponent[]{label, textField, comboBox},
                                              new JComponent[]{comboBox});
    }

    private void manageSizes() {
        Dimension textDim = textField.getPreferredSize();
        Dimension labelDim = label.getPreferredSize();
        Dimension boxDim = comboBox.getPreferredSize();
        size = new Dimension(textDim.width + labelDim.width + boxDim.width, boxDim.height);

        this.setPreferredSize(size);
    }

    @Override
    public Dimension getPreferredSize(){
        return size;
    }

    public JTimeField(String _label){
        this(_label, DEFAULT_DEFTEXT_ARG, DEFAULT_COLUMN_ARG);
    }

    public JTimeField(String _label, int columns){
        this(_label, DEFAULT_DEFTEXT_ARG, columns);
    }

    public JTimeField(String _label, String defaultText){
        this(_label, defaultText, DEFAULT_COLUMN_ARG);
    }

    public int getValue(){
        TimeType timeType = (TimeType)comboBox.getSelectedItem();
        return textField.getRawValue() * timeType.getTimeToSec();

    }

    public void setEnabled(boolean b){
        textField.setEnabled(b);
        comboBox.setEnabled(b);
    }
}
