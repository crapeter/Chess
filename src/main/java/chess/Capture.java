package chess;

public class Capture extends PieceUtils {
  private static int location;

  public Capture(int loc) {
    location = loc;
  }

  public boolean canBeTaken() {
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
    for (int i = location + dir; 0 <= i && i < 64; i += dir) {
      if (pieceLoc.containsKey(i)) {
        if (pieceLoc.get(location).startsWith("w")) {
          return pieceLoc.get(i).equals("bRook") || pieceLoc.get(i).equals("bQueen");
        } else if (pieceLoc.get(location).startsWith("b")) {
          return pieceLoc.get(i).equals("wRook") || pieceLoc.get(i).equals("wQueen");
        }
      }
    }
    return false;
  }

  private static boolean moveHorizontally(int dir) {
    for (int i = location + dir; i >= 0 && i < 64 && i / 8 == location / 8; i += dir) {
      if (pieceLoc.containsKey(i)) {
        if (pieceLoc.get(location).startsWith("w")) {
          return pieceLoc.get(i).equals("bRook") || pieceLoc.get(i).equals("bQueen");
        } else if (pieceLoc.get(location).startsWith("b")) {
          return pieceLoc.get(i).equals("wRook") || pieceLoc.get(i).equals("wQueen");
        }
      }
    }
    return false;
  }

  private static boolean moveDiagonally(int dir) {
    for (int i = location + dir; 0 <= i && i < 64; i += dir) {
      if ((dir == 9 || dir == -7) && i % 8 == 0) {
        break;
      } else if ((dir == -9 || dir == 7) && i % 8 == 7) {
        break;
      }
      if (pieceLoc.containsKey(i)) {
        if (pieceLoc.get(location).startsWith("w")) {
          return pieceLoc.get(i).equals("bBishop") || pieceLoc.get(i).equals("bQueen");
        } else if (pieceLoc.get(location).startsWith("b")) {
          return pieceLoc.get(i).equals("wBishop") || pieceLoc.get(i).equals("wQueen");
        }
      }
    }
    return false;
  }

  private static boolean knight() {
    int[] knightLoc1 = {location - 10, location - 6, location + 6, location + 10};
    int[] knightLoc2 = {location - 17, location - 15, location + 15, location + 17};
    for (int i : knightLoc1) {
      boolean inLine = Math.abs((location / 8) - (i / 8)) == 1;
      boolean inBoundaries = 0 <= i && i < 64;
      if (inBoundaries && inLine) {
        if (!pieceLoc.containsKey(i)) continue;
        if (pieceLoc.get(location).startsWith("w")) {
          return pieceLoc.get(i).equals("bKnight");
        } else if (pieceLoc.get(location).startsWith("b")) {
          return pieceLoc.get(i).equals("wKnight");
        }
      }
    }
    for (int i : knightLoc2) {
      boolean inLine = Math.abs((location / 8) - (i / 8)) == 2;
      boolean inBoundaries = 0 <= i && i < 64;
      if (inBoundaries && inLine) {
        if (!pieceLoc.containsKey(i)) continue;
        if (pieceLoc.get(location).startsWith("w")) {
          return pieceLoc.get(i).equals("bKnight");
        } else if (pieceLoc.get(location).startsWith("b")) {
          return pieceLoc.get(i).equals("wKnight");
        }
      }
    }
    return false;
  }

  private static boolean pawn() {
    int[] blackPawnLoc = {location - 9, location - 7};
    int[] whitePawnLoc = {location + 9, location + 7};
    if (pieceLoc.get(location).startsWith("w")) {
      for (int i : blackPawnLoc) {
        if (!pieceLoc.containsKey(i)) continue;
        if (0 <= i && i < 64 && Math.abs((location / 8) - (i / 8)) == 1) {
          return pieceLoc.get(i).equals("bPawn");
        }
      }
    } else if (pieceLoc.get(location).startsWith("b")) {
      for (int i : whitePawnLoc) {
        if (!pieceLoc.containsKey(i)) continue;
        if (i < 64 && Math.abs((location / 8) - (i / 8)) == 1) {
          return pieceLoc.get(i).equals("wPawn");
        }
      }
    }
    return false;
  }
}