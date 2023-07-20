import java.awt.Color;
import java.util.HashMap;
import javax.swing.JButton;

public class King extends HelperFunctions{
    public static void move(int location, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        int[] moveOptions = {location - 9, location - 8, location - 7, location - 1,
                             location + 1, location + 7, location + 8, location + 9};
        for (int i : moveOptions) {
            if (i == location - 1 || i == location + 1) {
                canPlace(i, i, location, buttons, pieceLoc);
            } else {
                canPlace(i, i / 8, location / 8, buttons, pieceLoc);
            }
        }
    }
    public static void canPlace(int loc, int location1, int location2, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        if (0 <= loc && loc < 64 && Math.abs(location1 - location2) == 1)
            if (pieceLoc.containsKey(loc))
                displayMoves(loc, buttons, Color.red);
            else
                displayMoves(loc, buttons, Color.yellow);
    }
}
