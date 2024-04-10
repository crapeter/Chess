package chess;

import java.awt.*;

class Check extends PieceUtils {
  private static final int BOARD_SIZE = 64;
  private static final int ROW_LENGTH = 8;
  private final int kingLoc;

  public Check(int loc) {
    this.kingLoc = loc;
  }

  public void checkLoc() {
    if (isKingSafe()) {
      displayMoves(kingLoc, buttons);
    } else if (!pieceLoc.containsKey(kingLoc)) {
      buttons[kingLoc].setBackground(Color.red.darker());
    }
  }

  private boolean isKingSafe() {
    return vertical() && horizontal()
        && rightDiagonal() && leftDiagonal()
        && checkKnight(kingLoc) && checkPawn(kingLoc);
  }

  private boolean vertical() {
    return moveDirectionally(-ROW_LENGTH) && moveDirectionally(ROW_LENGTH);
  }

  private boolean horizontal() {
    return moveHorizontally(-1) && moveHorizontally(1);
  }

  private boolean rightDiagonal() {
    return moveDiagonally(-ROW_LENGTH + 1) && moveDiagonally(ROW_LENGTH + 1);
  }

  private boolean leftDiagonal() {
    return moveDiagonally(-ROW_LENGTH - 1) && moveDiagonally(ROW_LENGTH - 1);
  }

  private boolean checkKnight(int loc) {
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
    return true;
  }

  private boolean checkPawn(int loc) {
    int[] blackPawnLoc = { loc - 9, loc - 7 };
    int[] whitePawnLoc = { loc + 9, loc + 7 };
    if (pieceHeld.equals("wKing")) {
      // checks both possible locations that a pawn could be if the new location
      for (int i : blackPawnLoc) {
        if (0 <= i && pieceLoc.containsKey(i) && pieceLoc.get(i).equals("bPawn")
            && Math.abs((loc / 8) - (i / 8)) == 1) {
          return false;
        }
      }
    } else if (pieceHeld.equals("bKing")) {
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

  private boolean moveDirectionally(int increment) {
    for (int i = kingLoc + increment; i >= 0 && i < BOARD_SIZE; i += increment) {
      // return false if the piece is a rook because the king isn't safe
      if (checking(i, "Rook")) {
        return false;
      } else if (pieceLoc.containsKey(i) && !pieceLoc.get(i).equals("wKing") && !pieceLoc.get(i).equals("bKing")) {
        return true;
      }
    }
    return true;
  }

  private boolean moveHorizontally(int increment) {
    for (int i = kingLoc + increment; i >= 0 && i < BOARD_SIZE
        && i / ROW_LENGTH == kingLoc / ROW_LENGTH; i += increment) {
      // return false if the piece is a rook because the king isn't safe
      if (checking(i, "Rook")) {
        return false;
      } else if (pieceLoc.containsKey(i) && !pieceLoc.get(i).equals("wKing") && !pieceLoc.get(i).equals("bKing")) {
        return true;
      }
    }
    return true;
  }

  private boolean moveDiagonally(int increment) {
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
        return false;
      } else if (pieceLoc.containsKey(i) && !pieceLoc.get(i).equals("wKing") && !pieceLoc.get(i).equals("bKing")) {
        return true;
      }
    }
    return true;
  }

  private boolean checking(int loc, String secondary) {
    String opponentQueen = pieceHeld.equals("wKing") ? "bQueen" : "wQueen";
    String opponentPiece = pieceHeld.equals("wKing") ? "b" + secondary : "w" + secondary;

    if (pieceLoc.containsKey(loc)) {
      String pieceAtLoc = pieceLoc.get(loc);
      return pieceAtLoc.equals(opponentQueen) || pieceAtLoc.equals(opponentPiece);
    }
    return false;
  }
}
