
public class Main {
    public static void main(String[] args) {
        BuildChess pieces = new BuildChess();
        Draw board = new Draw();
        board.draw();
        pieces.placePieces();
    }
}
