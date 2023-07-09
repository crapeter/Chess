import java.awt.Color;
import java.util.HashMap;
import javax.swing.JButton;

public class Knight {
    public static void move(int location, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        int loc1 = location - 17;
        int loc2 = location - 15;
        int loc3 = location - 10;
        int loc4 = location - 6;
        int loc5 = location + 6;
        int loc6 = location + 10;
        int loc7 = location + 15;
        int loc8 = location + 17;

        canPlace(loc1, location, 2, buttons, pieceLoc);
        canPlace(loc2, location, 2, buttons, pieceLoc);
        canPlace(loc3, location, 1, buttons, pieceLoc);
        canPlace(loc4, location, 1, buttons, pieceLoc);
        canPlace(loc5, location, 1, buttons, pieceLoc);
        canPlace(loc6, location, 1, buttons, pieceLoc);
        canPlace(loc7, location, 2, buttons, pieceLoc);
        canPlace(loc8, location, 2, buttons, pieceLoc);
    }

    public static void canPlace(int loc, int location, int spaceBetween, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        if (0 <= loc && loc < 64 && Math.abs((location / 8) - (loc / 8)) == spaceBetween)
            display(loc, buttons, pieceLoc);
    }

    public static void display(int loc, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        buttons[loc].setText("a");

        if (pieceLoc.containsKey(loc))
            buttons[loc].setForeground(Color.red);
        else
            buttons[loc].setForeground(Color.yellow);
    }
}