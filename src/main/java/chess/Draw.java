package chess;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Draw extends PieceUtils implements ActionListener {
  Font font;
  Font newFont;
  boolean white = true;
  Timer timer = new Timer();
  TimerTask task = new TimerTask() {
    @Override
    public void run() {
      textField.setText("White's turn");
    }
  };

  public void draw() {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1115, 900);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    textField.setBackground(Color.black);
    textField.setForeground(new Color(0, 66, 0));
    textField.setFont(new Font("Verdana", Font.PLAIN, 75));
    textField.setHorizontalAlignment(JLabel.CENTER);
    textField.setText("Chess");
    textField.setOpaque(true);
    titlePanel.setLayout(new BorderLayout());
    titlePanel.setBounds(0, 0, 800, 100);
    panel.setLayout(new GridLayout(8, 8));
    panel.setEnabled(false);
    panel.setBackground(Color.black);
    panel.setBorder(new LineBorder(new Color(118, 150, 86).darker(), 3));
    for (int i = 0; i < 64; i++) {
      buttons[i] = new JButton();
      panel.add(buttons[i]);
      buttons[i].setFocusable(false);
      buttons[i].setUI(new BasicButtonUI());
      buttons[i].setBorder(new EmptyBorder(0, 0, 0, 0));
      if (!white)
        buttons[i].setBackground(new Color(118, 150, 86));
      else
        buttons[i].setBackground(new Color(238, 238, 210));
      if (i % 8 != 7)
        white = !white;
    }
    capturedPanel.setPreferredSize(new Dimension(200, 800));
    capturedPanel.setBackground(Color.gray);
    capturedPanel.setLayout(new GridLayout(2, 2));
    capturedWhitePanel1.setLayout(new BoxLayout(capturedWhitePanel1, BoxLayout.Y_AXIS));
    capturedWhitePanel1.setBackground(new Color(28, 28, 28));
    capturedBlackPanel1.setLayout(new BoxLayout(capturedBlackPanel1, BoxLayout.Y_AXIS));
    capturedBlackPanel1.setBackground(Color.white.darker());
    capturedWhitePanel2.setLayout(new BoxLayout(capturedWhitePanel2, BoxLayout.Y_AXIS));
    capturedWhitePanel2.setBackground(new Color(28, 28, 28));
    capturedBlackPanel2.setLayout(new BoxLayout(capturedBlackPanel2, BoxLayout.Y_AXIS));
    capturedBlackPanel2.setBackground(Color.white.darker());
    forfeitPanel.setPreferredSize(new Dimension(115, 900));
    forfeitPanel.setLayout(new BoxLayout(forfeitPanel, BoxLayout.PAGE_AXIS));
    forfeitPanel.setBackground(new Color(28, 28, 28));
    forfeit.setFocusable(false);
    forfeit.setLayout(new BoxLayout(forfeit, BoxLayout.PAGE_AXIS));
    forfeit.setBackground(Color.red.darker());
    forfeit.addActionListener(this);
    forfeit.setPreferredSize(new Dimension(100, 200));
    forfeit.setText("Forfeit");
    font = forfeit.getFont();
    newFont = font.deriveFont(18f);
    forfeit.setFont(newFont);
    forfeitPanel.add(forfeit);
    capturedPanel.add(capturedWhitePanel1);
    capturedPanel.add(capturedWhitePanel2);
    capturedPanel.add(capturedBlackPanel1);
    capturedPanel.add(capturedBlackPanel2);
    titlePanel.add(textField);
    frame.add(titlePanel, BorderLayout.NORTH);
    frame.add(panel, BorderLayout.CENTER);
    frame.add(capturedPanel, BorderLayout.EAST);
    frame.add(forfeitPanel, BorderLayout.WEST);
    frame.setVisible(true);
    timer.schedule(task, 1500);
  }

  public static void addCapturedPiece(ImageIcon icon, boolean isWhitePiece, boolean isPawn) {
    JLabel label = new JLabel(icon);
    label.setPreferredSize(new Dimension(45, 45));
    if (isWhitePiece) {
      if (isPawn) {
        panelDisplay(capturedBlackPanel1, label, true);
      } else {
        panelDisplay(capturedBlackPanel2, label, true);
      }
    } else {
      if (isPawn) {
        panelDisplay(capturedWhitePanel1, label, true);
      } else {
        panelDisplay(capturedWhitePanel2, label, true);
      }
    }
    capturedPanel.revalidate();
    capturedPanel.repaint();
  }

  public static void panelDisplay(JPanel panel, JLabel label, boolean adding) {
    if (adding) {
      panel.add(label);
    } else {
      panel.removeAll();
    }
    panel.revalidate();
    panel.repaint();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == forfeit) {
      if (forfeit.getText().equals("Forfeit")) {
        textField.setText(currentlyWhite ? "White Forfeited" : "Black Forfeited");
        font = forfeit.getFont();
        newFont = font.deriveFont(18f);
        forfeit.setFont(newFont);
        forfeit.setText("Restart");
        gameOver = true;
        for (int i = 0; i < 64; i++) {
          if (currentlyWhite && pieceLoc.containsKey(i) && pieceLoc.get(i).equals("wKing")) {
            buttons[i].setIcon(wForfeit);
          } else if (!currentlyWhite && pieceLoc.containsKey(i) && pieceLoc.get(i).equals("bKing")) {
            buttons[i].setIcon(bForfeit);
          }
        }
      } else {
        font = forfeit.getFont();
        newFont = font.deriveFont(21f);
        forfeit.setFont(newFont);
        forfeit.setText("Forfeit");
        pieceLoc.clear();
        PieceSetup piece = new PieceSetup();
        piece.place();
        currentlyWhite = true;
        gameOver = false;
        panelDisplay(capturedWhitePanel1, null, false);
        panelDisplay(capturedWhitePanel2, null, false);
        panelDisplay(capturedBlackPanel1, null, false);
        panelDisplay(capturedBlackPanel2, null, false);
        if (textField.getText().equals("White Forfeited") || textField.getText().equals("Black Forfeited")) {
          textField.setText("White's turn");
        }
        resetBoardColor();
      }
    }
  }
}
