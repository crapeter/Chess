import javax.swing.*;
import java.util.HashMap;

public interface setupVars {
    HashMap<Integer, String> pieceLoc = new HashMap<>();
    JFrame frame = new JFrame();
    JLabel textField = new JLabel();
    JPanel titlePanel = new JPanel();
    JButton[] buttons = new JButton[64];
    JPanel panel = new JPanel();
}