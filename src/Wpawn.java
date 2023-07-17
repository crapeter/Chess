import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class Wpawn extends BuildChess implements ActionListener, Images {
    public JFrame frame = new JFrame();
    public JLabel textField = new JLabel();
    public JPanel titlePanel = new JPanel();
    public JButton[] buttons = new JButton[4];
    public JPanel panel = new JPanel();
    public static String[] blackPieces = { "bBishop", "bKing", "bKnight", "bPawn", "bQueen", "bRook" };

    public static void move(int location, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        if (location / 8 == 6) {
            display(location, buttons, pieceLoc, true);
            diagDisplay(location, buttons, pieceLoc);
        }
        // shows all possible moves if not first time moving
        else if (location / 8 != 0) {
            display(location, buttons, pieceLoc, false);
            diagDisplay(location, buttons, pieceLoc);
        }
    }

    public static void display(int loc, JButton[] buttons, HashMap<Integer, String> pieceLoc, boolean firstMove) {
        if (firstMove) {
            if (pieceLoc.containsKey(loc - 8)) {
                blockPawn(loc - 8, buttons, Color.red);
            }
            else if (pieceLoc.containsKey(loc - 16)) {
                displayMoves(loc - 8, buttons, Color.yellow);
                blockPawn(loc - 16, buttons, Color.red);
            }
            else {
                displayMoves(loc - 8, buttons, Color.yellow);
                displayMoves(loc - 16, buttons, Color.yellow);
            }
        }
        else {
            if (pieceLoc.containsKey(loc - 8)) {
                blockPawn(loc - 8, buttons, Color.red);
            }
            else {
                displayMoves(loc - 8, buttons, Color.yellow);
            }
        }
    }

    public static void diagDisplay(int loc, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        for (String piece: blackPieces) {
            if (pieceLoc.containsKey(loc - 7) && pieceLoc.get(loc - 7).equals(piece) && loc % 8 != 7) {
                displayMoves(loc - 7, buttons, Color.red);
            }
            if (pieceLoc.containsKey(loc - 9) && pieceLoc.get(loc - 9).equals(piece) && loc % 8 != 0) {
                displayMoves(loc - 9, buttons, Color.red);
            }
            //checking for en passant
            if (pieceLoc.containsKey(loc - 1) && pieceLoc.get(loc - 1).equals(piece) && loc % 8 != 0 && loc / 8 == 3) {
                if (Math.abs(blackPawnMove[0][(location - 1) % 8] - blackPawnMove[1][(location - 1) % 8]) == 16) {
                    displayMoves(loc - 9, buttons, Color.red);
                }
            }
            if (pieceLoc.containsKey(loc + 1) && pieceLoc.get(loc + 1).equals(piece) && loc % 8 != 7 && loc / 8 == 3) {
                if (Math.abs(blackPawnMove[0][(location + 1) % 8] - blackPawnMove[1][(location + 1) % 8]) == 16) {
                    displayMoves(loc - 7, buttons, Color.red);
                }
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
                if (buttons[i].getText().equals("Queen")) { Pawn.change(true, "Queen"); }
                else if (buttons[i].getText().equals("Knight")) { Pawn.change(true, "Knight"); }
                else if (buttons[i].getText().equals("Bishop")) { Pawn.change(true, "Bishop"); }
                else if (buttons[i].getText().equals("Rook")) { Pawn.change(true, "Rook"); }
                frame.setVisible(false);
            }
        }
    }
}
