import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

public class Wpawn extends BuildChess implements Images {
    public static String[] blackPieces = { "bBishop", "bKing", "bKnight", "bPawn", "bQueen", "bRook" };
    public static void move(int location, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        if (location / 8 == 6) {
            display(location, buttons, pieceLoc, true);
            diagDisplay(location, buttons, pieceLoc);
        }
        // shows all possible moves if not first time moving
        else if (location / 8 != 0) {
            display(location, buttons, pieceLoc, false);
            diagDisplay(location, buttons, pieceLoc);
        }
    }
    public static void display(int loc, JButton[] buttons, HashMap<Integer, String> pieceLoc, boolean firstMove) {
        if (firstMove) {
            if (pieceLoc.containsKey(loc - 8)) {
                blockPawn(loc - 8, buttons, Color.red);
            }
            else if (pieceLoc.containsKey(loc - 16)) {
                displayMoves(loc - 8, buttons, Color.yellow);
                blockPawn(loc - 16, buttons, Color.red);
            }
            else {
                displayMoves(loc - 8, buttons, Color.yellow);
                displayMoves(loc - 16, buttons, Color.yellow);
            }
        }
        else {
            if (pieceLoc.containsKey(loc - 8)) {
                blockPawn(loc - 8, buttons, Color.red);
            }
            else {
                displayMoves(loc - 8, buttons, Color.yellow);
            }
        }
    }
    public static void diagDisplay(int loc, JButton[] buttons, HashMap<Integer, String> pieceLoc) {
        for (String piece: blackPieces) {
            if (pieceLoc.containsKey(loc - 7) && pieceLoc.get(loc - 7).equals(piece) && loc % 8 != 7) {
                displayMoves(loc - 7, buttons, Color.red);
            }
            if (pieceLoc.containsKey(loc - 9) && pieceLoc.get(loc - 9).equals(piece) && loc % 8 != 0) {
                displayMoves(loc - 9, buttons, Color.red);
            }
            //checking for en passant
            if (pieceLoc.containsKey(loc - 1) && pieceLoc.get(loc - 1).equals(piece) && loc % 8 != 0 && loc / 8 == 3) {
                if (Math.abs(blackPawnMove[0][(location - 1) % 8] - blackPawnMove[1][(location - 1) % 8]) == 16) {
                    displayMoves(loc - 9, buttons, Color.red);
                }
            }
            if (pieceLoc.containsKey(loc + 1) && pieceLoc.get(loc + 1).equals(piece) && loc % 8 != 7 && loc / 8 == 3) {
                if (Math.abs(blackPawnMove[0][(location + 1) % 8] - blackPawnMove[1][(location + 1) % 8]) == 16) {
                    displayMoves(loc - 7, buttons, Color.red);
                }
            }
        }
    }
}