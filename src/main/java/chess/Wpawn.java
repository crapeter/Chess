package chess;

public class Wpawn extends PieceFunctionality {
  public static String[] blackPieces = { "bBishop", "bKing", "bKnight", "bPawn", "bQueen", "bRook" };

  public static void display() {
    if (location / 8 == 6) {
      forDisplay(location, true);
      diagDisplay(location);
    } else if (location / 8 != 0) {
      forDisplay(location, false);
      diagDisplay(location);
      if (location / 8 == 3) {
        enPassant(location);
      }
    }
  }

  public static void forDisplay(int loc, boolean firstMove) {
    if (firstMove) {
      boolean singleMove = pieceLoc.containsKey(loc - 8);
      boolean doubleMove = pieceLoc.containsKey(loc - 16);
      if (!singleMove)
        displayMoves(loc - 8, buttons);
      if (!doubleMove && !singleMove)
        displayMoves(loc - 16, buttons);
    } else {
      if (!pieceLoc.containsKey(loc - 8))
        displayMoves(loc - 8, buttons);
    }
  }

  public static void diagDisplay(int loc) {
    for (String piece : blackPieces) {
      if (pieceLoc.containsKey(loc - 7) && pieceLoc.get(loc - 7).equals(piece) && loc % 8 != 7)
        displayMoves(loc - 7, buttons);
      if (pieceLoc.containsKey(loc - 9) && pieceLoc.get(loc - 9).equals(piece) && loc % 8 != 0)
        displayMoves(loc - 9, buttons);
    }
  }

  public static void enPassant(int loc) {
    if (pieceLoc.containsKey(loc - 1) && pieceLoc.get(loc - 1).equals("bPawn") && loc % 8 != 0 && loc / 8 == 3) {
      if (blackEnPassant[(location - 1) % 8])
        displayMoves(loc - 9, buttons);
    }
    if (pieceLoc.containsKey(loc + 1) && pieceLoc.get(loc + 1).equals("bPawn") && loc % 8 != 7 && loc / 8 == 3) {
      if (blackEnPassant[(location + 1) % 8])
        displayMoves(loc - 7, buttons);
    }
  }
}
