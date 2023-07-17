
public class Main implements SetupVars{
    public static void main(String[] args) {
        BuildChess pieces = new BuildChess();
        Draw board = new Draw();
        board.draw();
        pieces.placePieces();
    }
}