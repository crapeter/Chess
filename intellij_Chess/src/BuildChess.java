import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildChess extends HelperFunctions implements ActionListener, SetupVars, Images {
    public static int[][] whitePawnMove = new int[2][8];
    public static int[][] blackPawnMove = new int[2][8];
    int firstWhitePawn = 48;
    int firstBlackPawn = 8;
    public static boolean gameOver = false;

    public void place() {
        for (int i = 0; i < 64; i++) {
            buttons[i].addActionListener(this);
            switch (i / 8) {
                case 1 -> placePiece(i, bPawn, "bPawn");
                case 6 -> placePiece(i, wPawn, "wPawn");
            }
            switch (i) {
                case 0, 7 -> placePiece(i, bRook, "bRook");
                case 1, 6 -> placePiece(i, bKnight, "bKnight");
                case 2, 5 -> placePiece(i, bBishop, "bBishop");
                case 3 -> placePiece(i, bQueen, "bQueen");
                case 4 -> placePiece(i, bKing, "bKing");
                case 56, 63 -> placePiece(i, wRook, "wRook");
                case 57, 62 -> placePiece(i, wKnight, "wKnight");
                case 58, 61 -> placePiece(i, wBishop, "wBishop");
                case 59 -> placePiece(i, wQueen, "wQueen");
                case 60 -> placePiece(i, wKing, "wKing");
            }
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
        for (int i = 0; i < 64; i++) {
            if (e.getSource() == buttons[i]) {
                if (pieceLoc.containsKey(i)) {
                    if (!holdingPiece) {
                        Move.grab(i);
                        break;
                    } else Move.contains(i);
                } else {
                    if (!holdingPiece)
                        System.out.println("Pick up a piece bozo");
                    else Move.empty(i);
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
