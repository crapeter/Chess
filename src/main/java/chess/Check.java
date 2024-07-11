package chess;

import java.awt.*;

class Check extends PieceUtils {
  private static final int BOARD_SIZE = 64;
  private static final int ROW_LENGTH = 8;
  private static int kingLoc;

  public Check(int loc) {
    kingLoc = loc;
  }

  public void checkLoc() {
    if (isKingSafe()) {
      displayMoves(kingLoc, buttons);
    } else if (!pieceLoc.containsKey(kingLoc)) {
      buttons[kingLoc].setBackground(Color.red.darker());
    }
  }

  public static boolean checkForCheck(int loc) {
    kingLoc = loc;
    if (!isKingSafe()) {
      textField.setText(currentlyWhite ? "White's in check" : "Black's in check");
      return true;
    }
    return false;
  }

  private static boolean isKingSafe() {
    return vertical() && horizontal()
        && rightDiagonal() && leftDiagonal()
        && checkKnight(kingLoc) && checkPawn(kingLoc);
  }

  private static boolean vertical() {
    return moveDirectionally(-ROW_LENGTH) && moveDirectionally(ROW_LENGTH);
  }

  private static boolean horizontal() {
    return moveHorizontally(-1) && moveHorizontally(1);
  }

  private static boolean rightDiagonal() {
    return moveDiagonally(-ROW_LENGTH + 1) && moveDiagonally(ROW_LENGTH + 1);
  }

  private static boolean leftDiagonal() {
    return moveDiagonally(-ROW_LENGTH - 1) && moveDiagonally(ROW_LENGTH - 1);
  }

  private static boolean checkKnight(int loc) {
    int[] knightLoc1 = { loc - 10, loc - 6, loc + 6, loc + 10 };
    int[] knightLoc2 = { loc - 17, loc - 15, loc + 15, loc + 17 };
    for (int i : knightLoc1) {
      // checks the locations of possible knights that are within 1 row of the new
      // location
      boolean inLine = Math.abs((loc / 8) - (i / 8)) == 1;
      boolean inBoundaries = 0 <= i && i < 64;
      if (inBoundaries && inLine && pieceHeld.equals("wKing") && pieceLoc.containsKey(i)) {
        if (pieceLoc.get(i).equals("bKnight")) {
          return false;
        }
      } else if (inBoundaries && inLine && pieceHeld.equals("bKing") && pieceLoc.containsKey(i)) {
        if (pieceLoc.get(i).equals("wKnight")) {
          return false;
        }
      }
    }
    for (int i : knightLoc2) {
      boolean inLine = Math.abs((loc / 8) - (i / 8)) == 2;
      boolean inBoundaries = 0 <= i && i < 64;
      // checks the locations of possible knights that are within 2 row of the new
      // location
      if (inBoundaries && inLine && currentlyWhite && pieceLoc.containsKey(i)) {
        if (pieceLoc.get(i).equals("bKnight")) {
          return false;
        }
      } else if (inBoundaries && inLine && !currentlyWhite && pieceLoc.containsKey(i)) {
        if (pieceLoc.get(i).equals("wKnight")) {
          return false;
        }
      }
    }
    return true;
  }

  private static boolean checkPawn(int loc) {
    int[] blackPawnLoc = { loc - 9, loc - 7 };
    int[] whitePawnLoc = { loc + 9, loc + 7 };
    if (currentlyWhite) {
      // checks both possible locations that a pawn could be if the new location
      for (int i : blackPawnLoc) {
        if (0 <= i && pieceLoc.containsKey(i) && pieceLoc.get(i).equals("bPawn")
            && Math.abs((loc / 8) - (i / 8)) == 1) {
          return false;
        }
      }
    } else {
      // checks both possible locations that a pawn could be if the new location
      for (int i : whitePawnLoc) {
        if (i < 64 && pieceLoc.containsKey(i) && pieceLoc.get(i).equals("wPawn")
            && Math.abs((loc / 8) - (i / 8)) == 1) {
          return false;
        }
      }
    }
    return true;
  }

  private static boolean moveDirectionally(int increment) {
    for (int i = kingLoc + increment; i >= 0 && i < BOARD_SIZE; i += increment) {
      // return false if the piece is a rook because the king isn't safe
      if (checking(i, "Rook")) {
        if (currentlyWhite)
          blackCheckLocations.add(kingLoc + increment);
        else
          whiteCheckLocations.add(kingLoc + increment);
        return false;
      } else if (pieceLoc.containsKey(i) && !pieceLoc.get(i).equals("wKing") && !pieceLoc.get(i).equals("bKing")) {
        return true;
      }
    }
    return true;
  }

  private static boolean moveHorizontally(int increment) {
    for (int i = kingLoc + increment; i >= 0 && i < BOARD_SIZE
        && i / ROW_LENGTH == kingLoc / ROW_LENGTH; i += increment) {
      // return false if the piece is a rook because the king isn't safe
      if (checking(i, "Rook")) {
        if (currentlyWhite)
          blackCheckLocations.add(kingLoc + increment);
        else
          whiteCheckLocations.add(kingLoc + increment);
        return false;
      } else if (pieceLoc.containsKey(i) && !pieceLoc.get(i).equals("wKing") && !pieceLoc.get(i).equals("bKing")) {
        return true;
      }
    }
    return true;
  }

  private static boolean moveDiagonally(int increment) {
    for (int i = kingLoc + increment; i >= 0 && i < BOARD_SIZE; i += increment) {
      // checks if the bottom right or top right diags go out of bounds
      // otherwise checks if the bottom left or top left diags go out of bounds
      if ((increment == 9 || increment == -7) && i % ROW_LENGTH == 0) {
        break;
      } else if ((increment == -9 || increment == 7) && i % ROW_LENGTH == 7) {
        break;
      }
      // return false if the piece is a bishop because the king isn't safe
      if (checking(i, "Bishop")) {
        if (currentlyWhite)
          blackCheckLocations.add(kingLoc + increment);
        else
          whiteCheckLocations.add(kingLoc + increment);
        return false;
      } else if (pieceLoc.containsKey(i) && !pieceLoc.get(i).equals("wKing") && !pieceLoc.get(i).equals("bKing")) {
        return true;
      }
    }
    return true;
  }

  private static boolean checking(int loc, String secondary) {
    String opponentQueen = currentlyWhite ? "bQueen" : "wQueen";
    String opponentPiece = currentlyWhite ? "b" + secondary : "w" + secondary;

    if (pieceLoc.containsKey(loc)) {
      String pieceAtLoc = pieceLoc.get(loc);
      return pieceAtLoc.equals(opponentQueen) || pieceAtLoc.equals(opponentPiece);
    }
    return false;
  }
}