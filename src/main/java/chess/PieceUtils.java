package chess;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class PieceUtils implements SetupVars, Images {
  public static int location = 0;
  public static int numberOfMoves = 0;
  public static boolean holdingPiece = false;
  public static boolean canBlackCastle1 = true;
  public static boolean canWhiteCastle1 = true;
  public static boolean canBlackCastle2 = true;
  public static boolean canWhiteCastle2 = true;
  public static boolean promoting = false;
  public static boolean gameOver = false;
  public static boolean currentlyWhite = true;
  public static String pieceHeld = "";
  public static ImageIcon icon = null;

  public static int whiteKingLocation = 60;
  public static int blackKingLocation = 4;
  public static HashMap<String, Integer> whiteCheckLocations = new HashMap<>();
  public static HashMap<String, Integer> blackCheckLocations = new HashMap<>();

  private static ImageIcon takingIcon = null;
  private static final String[] whitePieces = { "wPawn", "wRook", "wKnight", "wBishop", "wQueen", "wKing" };
  private static final List<String> whitePiece = Arrays.asList(whitePieces);

  public static void swapPiece(int oldLoc, int newLoc, ImageIcon currentIcon, String name) {
    pieceLoc.remove(oldLoc, name);
    pieceLoc.put(newLoc, name);
    buttons[oldLoc].setIcon(null);
    buttons[newLoc].setIcon(currentIcon);
    holdingPiece = false;
  }

  public static void removePiece(int loc, String pieceName, boolean enPas) {
    boolean isWhitePiece = whitePiece.contains(pieceHeld);
    boolean isPawn = false;
    if (enPas) {
      takingIcon = pieceName.equals("wPawn") ? wPawn : bPawn;
    } else {
      takingIcon = getIcon(loc);
    }
    if (pieceLoc.get(loc).equals("wPawn") || pieceLoc.get(loc).equals("bPawn")) {
      isPawn = true;
    }
    pieceLoc.remove(loc, pieceName);
    buttons[loc].setIcon(null);
    Draw.addCapturedPiece(takingIcon, isWhitePiece, isPawn);
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
    buttons[loc].setBackground(Color.green.brighter());
  }

  public static void displayMoves(int loc, JButton[] buttons) {
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
    if (currentlyWhite) {
      textField.setText("White's turn");
    } else {
      textField.setText("Black's turn");
    }
    for (int i = 0; i < 64; i++) {
      if (white) {
        buttons[i].setBackground(new Color(238, 238, 210));
      } else {
        buttons[i].setBackground(new Color(118, 150, 86));
      }
      if (buttons[i].getText().equals("a")) {
        buttons[i].setText("");
      }
      if (i % 8 != 7)
        white = !white;
    }
  }

  public static int getCheckDirection(int attackingLocation) {
    int kingRow = currentlyWhite ? whiteKingLocation / 8 : blackKingLocation / 8;
    int kingCol = currentlyWhite ? whiteKingLocation % 8 : blackKingLocation % 8;
    int attackingRow = attackingLocation / 8;
    int attackingCol = attackingLocation % 8;

    int rowDiff = kingRow - attackingRow;
    int colDiff = kingCol - attackingCol;

    if (rowDiff == 0) {
      return colDiff > 0 ? -1 : 1;
    } else if (colDiff == 0) {
      return rowDiff > 0 ? -8 : 8;
    } else if (rowDiff == colDiff) {
      return rowDiff > 0 ? -9 : 9;
    } else if (rowDiff == -colDiff) {
      return rowDiff > 0 ? -7 : 7;
    }
    return 0;
  }

  public static TimerTask setTitle(String title) {
    return new TimerTask() {
      @Override
      public void run() {
        textField.setText(title);
      }
    };
  }

  private static ImageIcon getIcon(int loc) {
    switch (pieceLoc.get(loc)) {
      case "wPawn" -> takingIcon = wPawn;
      case "bPawn" -> takingIcon = bPawn;
      case "wKing" -> takingIcon = wKing;
      case "bKing" -> takingIcon = bKing;
      case "wQueen" -> takingIcon = wQueen;
      case "bQueen" -> takingIcon = bQueen;
      case "wBishop" -> takingIcon = wBishop;
      case "bBishop" -> takingIcon = bBishop;
      case "wKnight" -> takingIcon = wKnight;
      case "bKnight" -> takingIcon = bKnight;
      case "wRook" -> takingIcon = wRook;
      case "bRook" -> takingIcon = bRook;
    }
    return takingIcon;
  }
}