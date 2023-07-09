import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class Bpawn implements ActionListener, Images {
    public JFrame frame = new JFrame();
    public JLabel textField = new JLabel();
    public JPanel titlePanel = new JPanel();
    public JButton[] buttons = new JButton[4];
    public JPanel panel = new JPanel();
    public static String[] whitePieces = { "wBishop", "wKing", "wKnight", "wPawn", "wQueen", "wRook" };

    public static void move(int location, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        if (location / 8 == 1) {
            display(location, buttons, pieceLoc, true);
            diagDisplay(location, buttons);
        }
        // shows all possible moves if not first time moving
        else if (location / 8 != 7) {
            display(location, buttons, pieceLoc, false);
            diagDisplay(location, buttons);
        }
    }

    public static void display(int loc, JButton[] buttons, HashMap<Integer, String> pieceLoc, boolean firstMove) {
        if (firstMove) {
            if (pieceLoc.containsKey(loc + 8)) {
                buttons[loc + 8].setText("b");
                buttons[loc + 8].setForeground(Color.red);
            }
            else if (pieceLoc.containsKey(loc + 16)) {
                buttons[loc + 8].setText("a");
                buttons[loc + 8].setForeground(Color.yellow);
                buttons[loc + 16].setText("b");
                buttons[loc + 16].setForeground(Color.red);
            }
            else {
                buttons[loc + 8].setText("a");
                buttons[loc + 8].setForeground(Color.yellow);
                buttons[loc + 16].setText("a");
                buttons[loc + 16].setForeground(Color.yellow);
            }
        }
        else {
            if (!pieceLoc.containsKey(loc + 8)) {
                buttons[loc + 8].setText("a");
                buttons[loc + 8].setForeground(Color.yellow);
            }
            else {
                buttons[loc + 8].setText("b");
                buttons[loc + 8].setForeground(Color.red);
            }
        }
    }

    public static void diagDisplay(int loc, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        for (String piece: whitePieces) {
            if (pieceLoc.containsKey(loc + 7) && pieceLoc.get(loc + 7).equals(piece) && loc % 8 != 0) {
                buttons[loc + 7].setText("a");
                buttons[loc + 7].setForeground(Color.red);
            }
            if (pieceLoc.containsKey(loc + 9) && pieceLoc.get(loc + 9).equals(piece) && loc % 8 != 7) {
                buttons[loc + 9].setText("a");
                buttons[loc + 9].setForeground(Color.red);
            }
            if (pieceLoc.containsKey(loc - 1) && pieceLoc.get(loc - 1).equals(piece) && loc % 8 != 0 && loc / 8 == 4) {
                buttons[loc + 7].setText("a");
                buttons[loc + 7].setForeground(Color.red);
            }
            if (pieceLoc.containsKey(loc + 1) && pieceLoc.get(loc + 1).equals(piece) && loc % 8 != 7 && loc / 8 == 4) {
                buttons[loc + 9].setText("a");
                buttons[loc + 9].setForeground(Color.red);
            }
        }
    }

    public void displayChange() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        textField.setBackground(Color.white.darker());
        textField.setForeground(Color.black);
        textField.setFont(new Font("Verdana", Font.PLAIN, 30));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Pawn Transformation");
        textField.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);

        panel.setLayout(new GridLayout(2, 2));
        panel.setEnabled(false);
        panel.setBackground(Color.black);

        for (int i = 0; i < 4; i++) {
            buttons[i] = new JButton();
            panel.add(buttons[i]);
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);

            if (i == 0)
                buttons[i].setText("Queen");
            else if (i == 1)
                buttons[i].setText("Knight");
            else if (i == 2)
                buttons[i].setText("Bishop");
            else
                buttons[i].setText("Rook");
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
                if (buttons[i].getText().equals("Queen")) { Pawn.change(false, "Queen"); }
                else if (buttons[i].getText().equals("Knight")) { Pawn.change(false, "Knight"); }
                else if (buttons[i].getText().equals("Bishop")) { Pawn.change(false, "Bishop"); }
                else if (buttons[i].getText().equals("Rook")) { Pawn.change(false, "Rook"); }
                frame.setVisible(false);
            }
        }
    }
}
