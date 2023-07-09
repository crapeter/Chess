import java.awt.Color;
import java.util.HashMap;
import javax.swing.JButton;

public class King {
    public static void move(int location, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        int neg9 = location - 9;
        int neg8 = location - 8;
        int neg7 = location - 7;
        int neg1 = location - 1;
        int pos1 = location + 1;
        int pos7 = location + 7;
        int pos8 = location + 8;
        int pos9 = location + 9;

        canPlace(neg9, neg9 / 8, location / 8, buttons, pieceLoc);
        canPlace(neg8, neg8 / 8, location / 8, buttons, pieceLoc);
        canPlace(neg7, neg7 / 8, location / 8, buttons, pieceLoc);
        canPlace(neg1, neg1, location, buttons, pieceLoc);
        canPlace(pos1, pos1, location, buttons, pieceLoc);
        canPlace(pos7, pos7 / 8, location / 8, buttons, pieceLoc);
        canPlace(pos8, pos8 / 8, location / 8, buttons, pieceLoc);
        canPlace(pos9, pos9 / 8, location / 8, buttons, pieceLoc);
    }

    public static void canPlace(int loc,int location1, int location2, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        if (0 <= loc && loc < 64 && Math.abs(location1 - location2) == 1)
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