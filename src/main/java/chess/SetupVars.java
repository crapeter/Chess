package chess;

import javax.swing.*;
import java.util.HashMap;

public interface SetupVars {
  HashMap<Integer, String> pieceLoc = new HashMap<>();
  JFrame frame = new JFrame();
  JLabel textField = new JLabel();
  JButton[] buttons = new JButton[64];
  JButton forfeit = new JButton();
  JPanel forfeitPanel = new JPanel();
  JPanel titlePanel = new JPanel();
  JPanel capturedPanel = new JPanel();
  JPanel panel = new JPanel();
  JPanel capturedWhitePanel1 = new JPanel();
  JPanel capturedBlackPanel1 = new JPanel();
  JPanel capturedWhitePanel2 = new JPanel();
  JPanel capturedBlackPanel2 = new JPanel();
}