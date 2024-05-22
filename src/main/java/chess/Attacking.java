package chess;

public class Attacking extends PieceUtils {
  private static final int BOARD_SIZE = 64;
  private static final int ROW_LENGTH = 8;
  private final int newPieceLoc;

  public Attacking(int loc) {
    this.newPieceLoc = loc;
  }

  public void isAttacking() {
    if (attacking()) {
      textField.setText(currentlyWhite ? "White's in check" : "Black's in check");
    }
  }

  private boolean attacking() {
    switch (pieceHeld) {
      case "wPawn", "bPawn" -> {
        return pawn();
      }
      case "wKnight", "bKnight" -> {
        return knight();
      }
      case "wRook", "bRook" -> {
        return vertical() || horizontal();
      }
      case "wBishop", "bBishop" -> {
        return rightDiagonal() || leftDiagonal();
      }
      case "wQueen", "bQueen" -> {
        return vertical() || horizontal() || rightDiagonal() || leftDiagonal();
      }
      default -> {
        System.out.println("The king can't attack another king");
        return false;
      }
    }
  }

  private boolean vertical() {
    return moveVertically(-ROW_LENGTH) || moveVertically(ROW_LENGTH);
  }

  private boolean horizontal() {
    return moveHorizontally(-1) || moveHorizontally(1);
  }

  private boolean rightDiagonal() {
    return moveDiagonally(-ROW_LENGTH + 1) || moveDiagonally(ROW_LENGTH + 1);
  }

  private boolean leftDiagonal() {
    return moveDiagonally(-ROW_LENGTH - 1) || moveDiagonally(ROW_LENGTH - 1);
  }

  private boolean knight() {
    int[] knightLoc1 = { newPieceLoc - 10, newPieceLoc - 6, newPieceLoc + 6, newPieceLoc + 10 };
    int[] knightLoc2 = { newPieceLoc - 17, newPieceLoc - 15, newPieceLoc + 15, newPieceLoc + 17 };
    for (int i : knightLoc1) {
      // checks the locations of possible knights that are within 1 row of the new
      // location
      boolean inLine = Math.abs((newPieceLoc / 8) - (i / 8)) == 1;
      boolean inBound = 0 <= i && i < 64;
      if (inBound && inLine && !currentlyWhite && pieceLoc.containsKey(i) && pieceLoc.get(i).equals("bKing")) {
        return true;
      } else if (inBound && inLine && currentlyWhite && pieceLoc.containsKey(i) && pieceLoc.get(i).equals("wKing")) {
        return true;
      }
    }
    for (int i : knightLoc2) {
      // checks the locations of possible knights that are within 2 row of the new
      // location
      boolean inLine = Math.abs((newPieceLoc / 8) - (i / 8)) == 2;
      boolean inBound = 0 <= i && i < 64;
      if (inBound && inLine && !currentlyWhite && pieceLoc.containsKey(i) && pieceLoc.get(i).equals("bKing")) {
        return true;
      } else if (inBound && inLine && currentlyWhite && pieceLoc.containsKey(i) && pieceLoc.get(i).equals("wKing")) {
        return true;
      }
    }
    return false;
  }

  private boolean pawn() {
    int[] whitePawnLoc = { newPieceLoc - 9, newPieceLoc - 7 };
    int[] blackPawnLoc = { newPieceLoc + 9, newPieceLoc + 7 };
    if (currentlyWhite) {
      // checks both possible locations that a pawn could be if the new location
      for (int i : blackPawnLoc) {
        if (0 <= i && pieceLoc.containsKey(i) && pieceLoc.get(i).equals("wKing")
            && Math.abs((newPieceLoc / 8) - (i / 8)) == 1) {
          return true;
        }
      }
    } else {
      // checks both possible locations that a pawn could be if the new location
      for (int i : whitePawnLoc) {
        if (i < 64 && pieceLoc.containsKey(i) && pieceLoc.get(i).equals("bKing")
            && Math.abs((newPieceLoc / 8) - (i / 8)) == 1) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean moveVertically(int increment) {
    for (int i = newPieceLoc + increment; i >= 0 && i < BOARD_SIZE; i += increment) {
      // checks to see if a king is in the same column as the current piece
      if (pieceLoc.containsKey(i)) {
        if (currentlyWhite) {
          return pieceLoc.get(i).equals("wKing");
        } else {
          return pieceLoc.get(i).equals("bKing");
        }
      }
    }
    return false;
  }

  private boolean moveHorizontally(int increment) {
    for (int i = newPieceLoc + increment; i >= 0 & i < BOARD_SIZE
        && i / ROW_LENGTH == newPieceLoc / ROW_LENGTH; i += increment) {
      // checks to see if a king is in the same row as the current piece
      if (pieceLoc.containsKey(i)) {
        if (currentlyWhite) {
          return pieceLoc.get(i).equals("wKing");
        } else {
          return pieceLoc.get(i).equals("bKing");
        }
      }
    }
    return false;
  }

  private boolean moveDiagonally(int increment) {
    for (int i = newPieceLoc; i >= 0 && i < BOARD_SIZE; i += increment) {
      if (i == newPieceLoc)
        continue;
      // checks if the bottom right or top right diags go out of bounds
      // otherwise checks if the bottom left or top left diags go out of bounds
      if ((increment == 9 || increment == -7) && i % ROW_LENGTH == 0) {
        break;
      } else if ((increment == -9 || increment == 7) && i % ROW_LENGTH == 7) {
        break;
      }
      // returns whether the piece is a king
      if (pieceLoc.containsKey(i)) {
        if (currentlyWhite) {
          return pieceLoc.get(i).equals("wKing");
        } else {
          return pieceLoc.get(i).equals("bKing");
        }
      }
    }
    return false;
  }
}
