import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class Wpawn implements ActionListener, Images {
    public JFrame frame = new JFrame();
    public JLabel textField = new JLabel();
    public JPanel titlePanel = new JPanel();
    public JButton[] buttons = new JButton[4];
    public JPanel panel = new JPanel();

    public static void move(int location, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        if (location / 8 == 6) {
            display(location, buttons, pieceLoc, true);
            diagDisplay(location, buttons);
        }
        // shows all possible moves if not first time moving
        else if (location / 8 != 0) {
            display(location, buttons, pieceLoc, false);
            diagDisplay(location, buttons);
        }
    }

    public static void display(int loc, JButton[] buttons, HashMap<Integer, String> pieceLoc, boolean firstMove) {
        if (firstMove) {
            if (pieceLoc.containsKey(loc - 8)) {
                buttons[loc - 8].setText("b");
                buttons[loc - 8].setForeground(Color.red);
            }
            else if (pieceLoc.containsKey(loc - 16)) {
                buttons[loc - 8].setText("a");
                buttons[loc - 8].setForeground(Color.yellow);
                buttons[loc - 16].setText("b");
                buttons[loc - 16].setForeground(Color.red);
            }
            else {
                buttons[loc - 8].setText("a");
                buttons[loc - 8].setForeground(Color.yellow);
                buttons[loc - 16].setText("a");
                buttons[loc - 16].setForeground(Color.yellow);
            }
        }
        else {
            if (!pieceLoc.containsKey(loc - 8)) {
                buttons[loc - 8].setText("a");
                buttons[loc - 8].setForeground(Color.yellow);
            }
            else {
                buttons[loc - 8].setText("b");
                buttons[loc - 8].setForeground(Color.red);
            }
        }
    }

    public static void diagDisplay(int loc, JButton[] buttons) {
        if (loc % 8 == 0) {
            buttons[loc - 7].setText("a");
            buttons[loc - 7].setForeground(Color.red);
        }
        else if (loc % 8 == 7) {
            buttons[loc - 9].setText("a");
            buttons[loc - 9].setForeground(Color.red);
        }
        else {
            buttons[loc - 9].setText("a");
            buttons[loc - 9].setForeground(Color.red);

            buttons[loc - 7].setText("a");
            buttons[loc - 7].setForeground(Color.red);
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