import javax.swing.*;
import java.awt.*;

public abstract class HelperFunctions implements SetupVars{
    public static int location = 0;
    public static int bKingLocation = 4;
    public static int wKingLocation = 60;
    public static boolean holdingPiece = false;
    public static boolean canBlackCastle = true;
    public static boolean canWhiteCastle = true;
    public static String pieceHeld = "";
    public static String[] blackPieces = { "bBishop", "bKing", "bKnight", "bPawn", "bQueen", "bRook" };
    public static ImageIcon icon = null;

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
    public static void displayMoves(int loc, JButton[] buttons, Color color) {
        buttons[loc].setText("a");
        buttons[loc].setForeground(color);
    }
}