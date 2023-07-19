import java.awt.Color;
import java.util.HashMap;
import javax.swing.JButton;

public class Knight extends HelperFunctions {
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
            if (pieceLoc.containsKey(loc))
                displayMoves(loc, buttons, Color.red);
            else
                displayMoves(loc, buttons, Color.yellow);
    }
}