package chess;

public abstract class RBQMoves extends PieceUtils {
  // rook moves
  public static void move1() {
    for (int i = location + 1; i < 64; i++) {
      if (i / 8 != location / 8)
        break;
      boolean notEmpty = pieceLoc.containsKey(i);
      displayMoves(i, buttons);
      if (notEmpty)
        break;
    }
    for (int i = location - 1; i >= 0; i--) {
      if (i / 8 != location / 8)
        break;
      boolean notEmpty = pieceLoc.containsKey(i);
      displayMoves(i, buttons);
      if (notEmpty)
        break;
    }
    for (int i = location + 8; i < 64; i += 8) {
      boolean notEmpty = pieceLoc.containsKey(i);
      displayMoves(i, buttons);
      if (notEmpty)
        break;
    }
    for (int i = location - 8; i >= 0; i -= 8) {
      boolean notEmpty = pieceLoc.containsKey(i);
      displayMoves(i, buttons);
      if (notEmpty)
        break;
    }
  }

  // bishop moves
  public static void move2() {
    for (int i = location + 9; i < 64; i += 9) {
      if (i % 8 == 0)
        break;
      boolean notEmpty = pieceLoc.containsKey(i);
      displayMoves(i, buttons);
      if (notEmpty)
        break;
    }
    for (int i = location + 7; i < 64; i += 7) {
      if (i % 8 == 7)
        break;
      boolean notEmpty = pieceLoc.containsKey(i);
      displayMoves(i, buttons);
      if (notEmpty)
        break;
    }
    for (int i = location - 9; i >= 0; i -= 9) {
      if (i % 8 == 7)
        break;
      boolean notEmpty = pieceLoc.containsKey(i);
      displayMoves(i, buttons);
      if (notEmpty)
        break;
    }
    for (int i = location - 7; i >= 0; i -= 7) {
      if (i % 8 == 0)
        break;
      boolean notEmpty = pieceLoc.containsKey(i);
      displayMoves(i, buttons);
      if (notEmpty)
        break;
    }
  }
}
