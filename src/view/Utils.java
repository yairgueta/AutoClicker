package view;

import javax.swing.*;
import java.awt.*;

public class Utils {
    public static Dimension getCumulativeSquaredSize(JComponent ... components){
        int height = 0, width = 0;
        for (JComponent c : components){
            Dimension preferredSize = c.getPreferredSize();
            height += preferredSize.height;
            width += preferredSize.width;
        }
        return new Dimension(width, height);
    }

    public static int getCumulativeHeight(JComponent ... components){
        int height = 0;
        for (JComponent c : components){
            Dimension preferredSize = c.getPreferredSize();
            height += preferredSize.height;
        }
        return height;
    }

    public static int getCumulativeWidth(JComponent ... components){
        int width = 0;
        for (JComponent c : components){
            Dimension preferredSize = c.getPreferredSize();
            width += preferredSize.width;
        }
        return width;
    }

    public static Dimension getCumulativeWidthHeight(JComponent[] width, JComponent[] height){
        return new Dimension(getCumulativeWidth(width), getCumulativeHeight(height));
    }

    public static Dimension getCumulativeSize(JComponent[] width, JComponent[] height){
        return getCumulativeWidthHeight(width, height);
    }


}
