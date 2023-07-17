import java.util.HashMap;
import javax.swing.JButton;

public class Queen extends RBQMoves {
    public static void move(int location, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        move1(location, buttons, pieceLoc);
        move2(location, buttons, pieceLoc);
    }
}