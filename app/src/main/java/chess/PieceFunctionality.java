package chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class PieceFunctionality extends PieceUtils implements ActionListener, SetupVars, Images {
    public static int[][] whitePawnMove = new int[2][8];
    public static int[][] blackPawnMove = new int[2][8];
    public static boolean gameOver = false;

    public void addFunctionality() {
        int firstWhitePawn = 48;
        int firstBlackPawn = 8;
        for (int i = 0; i < 64; i++) {
            buttons[i].addActionListener(this);
        }
        for (int i = 0; i < 8; i++) {
            whitePawnMove[0][i] = firstWhitePawn + i;
            blackPawnMove[0][i] = firstBlackPawn + i;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean whiteKing = false;
        boolean blackKing = false;
        String[] whitePieces = {"wPawn", "wRook", "wKnight", "wBishop", "wQueen", "wKing"};
        List<String> whitePiece = Arrays.asList(whitePieces);
        for (int i = 0; i < 64; i++) {
            if (e.getSource() == buttons[i]) {
                if (pieceLoc.containsKey(i) && !holdingPiece && !promoting) {
                    if ( (currentlyWhite && whitePiece.contains(pieceLoc.get(i))) || (!currentlyWhite && !whitePiece.contains(pieceLoc.get(i))) ) {
                        Move.grab(i);
                    } else {
                        String color = currentlyWhite ? "white" : "black";
                        System.out.println("It is " + color + "'s turn to move bozo");
                    }
                    break;
                } else if (pieceLoc.containsKey(i) && holdingPiece && !promoting) {
                    if ( (currentlyWhite && whitePiece.contains(pieceLoc.get(i))) || (!currentlyWhite && !whitePiece.contains(pieceLoc.get(i))) ) {
                        placePiece(location, icon, pieceHeld);
                        resetBoardColor();
                        Move.grab(i);
                        break;
                    } else {
                        Move.take(i);
                        resetBoardColor();
                    }
                } else if (!pieceLoc.containsKey(i) && !holdingPiece && !promoting) {
                    System.out.println("Pick up a piece bozo");
                } else if (!pieceLoc.containsKey(i) && holdingPiece && !promoting) {
                    Move.empty(i);
                    resetBoardColor();
                }
                for (int j = 0; j < 64; j++) {
                    if (!buttons[j].getText().equals("")) {
                        buttons[j].setText("");
                    }
                    if (pieceLoc.containsKey(j) && pieceLoc.get(j).equals("bKing")) {
                        blackKing = true;
                    }
                    if (pieceLoc.containsKey(j) && pieceLoc.get(j).equals("wKing")) {
                        whiteKing = true;
                    }
                }
                if (!whiteKing || !blackKing) {
                    gameOver = true;
                    for (int j = 0; j < 64; j++) {
                        buttons[j].removeActionListener(this);
                    }
                    textField.setText(whiteKing ? "White wins" : "Black wins");
                }
                System.out.println(pieceHeld + " " + i);
            }
        }
    }
}

