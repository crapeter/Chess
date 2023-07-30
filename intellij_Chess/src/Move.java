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
        if (!buttons[loc].getText().equals("a")) {
            swapPiece(location, location, icon, pieceHeld);
        } else /*move is available*/ {
            checkCastle(); //updates the castle booleans
            switch (pieceHeld) {
                case "wPawn" -> {
                    if (location / 8 == 0) {
                        Pawn pawn = new Pawn();
                        removePiece(location, "wPawn");
                        location = loc;
                        pawn.change("White");
                    } else if (loc == location - 7) {
                        removePiece(location + 1, "bPawn");
                    } else if (loc == location - 9) {
                        removePiece(location - 1, "bPawn");
                    } else if (whitePawnMove[1][location % 8] == 0) {
                        whitePawnMove[1][location % 8] = loc;
                    }
                    swapPiece(location, loc, icon, pieceHeld);
                }
                case "bPawn" -> {
                    if (loc / 8 == 7) {
                        Pawn pawn = new Pawn();
                        removePiece(location, "bPawn");
                        location = loc;
                        pawn.change("Black");
                    } else if (loc == location + 7) {
                        removePiece(location - 1, "wPawn");
                    } else if (loc == location + 9) {
                        removePiece(location + 1, "wPawn");
                    } else if (blackPawnMove[1][location % 8] == 0) {
                        blackPawnMove[1][location % 8] = loc;
                    }
                    swapPiece(location, loc, bPawn, "bPawn");
                }
                case "wKing" -> {
                    if (loc == 57) {
                        swapPiece(56, 58, wRook, "wRook");
                    } else if (loc == 62) {
                        swapPiece(63, 61, wRook, "wRook");
                    }
                    swapPiece(location, loc, icon, pieceHeld);
                    canWhiteCastle1 = false;
                    canWhiteCastle2 = false;
                }
                case "bKing" -> {
                    if (loc == 1) {
                        swapPiece(0, 2, bRook, "bRook");
                    } else if (loc == 6) {
                        swapPiece(7, 5, bRook, "bRook");
                    }
                    swapPiece(location, loc, icon, pieceHeld);
                    canBlackCastle1 = false;
                    canBlackCastle2 = false;
                }
                default -> swapPiece(location, loc, icon, pieceHeld);
            }
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
                if (location == 62) canWhiteCastle2 = false;
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