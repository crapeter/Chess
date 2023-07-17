import javax.swing.ImageIcon;

public class Pawn extends BuildChess implements Images {
    public static void change(boolean isWhite, String pieceName) {
        if (isWhite) {
            switch (pieceName) {
                case "Queen" -> display("wQueen", wQueen);
                case "Knight" -> display("wKnight", wKnight);
                case "Bishop" -> display("wBishop", wBishop);
                case "Rook" -> display("wRook", wRook);
            }
        }
        else {
            switch (pieceName) {
                case "Queen" -> display("bQueen", bQueen);
                case "Knight" -> display("bKnight", bKnight);
                case "Bishop" -> display("bBishop", bBishop);
                case "Rook" -> display("bRook", bRook);
            }
        }
    }
    public static void display(String name, ImageIcon newIcon) { placePiece(location, newIcon, name); }
}