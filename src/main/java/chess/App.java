package chess;

//testing for github pushing
public class App {
  public static void main(String[] args) {
    PieceFunctionality piece = new PieceFunctionality();
    PieceSetup pieces = new PieceSetup();
    Draw board = new Draw();
    board.draw();
    pieces.place();
    piece.addFunctionality();
  }
}
