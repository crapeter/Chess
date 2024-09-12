package chess;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pawn extends PieceFunctionality {
  public JFrame frame = new JFrame();
  public JLabel textField = new JLabel();
  public JPanel titlePanel = new JPanel();
  public JButton[] buttons = new JButton[4];
  public JPanel panel = new JPanel();

  public void change(String color) {
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    frame.setSize(400, 400);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);

    textField.setBackground(Color.darkGray);
    textField.setForeground(Color.white);
    textField.setFont(new Font("Verdana", Font.PLAIN, 30));
    textField.setHorizontalAlignment(JLabel.CENTER);
    textField.setText("Pawn Transformation");
    textField.setOpaque(true);

    titlePanel.setLayout(new BorderLayout());
    titlePanel.setBounds(0, 0, 800, 100);

    panel.setLayout(new GridLayout(2, 2));
    panel.setEnabled(false);
    panel.setBackground(Color.black);

    String[] pieceNames = { " Queen", " Knight", " Bishop", " Rook" };
    for (int i = 0; i < 4; i++) {
      buttons[i] = new JButton();
      panel.add(buttons[i]);
      buttons[i].setFocusable(false);
      buttons[i].addActionListener(this);
      buttons[i].setBackground(new Color(255, 195, 128));
      buttons[i].setBorder(new LineBorder(Color.black, 1));
      buttons[i].setUI(new BasicButtonUI());
      buttons[i].setText(color + pieceNames[i]);
      buttons[i].setFont(new Font("Verdana", Font.PLAIN, 20));
      init(buttons[i]);
    }
    titlePanel.add(textField);
    frame.add(titlePanel, BorderLayout.NORTH);
    frame.add(panel);
    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for (int i = 0; i < 4; i++) {
      if (e.getSource() == buttons[i]) {
        switch (buttons[i].getText()) {
          case "White Queen" -> display("wQueen", wQueen);
          case "White Knight" -> display("wKnight", wKnight);
          case "White Bishop" -> display("wBishop", wBishop);
          case "White Rook" -> display("wRook", wRook);
          case "Black Queen" -> display("bQueen", bQueen);
          case "Black Knight" -> display("bKnight", bKnight);
          case "Black Bishop" -> display("bBishop", bBishop);
          case "Black Rook" -> display("bRook", bRook);
        }
        frame.setVisible(false);
      }
    }
  }

  public static void display(String name, ImageIcon newIcon) {
    promoting = false;
    placePiece(location, newIcon, name);
  }

  private void init(JButton button) {
    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        button.setBackground(new Color(255, 195, 128).darker());
      }

      @Override
      public void mouseExited(MouseEvent e) {
        button.setBackground(new Color(255, 195, 128));
      }
    });
  }
}
