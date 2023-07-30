import java.awt.Color;
import java.util.HashMap;
import java.util.stream.IntStream;
import javax.swing.JButton;

public class Knight extends HelperFunctions {
    public static void move() {
        int[] knightLoc1 = { location - 10, location - 6, location + 6, location + 10 };
        int[] knightLoc2 = { location - 17, location - 15, location + 15, location + 17 };
        IntStream.range(0, 4)
                .forEach(i -> canPlace(knightLoc1[i], location, 1, buttons, pieceLoc));
        IntStream.range(0, 4)
                .forEach(i -> canPlace(knightLoc2[i], location, 2, buttons, pieceLoc));
    }
    public static void canPlace(int loc, int location, int spaceBetween, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        if (0 <= loc && loc < 64 && Math.abs((location / 8) - (loc / 8)) == spaceBetween)
            if (pieceLoc.containsKey(loc))
                displayMoves(loc, buttons, Color.red);
            else
                displayMoves(loc, buttons, Color.yellow);
    }
}