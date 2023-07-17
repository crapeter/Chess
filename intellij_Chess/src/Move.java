public class Move extends BuildChess{
    public static void grab(int loc) {
        if (pieceLoc.get(loc).equals("wRook") || pieceLoc.get(loc).equals("bRook")) {
            if (pieceLoc.get(loc).equals("wRook"))
                grabPiece(loc, wRook);
            else
                grabPiece(loc, bRook);
            Rook.move(location, buttons, pieceLoc);
            pickUpPiece(loc, pieceHeld);
        }
        else if (pieceLoc.get(loc).equals("wKnight") || pieceLoc.get(loc).equals("bKnight")) {
            if (pieceLoc.get(loc).equals("wKnight"))
                grabPiece(loc, wKnight);
            else
                grabPiece(loc, bKnight);
            Knight.move(location, buttons, pieceLoc);
            pickUpPiece(loc, pieceHeld);
        }
        else if (pieceLoc.get(loc).equals("wBishop") || pieceLoc.get(loc).equals("bBishop")) {
            if (pieceLoc.get(loc).equals("wBishop"))
                grabPiece(loc, wBishop);
            else
                grabPiece(loc, bBishop);
            Bishop.move(location, buttons, pieceLoc);
            pickUpPiece(loc, pieceHeld);
        }
        else if (pieceLoc.get(loc).equals("wQueen") || pieceLoc.get(loc).equals("bQueen")) {
            if (pieceLoc.get(loc).equals("wQueen"))
                grabPiece(loc, wQueen);
            else
                grabPiece(loc, bQueen);
            Queen.move(location, buttons, pieceLoc);
            pickUpPiece(loc, pieceHeld);
        }
        else if (pieceLoc.get(loc).equals("wKing") || pieceLoc.get(loc).equals("bKing")) {
            if (pieceLoc.get(loc).equals("wKing"))
                grabPiece(loc, wKing);
            else
                grabPiece(loc, bKing);
            King.move(location, buttons, pieceLoc);
            Castle.canCastle(location, buttons, pieceLoc, canWhiteCastle, canBlackCastle);
            pickUpPiece(loc, pieceHeld);
        }
        else if (pieceLoc.get(loc).equals("bPawn")) {
            grabPiece(loc, bPawn);
            Bpawn.move(location, buttons, pieceLoc);
            pickUpPiece(loc, pieceHeld);
        }
        else if (pieceLoc.get(loc).equals("wPawn")) {
            grabPiece(loc, wPawn);
            Wpawn.move(location, buttons, pieceLoc);
            pickUpPiece(loc, pieceHeld);
        }
    }
    public static void free(int loc) {
        if (buttons[loc].getText().equals("a")) {
            if (pieceHeld.equals("bPawn")) {
                if (loc / 8 == 7) {
                    Bpawn bPawn = new Bpawn();
                    removePiece(location, "bPawn");
                    location = loc;
                    bPawn.displayChange();
                }
                if (loc == location + 7) {
                    pieceMov(location, loc, bPawn, "bPawn");
                    removePiece(location - 1, "wPawn");
                }
                else if (loc == location + 9) {
                    pieceMov(location, loc, bPawn, "bPawn");
                    removePiece(location + 1, "wPawn");
                }
                else if (loc != location + 9 && loc != location + 7){
                    pieceMov(location, loc, icon, pieceHeld);

                    if (blackPawnMove[1][location % 8] == 0)
                        blackPawnMove[1][location % 8] = loc;
                }
                else
                    pieceMov(location, location, icon, pieceHeld);
            }
            else if (pieceHeld.equals("wPawn")) {
                if (location / 8 == 0) {
                    Wpawn wPawn = new Wpawn();
                    removePiece(location, "wPawn");
                    location = loc;
                    wPawn.displayChange();
                }
                if (loc == location - 7) {
                    pieceMov(location, loc, wPawn, "wPawn");
                    removePiece(location + 1, "bPawn");
                }
                else if (loc == location - 9) {
                    pieceMov(location, loc, wPawn, "wPawn");
                    removePiece(location - 1, "bPawn");
                }
                else if (loc != location - 9 && loc != location - 7){
                    pieceMov(location, loc, icon, pieceHeld);

                    if (whitePawnMove[1][location % 8] == 0)
                        whitePawnMove[1][location % 8] = loc;
                }
                else
                    pieceMov(location, location, icon, pieceHeld);
            }
            else if (pieceHeld.equals("wKing") && (loc == 57 || loc == 62) && location == wKingLocation && canWhiteCastle) {
                if (loc == 57)
                    pieceMov(56, 58, wRook, "wRook");
                else
                    pieceMov(63, 61, wRook, "wRook");

                pieceMov(location, loc, icon, pieceHeld);
                canWhiteCastle = false;
            }
            else if (pieceHeld.equals("bKing") && (loc == 1 || loc == 6) && location == bKingLocation && canBlackCastle) {
                if (loc == 1)
                    pieceMov(0, 2, bRook, "bRook");
                else
                    pieceMov(7, 5, bRook, "bRook");

                pieceMov(location, loc, icon, pieceHeld);
                canBlackCastle = false;
            }
            else
                pieceMov(location, loc, icon, pieceHeld);
        }
        else if (holdingPiece)
            pieceMov(location, location, icon, pieceHeld);
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
            pieceMov(location, loc, icon, pieceHeld);
        }
        else
            pieceMov(location, location, icon, pieceHeld);
    }
}
