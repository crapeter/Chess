import java.util.HashMap;
import javax.swing.JButton;

public class Rook extends RBQMoves {
    public static void move(int location, JButton[] buttons, HashMap<Integer, String> pieceLoc) { move1(location, buttons, pieceLoc); }
}