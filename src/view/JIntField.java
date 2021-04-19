package view;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class JIntField extends JTextField implements FocusListener {
    private final static int DEFAULT_MAX_INTS = 5;
    private final static int DEFAULT_COLUMNS = 5;


    public JIntField(int maxInts, String defaultText, int columns){
        super(defaultText, columns);
        ((AbstractDocument)this.getDocument()).setDocumentFilter(new IntegerDocument(5));
        this.addFocusListener(this);
    }

    public JIntField(String defaultText, int columns){
        this(DEFAULT_MAX_INTS, defaultText, columns);
    }

    public JIntField(String defaultText){
        this(DEFAULT_MAX_INTS, defaultText, DEFAULT_COLUMNS);
    }

    public int getRawValue(){
        try{
            return Integer.parseInt(this.getText());
        } catch (NumberFormatException e){
            return 0;
        }
    }

    private void reformatText(){
        this.setText(String.valueOf(getRawValue()));
    }

    @Override
    public void focusGained(FocusEvent e) {
        reformatText();
    }

    @Override
    public void focusLost(FocusEvent e) {
        reformatText();
    }

}
