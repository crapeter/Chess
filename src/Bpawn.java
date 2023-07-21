import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

public class Bpawn extends BuildChess implements Images {
    public static String[] whitePieces = { "wBishop", "wKing", "wKnight", "wPawn", "wQueen", "wRook" };
    public static void move(int location, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        if (location / 8 == 1) {
            display(location, buttons, pieceLoc, true);
            diagDisplay(location, buttons, pieceLoc);
        }
        // shows all possible moves if not first time moving
        else if (location / 8 != 7) {
            display(location, buttons, pieceLoc, false);
            diagDisplay(location, buttons, pieceLoc);
        }
    }
    public static void display(int loc, JButton[] buttons, HashMap<Integer, String> pieceLoc, boolean firstMove) {
        if (firstMove) {
            if (pieceLoc.containsKey(loc + 8)) {
                blockPawn(loc + 8, buttons, Color.red);
            }
            else if (pieceLoc.containsKey(loc + 16)) {
                displayMoves(loc + 8, buttons, Color.yellow);
                blockPawn(loc + 16, buttons, Color.red);
            }
            else {
                displayMoves(loc + 8, buttons, Color.yellow);
                displayMoves(loc + 16, buttons, Color.yellow);
            }
        }
        else {
            if (pieceLoc.containsKey(loc + 8)) {
                blockPawn(loc + 8, buttons, Color.red);
            }
            else {
                displayMoves(loc + 8, buttons, Color.yellow);
            }
        }
    }
    public static void diagDisplay(int loc, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        for (String piece: whitePieces) {
            if (pieceLoc.containsKey(loc + 7) && pieceLoc.get(loc + 7).equals(piece) && loc % 8 != 0) {
                displayMoves(loc + 7, buttons, Color.red);
            }
            if (pieceLoc.containsKey(loc + 9) && pieceLoc.get(loc + 9).equals(piece) && loc % 8 != 7) {
                displayMoves(loc + 9, buttons, Color.red);
            }
            //checking for en passant
            if (pieceLoc.containsKey(loc - 1) && pieceLoc.get(loc - 1).equals(piece) && loc % 8 != 0 && loc / 8 == 4) {
                if (Math.abs(whitePawnMove[0][(location - 1) % 8] - whitePawnMove[1][(location - 1) % 8]) == 16) {
                    displayMoves(loc + 7, buttons, Color.red);
                }
            }
            if (pieceLoc.containsKey(loc + 1) && pieceLoc.get(loc + 1).equals(piece) && loc % 8 != 7 && loc / 8 == 4) {
                if (Math.abs(whitePawnMove[0][(location + 1) % 8] - whitePawnMove[1][(location + 1) % 8]) == 16) {
                    displayMoves(loc + 9, buttons, Color.red);
                }
            }
        }
    }
}