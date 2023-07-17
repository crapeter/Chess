import javax.swing.*;
import java.util.HashMap;

public class Main {
    public static HashMap<Integer, String> pieceLoc = new HashMap<>();
    public static JFrame frame = new JFrame();
    public static JLabel textField = new JLabel();
    public static JPanel titlePanel = new JPanel();
    public static JButton[] buttons = new JButton[64];
    public static JPanel panel = new JPanel();

    public static void main(String[] args) {
        BuildChess chess = new BuildChess();
        Draw board = new Draw();
        board.draw();
        chess.constructChess();
    }
}