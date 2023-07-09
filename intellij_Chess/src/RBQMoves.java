import java.awt.Color;
import java.util.HashMap;
import javax.swing.JButton;

public abstract class RBQMoves {

    public static void move1(int location, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        for (int i = location + 1; i < 64; i++) {
            if (i / 8 != location / 8)
                break;

            if (!pieceLoc.containsKey(i)) {
                display(i, buttons, Color.yellow);
            }
            else {
                display(i, buttons, Color.red);
                break;
            }
        }
        for (int i = location - 1; i >= 0; i--) {
            if (i / 8 != location / 8)
                break;

            if (!pieceLoc.containsKey(i)) {
                display(i, buttons, Color.yellow);
            }
            else {
                display(i, buttons, Color.red);
                break;
            }
        }
        for (int i = location + 8; i < 64; i += 8) {
            if (!pieceLoc.containsKey(i)) {
                display(i, buttons, Color.yellow);
            }
            else {
                display(i, buttons, Color.red);
                break;
            }
        }
        for (int i = location - 8; i >= 0; i -= 8) {
            if (!pieceLoc.containsKey(i)) {
                display(i, buttons, Color.yellow);
            }
            else {
                display(i, buttons, Color.red);
                break;
            }
        }
    }
    public static void move2(int location, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        for (int i = location + 9; i < 64; i += 9) {
            if (i % 8 == 0)
                break;

            if (!pieceLoc.containsKey(i)) {
                display(i, buttons, Color.yellow);
            }
            else {
                display(i, buttons, Color.red);
                break;
            }
        }
        for (int i = location + 7; i < 64; i += 7) {
            if (i % 8 == 7)
                break;

            if (!pieceLoc.containsKey(i)) {
                display(i, buttons, Color.yellow);
            }
            else {
                display(i, buttons, Color.red);
                break;
            }
        }
        for (int i = location - 9; i >= 0; i -= 9) {
            if (i % 8 == 7)
                break;

            if (!pieceLoc.containsKey(i)) {
                display(i, buttons, Color.yellow);
            }
            else {
                display(i, buttons, Color.red);
                break;
            }
        }
        for (int i = location - 7; i >= 0; i -= 7) {
            if (i % 8 == 0)
                break;

            if (!pieceLoc.containsKey(i)) {
                display(i, buttons, Color.yellow);
            }
            else {
                display(i, buttons, Color.red);
                break;
            }
        }
    }
    public static void display(int loc, JButton[] buttons, Color color) {
        buttons[loc].setText("a");
        buttons[loc].setForeground(color);
    }
}
