package chess;

public class App {
  public String getGreeting() {
    return "Welcome to jSwing Chess";
  }

  public static void main(String[] args) {
    System.out.println(new App().getGreeting());
    PieceFunctionality piece = new PieceFunctionality();
    PieceSetup pieces = new PieceSetup();
    Draw board = new Draw();
    board.draw();
    pieces.place();
    piece.addFunctionality();
  }
}
