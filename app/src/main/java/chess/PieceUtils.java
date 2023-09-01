package chess;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public abstract class PieceUtils implements SetupVars {
    public static int location = 0;
    public static boolean holdingPiece = false;
    public static boolean canBlackCastle1 = true;
    public static boolean canWhiteCastle1 = true;
    public static boolean canBlackCastle2 = true;
    public static boolean canWhiteCastle2 = true;
    public static boolean promoting = false;
    public static String pieceHeld = "";
    public static ImageIcon icon = null;
    public static boolean currentlyWhite = true;

    public static void swapPiece(int oldLoc, int newLoc, ImageIcon currentIcon, String name) {
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
    public static void displayMoves(int loc, JButton[] buttons) {
        String[] whitePieces = {"wPawn", "wRook", "wKnight", "wBishop", "wQueen", "wKing"};
        List<String> whitePiece = Arrays.asList(whitePieces);
        if (0 <= loc && loc < 64) {
            if (pieceLoc.containsKey(loc)) {
                boolean movingWhite = whitePiece.contains(pieceLoc.get(loc));
                boolean currentWhite = whitePiece.contains(pieceLoc.get(location));
                if (movingWhite == !currentWhite) {
                    buttons[loc].setText("a");
                    buttons[loc].setBackground(Color.yellow);
                    buttons[loc].setForeground(Color.yellow);
                }
            } else {
                buttons[loc].setText("a");
                buttons[loc].setBackground(Color.yellow);
                buttons[loc].setForeground(Color.yellow);
            }
        }
    }

    public static void resetBoardColor() {
        boolean white = true;
        for (int i = 0; i < 64; i++) {
            if (white)
                buttons[i].setBackground(new Color(238, 238, 210));
            else
                buttons[i].setBackground(new Color(118, 150, 86));
            if (i % 8 != 7)
                white = !white;
        }
    }
}