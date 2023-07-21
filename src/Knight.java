import java.awt.Color;
import java.util.HashMap;
import javax.swing.JButton;

public class Knight extends HelperFunctions {
    public static void move(int location, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        int[] moveOptions = {location - 17, location - 15, location - 10, location - 6,
                             location + 6, location + 10, location + 15, location + 17};
        for (int i : moveOptions) {
            if (i == location - 10 || i == location - 6 || i == location + 6 || i == location + 10) {
                canPlace(i, location, 1, buttons, pieceLoc);
            } else {
                canPlace(i, location, 2, buttons, pieceLoc);
            }
        }
    }
    public static void canPlace(int loc, int location, int spaceBetween, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        if (0 <= loc && loc < 64 && Math.abs((location / 8) - (loc / 8)) == spaceBetween)
            if (pieceLoc.containsKey(loc))
                displayMoves(loc, buttons, Color.red);
            else
                displayMoves(loc, buttons, Color.yellow);
    }
}