package chess;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Draw implements Images, SetupVars {
  static JPanel capturedWhitePanel1 = new JPanel();
  static JPanel capturedBlackPanel1 = new JPanel();
  static JPanel capturedWhitePanel2 = new JPanel();
  static JPanel capturedBlackPanel2 = new JPanel();
  public boolean white = true;
  Timer timer = new Timer();
  TimerTask task = new TimerTask() {
    @Override
    public void run() {
      textField.setText("White's turn");
    }
  };

  public void draw() {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 900);
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

    for (int i = 0; i < 64; i++) {
      buttons[i] = new JButton();
      panel.add(buttons[i]);
      buttons[i].setFocusable(false);
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

    capturedPanel.add(capturedWhitePanel1);
    capturedPanel.add(capturedWhitePanel2);
    capturedPanel.add(capturedBlackPanel1);
    capturedPanel.add(capturedBlackPanel2);

    titlePanel.add(textField);
    frame.add(titlePanel, BorderLayout.NORTH);
    frame.add(panel, BorderLayout.CENTER);
    frame.add(capturedPanel, BorderLayout.EAST);
    frame.setVisible(true);
    timer.schedule(task, 1500);
  }

  public static void addCapturedPiece(ImageIcon icon, boolean isWhitePiece, boolean isPawn) {
    JLabel label = new JLabel(icon);
    label.setPreferredSize(new Dimension(45, 45));
    if (isWhitePiece) {
      if (isPawn) {
        capturedBlackPanel1.add(label);
        capturedBlackPanel1.revalidate();
        capturedBlackPanel1.repaint();
      } else {
        capturedBlackPanel2.add(label);
        capturedBlackPanel2.revalidate();
        capturedBlackPanel2.repaint();
      }
    } else {
      if (isPawn) {
        capturedWhitePanel1.add(label);
        capturedWhitePanel1.revalidate();
        capturedWhitePanel1.repaint();
      } else {
        capturedWhitePanel2.add(label);
        capturedWhitePanel2.revalidate();
        capturedWhitePanel2.repaint();
      }
    }
    capturedPanel.revalidate();
    capturedPanel.repaint();
  }
}
