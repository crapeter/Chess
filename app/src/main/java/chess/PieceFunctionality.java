package chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PieceFunctionality extends HelperFunctions implements ActionListener, SetupVars, Images {
    public static int[][] whitePawnMove = new int[2][8];
    public static int[][] blackPawnMove = new int[2][8];
    public static boolean gameOver = false;
    int firstWhitePawn = 48;
    int firstBlackPawn = 8;

    public void addFunctionality() {
        for (int i = 0; i < 64; i++)
            buttons[i].addActionListener(this);
        for (int i = 0; i < 8; i++) {
            whitePawnMove[0][i] = firstWhitePawn + i;
            blackPawnMove[0][i] = firstBlackPawn + i;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean whiteKing = false;
        boolean blackKing = false;
        for (int i = 0; i < 64; i++) {
            if (e.getSource() == buttons[i]) {
                if (pieceLoc.containsKey(i) && !holdingPiece && !promoting) {
                    Move.grab(i);
                    break;
                } else if (pieceLoc.containsKey(i) && holdingPiece && !promoting) {
                    Move.contains(i);
                } else if (!pieceLoc.containsKey(i) && !holdingPiece && !promoting) {
                    System.out.println("Pick up a piece bozo");
                } else if (!pieceLoc.containsKey(i) && holdingPiece && !promoting) {
                    Move.empty(i);
                }
                for (int j = 0; j < 64; j++) {
                    if (!buttons[j].getText().equals(""))
                        buttons[j].setText("");
                    if (pieceLoc.containsKey(j) && pieceLoc.get(j).equals("bKing"))
                        blackKing = true;
                    if (pieceLoc.containsKey(j) && pieceLoc.get(j).equals("wKing"))
                        whiteKing = true;
                }
                if (!whiteKing || !blackKing) {
                    gameOver = true;
                    for (int j = 0; j < 64; j++)
                        buttons[j].removeActionListener(this);
                    textField.setText(whiteKing ? "White wins" : "Black wins");
                }
                System.out.println(pieceHeld + " " + i);
            }
        }
    }
}

