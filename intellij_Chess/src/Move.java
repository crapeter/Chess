public class Move extends BuildChess{
    public static void grab(int loc) {
        switch (pieceLoc.get(loc)) {
            case "wPawn" -> {
                grabPiece(loc, wPawn);
                Wpawn.move();
                pickUpPiece(loc, pieceHeld);
            }
            case "bPawn" -> {
                grabPiece(loc, bPawn);
                Bpawn.move();
                pickUpPiece(loc, pieceHeld);
            }
            case "wKing" -> {
                grabPiece(loc, wKing);
                King.move();
                King.castle();
                pickUpPiece(loc, pieceHeld);
            }
            case "bKing" -> {
                grabPiece(loc, bKing);
                King.move();
                King.castle();
                pickUpPiece(loc, pieceHeld);
            }
            case "bQueen" -> {
                grabPiece(loc, bQueen);
                Queen.move();
                pickUpPiece(loc, pieceHeld);
            }
            case "wQueen" -> {
                grabPiece(loc, wQueen);
                Queen.move();
                pickUpPiece(loc, pieceHeld);
            }
            case "wBishop" -> {
                grabPiece(loc, wBishop);
                Bishop.move();
                pickUpPiece(loc, pieceHeld);
            }
            case "bBishop" -> {
                grabPiece(loc, bBishop);
                Bishop.move();
                pickUpPiece(loc, pieceHeld);
            }
            case "wKnight" -> {
                grabPiece(loc, wKnight);
                Knight.move();
                pickUpPiece(loc, pieceHeld);
            }
            case "bKnight" -> {
                grabPiece(loc, bKnight);
                Knight.move();
                pickUpPiece(loc, pieceHeld);
            }
            case "wRook" -> {
                grabPiece(loc, wRook);
                Rook.move();
                pickUpPiece(loc, pieceHeld);
            }
            case "bRook" -> {
                grabPiece(loc, bRook);
                Rook.move();
                pickUpPiece(loc, pieceHeld);
            }
        }
    }
    public static void empty(int loc) {
        Pawn pawn = new Pawn();
        if (!buttons[loc].getText().equals("a"))
            swapPiece(location, location, icon, pieceHeld);
        else { //move is available
            checkCastle(); //updates the castle booleans
            switch (pieceHeld) {
                case "wPawn" -> {
                    if (location / 8 == 0) {
                        removePiece(location, "wPawn");
                        location = loc;
                        pawn.change("White");
                    } else if (loc == location - 7 || loc == location - 9) {
                        boolean upLeft = loc == location - 9; //location of opposing pawn
                        removePiece(upLeft ? location - 1 : location + 1, "bPawn");
                    }
                    if (whitePawnMove[1][location % 8] == 0)
                        whitePawnMove[1][location % 8] = loc;
                }
                case "bPawn" -> {
                    if (loc / 8 == 7) {
                        removePiece(location, "bPawn");
                        location = loc;
                        pawn.change("Black");
                    } else if (loc == location + 7 || loc == location + 9) {
                        boolean downLeft = loc == location + 7; //location of opposing pawn
                        removePiece(downLeft ? location - 1 : location + 1, "wPawn");
                    }
                    if (blackPawnMove[1][location % 8] == 0)
                        blackPawnMove[1][location % 8] = loc;
                }
                case "wKing" -> {
                    if (loc == 57 || loc == 62)
                        swapPiece(loc == 57 ? 56 : 63, loc == 57 ? 58 : 61, wRook, "wRook");
                    canWhiteCastle1 = false;
                    canWhiteCastle2 = false;
                }
                case "bKing" -> {
                    if (loc == 1 || loc == 6)
                        swapPiece(loc == 1 ? 0 : 7, loc == 1 ? 2 : 5, bRook, "bRook");
                    canBlackCastle1 = false;
                    canBlackCastle2 = false;
                }
            }
            swapPiece(location, loc, icon, pieceHeld);
        }
    }
    public static void contains(int loc) {
        String currentPieceColor = "white";
        String takingPieceColor = "white";

        for (String piece : blackPieces) {
            if (pieceHeld.equals(piece))
                currentPieceColor = "black";
            if (pieceLoc.get(loc).equals(piece))
                takingPieceColor = "black";
        }

        if (!currentPieceColor.equals(takingPieceColor) && buttons[loc].getText().equals("a")) {
            pawnCheck(loc, 7, "bPawn", "wKing", "Black");
            pawnCheck(loc, 0, "wPawn", "bKing", "White");
            swapPiece(location, loc, icon, pieceHeld);
        } else
            swapPiece(location, location, icon, pieceHeld);
    }
    private static void pawnCheck(int loc, int row, String pieceName, String takeName, String color) {
        if (loc / 8 == row && pieceHeld.equals(pieceName) && !pieceLoc.get(loc).equals(takeName)) {
            removePiece(loc, pieceLoc.get(loc));
            removePiece(location, pieceName);
            location = loc;
            Pawn pawn = new Pawn();
            pawn.change(color);
        }
    }
    private static void checkCastle() {
        switch (pieceHeld) {
            case "wRook" -> {
                if (location == 56) canWhiteCastle1 = false;
                if (location == 63) canWhiteCastle2 = false;
            }
            case "wKing" -> {
                if (location == 60) {
                    canWhiteCastle1 = false;
                    canWhiteCastle2 = false;
                }
            }
            case "bRook" -> {
                if (location == 0) canBlackCastle1 = false;
                if (location == 7) canBlackCastle2 = false;
            }
            case "bKing" -> {
                if (location == 4) {
                    canBlackCastle1 = false;
                    canBlackCastle2 = false;
                }
            }
        }
    }
}