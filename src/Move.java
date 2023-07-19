public class Move extends BuildChess{
    public static void grab(int loc) {
        boolean canWhiteCastle = (canWhiteCastle1 || canWhiteCastle2);
        boolean canBlackCastle = (canBlackCastle1 || canBlackCastle2);
        switch (pieceLoc.get(loc)) {
            case "wPawn" -> {
                grabPiece(loc, wPawn);
                Wpawn.move(location, buttons, pieceLoc);
                pickUpPiece(loc, pieceHeld);
            }
            case "bPawn" -> {
                grabPiece(loc, bPawn);
                Bpawn.move(location, buttons, pieceLoc);
                pickUpPiece(loc, pieceHeld);
            }
            case "wKing" -> {
                grabPiece(loc, wKing);
                King.move(location, buttons, pieceLoc);
                Castle.canCastle(location, buttons, pieceLoc, canWhiteCastle, canBlackCastle);
                pickUpPiece(loc, pieceHeld);
            }
            case "bKing" -> {
                grabPiece(loc, bKing);
                King.move(location, buttons, pieceLoc);
                Castle.canCastle(location, buttons, pieceLoc, canWhiteCastle, canBlackCastle);
                pickUpPiece(loc, pieceHeld);
            }
            case "bQueen" -> {
                grabPiece(loc, bQueen);
                Queen.move(location, buttons, pieceLoc);
                pickUpPiece(loc, pieceHeld);
            }
            case "wQueen" -> {
                grabPiece(loc, wQueen);
                Queen.move(location, buttons, pieceLoc);
                pickUpPiece(loc, pieceHeld);
            }
            case "wBishop" -> {
                grabPiece(loc, wBishop);
                Bishop.move(location, buttons, pieceLoc);
                pickUpPiece(loc, pieceHeld);
            }
            case "bBishop" -> {
                grabPiece(loc, bBishop);
                Bishop.move(location, buttons, pieceLoc);
                pickUpPiece(loc, pieceHeld);
            }
            case "wKnight" -> {
                grabPiece(loc, wKnight);
                Knight.move(location, buttons, pieceLoc);
                pickUpPiece(loc, pieceHeld);
            }
            case "bKnight" -> {
                grabPiece(loc, bKnight);
                Knight.move(location, buttons, pieceLoc);
                pickUpPiece(loc, pieceHeld);
            }
            case "wRook" -> {
                grabPiece(loc, wRook);
                Rook.move(location, buttons, pieceLoc);
                pickUpPiece(loc, pieceHeld);
            }
            case "bRook" -> {
                grabPiece(loc, bRook);
                Rook.move(location, buttons, pieceLoc);
                pickUpPiece(loc, pieceHeld);
            }
        }
    }
    public static void empty(int loc) {
        if (!buttons[loc].getText().equals("a")) {
            swapPiece(location, location, icon, pieceHeld);
        }
        else {
            if (!pieceHeld.equals("bPawn") && !pieceHeld.equals("wPawn")) {
                if (canWhiteCastle1 && pieceHeld.equals("wKing")) {
                    if (loc == 57)
                        swapPiece(56, 58, wRook, "wRook");
                    canWhiteCastle1 = false;
                    canWhiteCastle2 = false;
                }
                if (canWhiteCastle2 && pieceHeld.equals("wKing")) {
                    if (loc == 62)
                        swapPiece(63, 61, wRook, "wRook");
                    canWhiteCastle1 = false;
                    canWhiteCastle2 = false;
                }
                if (canBlackCastle1 && pieceHeld.equals("bKing")) {
                    if (loc == 1)
                        swapPiece(0, 2, bRook, "bRook");
                    canBlackCastle1 = false;
                    canBlackCastle2 = false;
                }
                if (canBlackCastle2 && pieceHeld.equals("bKing")) {
                    if (loc == 6)
                        swapPiece(0, 2, bRook, "bRook");
                    canBlackCastle1 = false;
                    canBlackCastle2 = false;
                }
                checkCastle();
                swapPiece(location, loc, icon, pieceHeld);
            }
            else if (pieceHeld.equals("bPawn")) {
                if (loc / 8 == 7) {
                    Bpawn bPawn = new Bpawn();
                    removePiece(location, "bPawn");
                    location = loc;
                    bPawn.displayChange();
                }
                if (loc == location + 7) {
                    swapPiece(location, loc, bPawn, "bPawn");
                    swapPiece(location, loc, bPawn, "bPawn");
                    removePiece(location + 1, "wPawn");
                }
                else if (loc == location + 9) {
                    swapPiece(location, loc, wPawn, "wPawn");
                    removePiece(location - 1, "bPawn");
                }
                else {
                    swapPiece(location, loc, icon, pieceHeld);

                    if (blackPawnMove[1][location % 8] == 0)
                        blackPawnMove[1][location % 8] = loc;
                }
            }
            else {
                if (location / 8 == 0) {
                    Wpawn wPawn = new Wpawn();
                    removePiece(location, "wPawn");
                    location = loc;
                    wPawn.displayChange();
                }
                if (loc == location - 7) {
                    swapPiece(location, loc, wPawn, "wPawn");
                    removePiece(location + 1, "bPawn");
                }
                else if (loc == location - 9) {
                    swapPiece(location, loc, wPawn, "wPawn");
                    removePiece(location - 1, "bPawn");
                }
                else {
                    swapPiece(location, loc, icon, pieceHeld);

                    if (whitePawnMove[1][location % 8] == 0)
                        whitePawnMove[1][location % 8] = loc;
                }
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
            if (pieceHeld.equals("bPawn") && loc / 8 == 7 && !gameOver) {
                Bpawn bPawn = new Bpawn();
                removePiece(loc, pieceLoc.get(loc));
                removePiece(location, "bPawn");
                location = loc;
                bPawn.displayChange();
            }
            else if (pieceHeld.equals("wPawn") && loc / 8 == 0 && !gameOver) {
                Wpawn wPawn = new Wpawn();
                removePiece(loc, pieceLoc.get(loc));
                removePiece(location, "wPawn");
                location = loc;
                wPawn.displayChange();
            }
            swapPiece(location, loc, icon, pieceHeld);
        }
        else
            swapPiece(location, location, icon, pieceHeld);
    }
    private static void checkCastle() {
        if (pieceHeld.equals("wRook") && location == 56) {
            canWhiteCastle1 = false;
        }
        else if (pieceHeld.equals("wRook") && location == 63) {
            canWhiteCastle2 = false;
        }
        else if (pieceHeld.equals("wKing") && location == 60){
            canWhiteCastle1 = false;
            canWhiteCastle2 = false;
        }
        else if (pieceHeld.equals("bRook") && location == 0) {
            canBlackCastle1 = false;
        }
        else if (pieceHeld.equals("bRook") && location == 7) {
            canBlackCastle2 = false;
        }
        else if (pieceHeld.equals("bKing") && location == 60){
            canBlackCastle1 = false;
            canBlackCastle2 = false;
        }
    }
}