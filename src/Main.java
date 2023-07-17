
public class Main implements setupVars{
    public static void main(String[] args) {
        BuildChess pieces = new BuildChess();
        Draw board = new Draw();
        board.draw();
        pieces.placePieces();
    }
}