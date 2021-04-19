package view;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MouseSettingsView extends JPanel implements KeyListener {
    private static final String MOUSE_POS_FORMAT = "X:%04d Y:%04d";
    JIntField xField, yField;
    JButton testMouseBtn;
    KeyListenerButton keyButton;
    private JLabel mousePos;
    Dimension size;

    public MouseSettingsView(){
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        xField = new JIntField("0", 7);
        yField = new JIntField("0", 7);

        JLabel xLabel = new JLabel("X: ");
        JLabel yLabel = new JLabel("Y: ");

        JPanel mousePosPanel = new JPanel();
        JLabel mousePosLabel = new JLabel("Mouse: ");
        mousePos = new JLabel(MOUSE_POS_FORMAT.formatted(999, 999));
        mousePosPanel.add(mousePosLabel);
        mousePosPanel.add(mousePos);

        mousePosPanel.setBorder(BorderFactory.createLineBorder(new Color(0xB8BFB6)));

        testMouseBtn = new JButton("Test\nMouse");
        testMouseBtn.setPreferredSize(new Dimension(testMouseBtn.getPreferredSize().width, testMouseBtn.getPreferredSize().height * 2));
        keyButton = new KeyListenerButton();
        keyButton.setFunction(() -> {
            String s = mousePos.getText();
            xField.setText(s.substring(2, 6));
            yField.setText(s.substring(9));
        });

        this.add(xLabel);
        this.add(xField);
        this.add(yLabel);
        this.add(yField);
        this.add(mousePosPanel);
        this.add(testMouseBtn);
        this.add(keyButton);

        // Spread x label&field horizontally
        layout.putConstraint(SpringLayout.WEST, xLabel, 8, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, xField, -8, SpringLayout.EAST, this);

        // Spread y label&field horizontally
        layout.putConstraint(SpringLayout.WEST, yLabel, 8, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, yField, -8, SpringLayout.EAST, this);

        // Spread Mouse pos horizontally
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mousePosPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
//        layout.putConstraint(SpringLayout.EAST, yField, -8, SpringLayout.EAST, this);


        // Spread Buttons horizontally
        layout.putConstraint(SpringLayout.WEST, testMouseBtn, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, testMouseBtn, 5, SpringLayout.WEST, keyButton);
        layout.putConstraint(SpringLayout.EAST, keyButton, -5, SpringLayout.EAST, this);

        // X on top of y on top of mouspos on top of btn
        layout.putConstraint(SpringLayout.NORTH, yLabel, 0, SpringLayout.SOUTH, xLabel);
        layout.putConstraint(SpringLayout.NORTH, yField, 0, SpringLayout.SOUTH, xField);
        layout.putConstraint(SpringLayout.NORTH, mousePosPanel, 0, SpringLayout.SOUTH, yField);
        layout.putConstraint(SpringLayout.NORTH, testMouseBtn, 0, SpringLayout.SOUTH, mousePosPanel);
        layout.putConstraint(SpringLayout.NORTH, keyButton, 0, SpringLayout.SOUTH, mousePosPanel);
        layout.putConstraint(SpringLayout.SOUTH, keyButton, 0, SpringLayout.SOUTH, this);

        // align labels vertically center
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, xLabel, 0, SpringLayout.VERTICAL_CENTER, xField);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, yLabel, 0, SpringLayout.VERTICAL_CENTER, yField);

        size = Utils.getCumulativeSize(new JComponent[]{mousePosPanel},
                                       new JComponent[]{xField, yField, mousePosPanel, testMouseBtn});
        size.width += 15;
        this.setPreferredSize(size);
        this.setFocusable(true);
    }

    @Override
    public Dimension getPreferredSize(){
        return this.size;
    }

    public void updateMousePos(int x, int y){
        this.mousePos.setText(MOUSE_POS_FORMAT.formatted(x, y));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode());

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyCode());

    }
}
