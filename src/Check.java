import java.awt.*;

class Check extends HelperFunctions implements SetupVars{
    int kingLoc;

    public Check(int loc) {
        this.kingLoc = loc;
    }
    public void checkLoc() {
        if (up(kingLoc) && down(kingLoc) && left(kingLoc) && right(kingLoc)
                && upRight(kingLoc) && upLeft(kingLoc) && downRight(kingLoc) && downLeft(kingLoc)
                && checkKnight(kingLoc) && checkPawn(kingLoc)) {
            displayMoves(kingLoc, buttons, Color.yellow);
        }
    }
    public boolean up(int loc){
        for (int i = loc; i >= 0; i -= 8) {
            if (pieceHeld.equals("wKing") && pieceLoc.containsKey(i) && i != loc) {
                if (pieceLoc.get(i).equals("bQueen") || pieceLoc.get(i).equals("bRook")) {
                    return false;
                } else {
                    break;
                }
            } else if (pieceHeld.equals("bKing") && pieceLoc.containsKey(i) && i != loc) {
                if (pieceLoc.get(i).equals("wQueen") || pieceLoc.get(i).equals("wRook")) {
                    return false;
                } else {
                    break;
                }
            }
        }
        return true;
    }
    public boolean down(int loc){
        for (int i = loc; i < 64; i += 8) {
            if (pieceHeld.equals("wKing") && pieceLoc.containsKey(i) && i != loc) {
                if (pieceLoc.get(i).equals("bQueen") || pieceLoc.get(i).equals("bRook")) {
                    return false;
                } else {
                    break;
                }
            } else if (pieceHeld.equals("bKing") && pieceLoc.containsKey(i) && i != loc) {
                if (pieceLoc.get(i).equals("wQueen") || pieceLoc.get(i).equals("wRook")) {
                    return false;
                } else {
                    break;
                }
            }
        }
        return true;
    }
    public boolean left(int loc){
        for (int i = loc; i >= 0; i--) {
            if (pieceHeld.equals("wKing") && pieceLoc.containsKey(i) && i / 8 == loc / 8 && i != loc) {
                if (pieceLoc.get(i).equals("bQueen") || pieceLoc.get(i).equals("bRook")) {
                    return false;
                } else {
                    break;
                }
            } else if (pieceHeld.equals("bKing") && pieceLoc.containsKey(i) && i / 8 == loc / 8 && i != loc) {
                if (pieceLoc.get(i).equals("wQueen") || pieceLoc.get(i).equals("wRook")) {
                    return false;
                } else {
                    break;
                }
            }
        }
        return true;
    }
    public boolean right(int loc){
        for (int i = loc; i < 64; i++) {
            if (pieceHeld.equals("wKing") && pieceLoc.containsKey(i) && i / 8 == loc / 8 && i != loc) {
                if (pieceLoc.get(i).equals("bQueen") || pieceLoc.get(i).equals("bRook")) {
                    return false;
                } else {
                    break;
                }
            } else if (pieceHeld.equals("bKing") && pieceLoc.containsKey(i) && i / 8 == loc / 8 && i != loc) {
                if (pieceLoc.get(i).equals("wQueen") || pieceLoc.get(i).equals("wRook")) {
                    return false;
                } else {
                    break;
                }
            }
        }
        return true;
    }
    public boolean upRight(int loc){
        for (int i = loc; i >= 0; i -= 7) {
            if (i % 8 == 0) break;
            if (pieceHeld.equals("wKing") && pieceLoc.containsKey(i) && i != loc) {
                if (pieceLoc.get(i).equals("bQueen") || pieceLoc.get(i).equals("bBishop")) {
                    return false;
                } else {
                    break;
                }
            } else if (pieceHeld.equals("bKing") && pieceLoc.containsKey(i) && i != loc) {
                if (pieceLoc.get(i).equals("wQueen") || pieceLoc.get(i).equals("wBishop")) {
                    return false;
                } else {
                    break;
                }
            }
        }
        return true;
    }
    public boolean upLeft(int loc){
        for (int i = loc; i >= 0; i -= 9) {
            if (i % 8 == 7) break;
            if (pieceHeld.equals("wKing") && pieceLoc.containsKey(i) && i != loc) {
                if (pieceLoc.get(i).equals("bQueen") || pieceLoc.get(i).equals("bBishop")) {
                    return false;
                } else {
                    break;
                }
            } else if (pieceHeld.equals("bKing") && pieceLoc.containsKey(i) && i != loc) {
                if (pieceLoc.get(i).equals("wQueen") || pieceLoc.get(i).equals("wBishop")) {
                    return false;
                } else {
                    break;
                }
            }
        }
        return true;
    }
    public boolean downRight(int loc){
        for (int i = loc; i < 64; i += 9) {
            if (i % 8 == 0) break;
            if (pieceHeld.equals("wKing") && pieceLoc.containsKey(i) && i != loc) {
                if (pieceLoc.get(i).equals("bQueen") || pieceLoc.get(i).equals("bBishop")) {
                    return false;
                } else {
                    break;
                }
            } else if (pieceHeld.equals("bKing") && pieceLoc.containsKey(i) && i != loc) {
                if (pieceLoc.get(i).equals("wQueen") || pieceLoc.get(i).equals("wBishop")) {
                    return false;
                } else {
                    break;
                }
            }
        }
        return true;
    }
    public boolean downLeft(int loc){
        for (int i = loc; i < 64; i += 9) {
             if (i % 8 == 7) break;
            if (pieceHeld.equals("wKing") && pieceLoc.containsKey(i) && i != loc) {
                if (pieceLoc.get(i).equals("bQueen") || pieceLoc.get(i).equals("bBishop")) {
                    return false;
                } else {
                    break;
                }
            } else if (pieceHeld.equals("bKing") && pieceLoc.containsKey(i) && i != loc) {
                if (pieceLoc.get(i).equals("wQueen") || pieceLoc.get(i).equals("wBishop")) {
                    return false;
                } else {
                    break;
                }
            }
        }
        return true;
    }
    public boolean checkKnight(int loc){
        int[] knightLoc = {loc - 17, loc - 15, loc - 10, loc - 6,
                           loc + 6, loc + 10, loc + 15, loc + 17};
        for (int i : knightLoc) {
            if (0 <= i && i < 64 && pieceHeld.equals("wKing") && pieceLoc.containsKey(i)) {
                if (pieceLoc.get(i).equals("bKnight")) {
                    return false;
                }
            } else if (0 <= i && i < 64 && pieceHeld.equals("bKing") && pieceLoc.containsKey(i)) {
                if (pieceLoc.get(i).equals("wKnight")) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean checkPawn(int loc){
        int[] blackPawnLoc = {loc - 9, loc - 7};
        int[] whitePawnLoc = {loc + 9, loc + 7};
        if (pieceHeld.equals("wKing")) {
            for (int i : blackPawnLoc) {
                if (0 <= i && pieceLoc.containsKey(i) && pieceLoc.get(i).equals("bPawn")) {
                    return false;
                }
            }
        } else if (pieceHeld.equals("bKing")) {
            for (int i : whitePawnLoc) {
                if (i < 64 && pieceLoc.containsKey(i) && pieceLoc.get(i).equals("wPawn")) {
                    return false;
                }
            }
        }
        return true;
    }
}