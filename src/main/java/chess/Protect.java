package chess;

public class Protect extends PieceUtils {
  private static int loc;

  public Protect(int location) {
    loc = location;
  }

  public boolean canProtect() {
    return vertical() || horizontal() ||
        rightDiagonal() || leftDiagonal() ||
        knight() || pawn();
  }

  private static boolean vertical() {
    return moveDirectionally(-8) || moveDirectionally(8);
  }

  private static boolean horizontal() {
    return moveHorizontally(-1) || moveHorizontally(1);
  }

  private static boolean rightDiagonal() {
    return moveDiagonally(-7) || moveDiagonally(9);
  }

  private static boolean leftDiagonal() {
    return moveDiagonally(-9) || moveDiagonally(7);
  }

  private static boolean moveDirectionally(int dir) {
    for (int i = loc + dir; 0 <= i && i < 64; i += dir) {
      if (pieceLoc.containsKey(i)) {
        if (currentlyWhite) {
          return pieceLoc.get(i).equals("wRook") || pieceLoc.get(i).equals("wQueen");
        } else {
          return pieceLoc.get(i).equals("bRook") || pieceLoc.get(i).equals("bQueen");
        }
      }
    }
    return false;
  }

  private static boolean moveHorizontally(int dir) {
    for (int i = loc + dir; i >= 0 && i < 64 && i / 8 == loc / 8; i += dir) {
      if (pieceLoc.containsKey(i)) {
        if (currentlyWhite) {
          return pieceLoc.get(i).equals("wRook") || pieceLoc.get(i).equals("wQueen");
        } else {
          return pieceLoc.get(i).equals("bRook") || pieceLoc.get(i).equals("bQueen");
        }
      }
    }
    return false;
  }

  private static boolean moveDiagonally(int dir) {
    for (int i = loc + dir; 0 <= i && i < 64; i += dir) {
      if ((dir == 9 || dir == -7) && i % 8 == 0) {
        break;
      } else if ((dir == -9 || dir == 7) && i % 8 == 7) {
        break;
      }
      if (pieceLoc.containsKey(i)) {
        if (currentlyWhite) {
          return pieceLoc.get(i).equals("wBishop") || pieceLoc.get(i).equals("wQueen");
        } else {
          return pieceLoc.get(i).equals("bBishop") || pieceLoc.get(i).equals("bQueen");
        }
      }
    }
    return false;
  }

  private static boolean knight() {
    int[] knightLoc1 = {loc - 10, loc - 6, loc + 6, loc + 10};
    int[] knightLoc2 = {loc - 17, loc - 15, loc + 15, loc + 17};
    for (int i : knightLoc1) {
      boolean inLine = Math.abs((location / 8) - (i / 8)) == 1;
      boolean inBoundaries = 0 <= i && i < 64;
      if (inLine && inBoundaries) {
        return pieceLoc.containsKey(i) &&
            pieceLoc.get(i).equals(currentlyWhite ? "wKnight" : "bKnight");
      }
    }
    for (int i : knightLoc2) {
      boolean inLine = Math.abs((location / 8) - (i / 8)) == 2;
      boolean inBoundaries = 0 <= i && i < 64;
      if (inLine && inBoundaries) {
        return pieceLoc.containsKey(i) &&
            pieceLoc.get(i).equals(currentlyWhite ? "wKnight" : "bKnight");
      }
    }
    return false;
  }

  private static boolean pawn() {
    if (currentlyWhite) {
      if (loc / 8 == 4) {
        if (!pieceLoc.containsKey(loc + 8) && pieceLoc.containsKey(loc + 16) &&
            pieceLoc.get(loc + 16).equals("wPawn")) {
          return true;
        } else {
          return pieceLoc.containsKey(loc + 8) && pieceLoc.get(loc + 8).equals("wPawn");
        }
      }
      return pieceLoc.containsKey(loc + 8) && pieceLoc.get(loc + 8).equals("wPawn");
    } else {
      if (loc / 8 == 3) {
        if (!pieceLoc.containsKey(loc - 8) && pieceLoc.containsKey(loc - 16) &&
            pieceLoc.get(loc - 16).equals("bPawn")) {
          return true;
        } else {
          return pieceLoc.containsKey(loc - 8) && pieceLoc.get(loc - 8).equals("bPawn");
        }
      }
      return pieceLoc.containsKey(loc - 8) && pieceLoc.get(loc - 8).equals("bPawn");
    }
  }
}