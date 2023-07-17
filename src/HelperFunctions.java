import javax.swing.*;

public abstract class HelperFunctions implements setupVars{
    public static int location = 0;
    public static boolean holdingPiece = false;
    public static String pieceHeld = "";
    public static ImageIcon icon = null;

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
}