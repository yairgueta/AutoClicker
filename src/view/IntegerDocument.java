package view;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntegerDocument extends DocumentFilter {
    private final Pattern pattern;

    private static String fbToString(FilterBypass fb) throws BadLocationException {
        return fb.getDocument().getText(0, fb.getDocument().getLength());
    }

    public IntegerDocument(int maxInts){
        String digitsQuantifier = maxInts < 0 ? "*" : "{0,%d}".formatted(maxInts);
        pattern = Pattern.compile("^((\\d" + digitsQuantifier + "))$");
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string,
                             AttributeSet attr) throws BadLocationException {
        String newStr = fbToString(fb) + string;
        Matcher m = pattern.matcher(newStr);
        if (m.matches()) super.insertString(fb, offset, string, attr);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws
            BadLocationException {
        super.remove(fb, offset, length);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text,
                        AttributeSet attrs) throws BadLocationException {
//        System.out.printf("%s %s %s %s %s%n", fbToString(fb), offset, length, text, attrs);
        StringBuilder sb = new StringBuilder(fbToString(fb));
        String newStr = sb.replace(offset, offset+length, text).toString();
        Matcher m = pattern.matcher(newStr);
        if (m.matches()) super.replace(fb, offset, length, text, attrs);
    }

}
