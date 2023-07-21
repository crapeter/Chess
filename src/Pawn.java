import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pawn extends BuildChess implements Images, ActionListener {
    public JFrame frame = new JFrame();
    public JLabel textField = new JLabel();
    public JPanel titlePanel = new JPanel();
    public JButton[] buttons = new JButton[8];
    public JPanel panel = new JPanel();
    public void change() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 550);
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

        panel.setLayout(new GridLayout(4, 2));
        panel.setEnabled(false);
        panel.setBackground(Color.black);

        for (int i = 0; i < 8; i++) {
            buttons[i] = new JButton();
            panel.add(buttons[i]);
            buttons[i].setFocusable(false);
            if (i % 2 == 0 && pieceHeld.equals("wPawn")) {
                buttons[i].addActionListener(this);
            } else if (i % 2 == 1 && pieceHeld.equals("bPawn")) {
                buttons[i].addActionListener(this);
            }
            switch (i) {
                case 0 -> buttons[i].setText("White Queen");
                case 2 -> buttons[i].setText("White Knight");
                case 4 -> buttons[i].setText("White Bishop");
                case 6 -> buttons[i].setText("White Rook");
                case 1 -> buttons[i].setText("Black Queen");
                case 3 -> buttons[i].setText("Black Knight");
                case 5 -> buttons[i].setText("Black Bishop");
                case 7 -> buttons[i].setText("Black Rook");
            }
            buttons[i].setFont(new Font("Verdana", Font.PLAIN, 20));
        }
        titlePanel.add(textField);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(panel);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 8; i++) {
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
        placePiece(location, newIcon, name);
    }
}