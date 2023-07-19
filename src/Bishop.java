import java.util.HashMap;
import javax.swing.JButton;

public class Bishop extends RBQMoves {
    public static void move(int location, JButton[] buttons, HashMap<Integer, String> pieceLoc) { move2(location, buttons, pieceLoc); }
}