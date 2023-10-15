package chess;

public class PieceSetup extends PieceUtils implements SetupVars, Images {
  public void place() {
    for (int i = 0; i < 64; i++) {
      buttons[i].setIcon(null);
      switch (i / 8) {
        case 1 -> placePiece(i, bPawn, "bPawn");
        case 6 -> placePiece(i, wPawn, "wPawn");
      }
      switch (i) {
        case 0, 7 -> placePiece(i, bRook, "bRook");
        case 1, 6 -> placePiece(i, bKnight, "bKnight");
        case 2, 5 -> placePiece(i, bBishop, "bBishop");
        case 3 -> placePiece(i, bQueen, "bQueen");
        case 4 -> placePiece(i, bKing, "bKing");
        case 56, 63 -> placePiece(i, wRook, "wRook");
        case 57, 62 -> placePiece(i, wKnight, "wKnight");
        case 58, 61 -> placePiece(i, wBishop, "wBishop");
        case 59 -> placePiece(i, wQueen, "wQueen");
        case 60 -> placePiece(i, wKing, "wKing");
      }
    }
  }
}