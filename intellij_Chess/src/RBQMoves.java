import java.awt.Color;
import java.util.HashMap;
import javax.swing.JButton;

public abstract class RBQMoves extends HelperFunctions {
    public static void move1(int location, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        for (int i = location + 1; i < 64; i++) {
            if (i / 8 != location / 8) break;
            boolean notEmpty = pieceLoc.containsKey(i);
            displayMoves(i, buttons, notEmpty ? Color.red : Color.yellow);
            if (notEmpty) break;
        }
        for (int i = location - 1; i >= 0; i--) {
            if (i / 8 != location / 8) break;
            boolean notEmpty = pieceLoc.containsKey(i);
            displayMoves(i, buttons, notEmpty ? Color.red : Color.yellow);
            if (notEmpty) break;
        }
        for (int i = location + 8; i < 64; i += 8) {
            boolean notEmpty = pieceLoc.containsKey(i);
            displayMoves(i, buttons, notEmpty ? Color.red : Color.yellow);
            if (notEmpty) break;
        }
        for (int i = location - 8; i >= 0; i -= 8) {
            boolean notEmpty = pieceLoc.containsKey(i);
            displayMoves(i, buttons, notEmpty ? Color.red : Color.yellow);
            if (notEmpty) break;
        }
    }
    public static void move2(int location, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        for (int i = location + 9; i < 64; i += 9) {
            if (i % 8 == 0) break;
            boolean notEmpty = pieceLoc.containsKey(i);
            displayMoves(i, buttons, notEmpty ? Color.red : Color.yellow);
            if (notEmpty) break;
        }
        for (int i = location + 7; i < 64; i += 7) {
            if (i % 8 == 7) break;
            boolean notEmpty = pieceLoc.containsKey(i);
            displayMoves(i, buttons, notEmpty ? Color.red : Color.yellow);
            if (notEmpty) break;
        }
        for (int i = location - 9; i >= 0; i -= 9) {
            if (i % 8 == 7) break;
            boolean notEmpty = pieceLoc.containsKey(i);
            displayMoves(i, buttons, notEmpty ? Color.red : Color.yellow);
            if (notEmpty) break;
        }
        for (int i = location - 7; i >= 0; i -= 7) {
            if (i % 8 == 0) break;
            boolean notEmpty = pieceLoc.containsKey(i);
            displayMoves(i, buttons, notEmpty ? Color.red : Color.yellow);
            if (notEmpty) break;
        }
    }
}
