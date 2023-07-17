import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class BuildChess extends Main implements ActionListener, Images {

    public static int location = 0;
    public static final int bKingLocation = 4;
    public static final int wKingLocation = 60;

    public static int[][] whitePawnMove = new int[2][8];
    public static int[][] blackPawnMove = new int[2][8];
    public static boolean holdingPiece = false;
    public static boolean canBlackCastle = true;
    public static boolean canWhiteCastle = true;
    public static boolean gameOver = false;
    public static String pieceHeld = "";
    public static String[] blackPieces = { "bBishop", "bKing", "bKnight", "bPawn", "bQueen", "bRook" };
    public static ImageIcon icon = null;

    public void constructChess() {
        for (int i = 0; i < 64; i++) {
            buttons[i].addActionListener(this);
            if (i / 8 == 1)
                placePiece(i, bPawn, "bPawn");

            if (i / 8 == 6)
                placePiece(i, wPawn, "wPawn");
        }

        for (int i = 0; i < 64; i++) {
            if (i == 0 || i == 7)
                placePiece(i, bRook, "bRook");
            else if (i == 1 || i == 6)
                placePiece(i, bKnight, "bKnight");
            else if (i == 2 || i == 5)
                placePiece(i, bBishop, "bBishop");
            else if (i == 3)
                placePiece(i, bQueen, "bQueen");
            else if (i == 4)
                placePiece(i, bKing, "bKing");
            else if (i == 56 || i == 63)
                placePiece(i, wRook, "wRook");
            else if (i == 57 || i == 62)
                placePiece(i, wKnight, "wKnight");
            else if (i == 58 || i == 61)
                placePiece(i, wBishop, "wBishop");
            else if (i == 59)
                placePiece(i, wQueen, "wQueen");
            else if (i == 60)
                placePiece(i, wKing, "wKing");
        }

        for (int i = 0; i < 8; i++) {
            int firstWhitePawn = 48;
            int firstBlackPawn = 8;

            whitePawnMove[0][i] = firstWhitePawn + i;
            whitePawnMove[1][i] = 0;
            blackPawnMove[0][i] = firstBlackPawn + i;
            blackPawnMove[1][i] = 0;
        }
    }

    public static void pieceMov(int oldLoc, int newLoc, ImageIcon currentIcon, String name) {
        pieceLoc.remove(oldLoc, name);
        pieceLoc.put(newLoc, name);
        buttons[oldLoc].setIcon(null);
        buttons[newLoc].setIcon(currentIcon);
        holdingPiece = false;
    }
    public static void removePiece(int loc, String pieceName) {
        pieceLoc.remove(loc, pieceName);
        buttons[loc].setIcon(null);
    }
    public static void pickUpPiece(int loc, String pieceName) {
        pieceLoc.remove(loc, pieceName);
        holdingPiece = true;
    }
    public static void placePiece(int loc, ImageIcon newIcon, String pieceName) {
        pieceLoc.put(loc, pieceName);
        buttons[loc].setIcon(newIcon);
    }
    public static void grabPiece(int loc, ImageIcon newIcon) {
        pieceHeld = pieceLoc.get(loc);
        location = loc;
        icon = newIcon;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 64; i++) {
            if (e.getSource() == buttons[i]) {
                if (pieceLoc.containsKey(i) && !holdingPiece) {
                    Move.grab(i);
                    break;
                }
                else if (!pieceLoc.containsKey(i)) {
                    Move.free(i);
                }
                else {
                    Move.contains(i);
                }
                for (int j = 0; j < 64; j++) {
                    if (!buttons[j].getText().equals(""))
                        buttons[j].setText("");
                }
                if (!pieceLoc.containsValue("wKing") || !pieceLoc.containsValue("bKing")) {
                    gameOver = true;
                    for (int j = 0; j < 64; j++)
                        buttons[j].removeActionListener(this);
                    textField.setText("Game Over");
                }
                System.out.println(pieceHeld + " " + i);
            }
        }
    }
}
