public class Main{
    public static void main(String[] args) {
        PieceFunctionality piece = new PieceFunctionality();
        PieceSetup pieces = new PieceSetup();
        Draw board = new Draw();
        board.draw();
        pieces.place();
        piece.addFunctionality();
    }
}