import java.awt.Color;
import java.util.HashMap;
import javax.swing.JButton;

public class Castle extends BuildChess {
    public static void canCastle(int location, JButton[] buttons, HashMap<Integer, String> pieceLoc, boolean whiteCastle, boolean blackCastle) {
        int count = 0;
        if (blackCastle) {
            if (location == bKingLocation && pieceLoc.get(location).equals("bKing")) {
                for (int i = location - 1; i > 0; i--) {
                    if (pieceLoc.containsKey(i))
                        break;
                    else
                        count++;
                }
                if (count == 3 && pieceLoc.containsKey(0) && pieceLoc.get(0).equals("bRook"))
                    display(1, buttons);

                count = 0;
                for (int i = location + 1; i < 7; i++) {
                    if (pieceLoc.containsKey(i))
                        break;
                    else
                        count++;
                }
                if (count == 2 && pieceLoc.containsKey(7) && pieceLoc.get(7).equals("bRook"))
                    display(6, buttons);
            }
        }
        if (whiteCastle) {
            if (location == wKingLocation && pieceLoc.get(location).equals("wKing")) {
                for (int i = location - 1; i > 56; i--) {
                    if (pieceLoc.containsKey(i))
                        break;
                    else
                        count++;
                }
                if (count == 3 && pieceLoc.containsKey(56) && pieceLoc.get(56).equals("wRook"))
                    display(57, buttons);

                count = 0;
                for (int i = location + 1; i < 63; i++) {
                    if (pieceLoc.containsKey(i))
                        break;
                    else
                        count++;
                }
                if (count == 2 && pieceLoc.containsKey(63) && pieceLoc.get(63).equals("wRook"))
                    display(62, buttons);
            }
        }
    }

    public static void display(int loc, JButton[] buttons) {
        buttons[loc].setText("a");
        buttons[loc].setForeground(Color.yellow);
    }
}