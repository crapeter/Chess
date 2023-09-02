package chess;

import java.awt.*;

class Check extends PieceUtils implements SetupVars {
    int kingLoc;

    public Check(int loc) {
        this.kingLoc = loc;
    }
    public void checkLoc() {
        if (up(kingLoc) && down(kingLoc) && left(kingLoc) && right(kingLoc)
                && upRight(kingLoc) && upLeft(kingLoc) && downRight(kingLoc) && downLeft(kingLoc)
                && checkKnight(kingLoc) && checkPawn(kingLoc)) {
            displayMoves(kingLoc, buttons);
        } else if (!pieceLoc.containsKey(kingLoc)) {
            buttons[kingLoc].setBackground(Color.red.darker());
        }
    }
    public boolean up(int loc){
        for (int i = loc; i >= 0; i -= 8) {
            if (i == loc) continue;
            if (!checking(i, "Rook")) {
                return false;
            } else if (pieceLoc.containsKey(i)) break;
        }
        return true;
    }
    public boolean down(int loc){
        for (int i = loc; i < 64; i += 8) {
            if (i == loc) continue;
            if (!checking(i, "Rook")) {
                return false;
            } else if (pieceLoc.containsKey(i)) break;
        }
        return true;
    }
    public boolean left(int loc){
        for (int i = loc; i >= 0; i--) {
            if (i == loc) continue;
            if (i / 8 != loc / 8) break;
            if (!checking(i, "Rook")) {
                return false;
            } else if (pieceLoc.containsKey(i)) break;
        }
        return true;
    }
    public boolean right(int loc){
        for (int i = loc; i < 64; i++) {
            if (i == loc) continue;
            if (i / 8 != loc / 8) break;
            if (!checking(i, "Rook")) {
                return false;
            } else if (pieceLoc.containsKey(i)) break;
        }
        return true;
    }
    public boolean upRight(int loc){
        for (int i = loc; i >= 0; i -= 7) {
            if (i == loc && i % 8 != 0) continue;
            if (i % 8 == 0) break;
            if (!checking(i, "Bishop")) {
                return false;
            } else if (pieceLoc.containsKey(i)) break;
        }
        return true;
    }
    public boolean upLeft(int loc){
        for (int i = loc; i >= 0; i -= 9) {
            if (i == loc && i % 8 != 7) continue;
            if (i % 8 == 7) break;
            System.out.println(checking(i, "Bishop"));
            if (!checking(i, "Bishop")) {
                return false;
            } else if (pieceLoc.containsKey(i)) break;
        }
        return true;
    }
    public boolean downRight(int loc){
        for (int i = loc; i < 64; i += 9) {
            if (i == loc && i % 8 != 0) continue;
            if (i % 8 == 0) break;
            if (!checking(i, "Bishop")) {
                return false;
            } else if (pieceLoc.containsKey(i)) break;
        }
        return true;
    }
    public boolean downLeft(int loc){
        for (int i = loc; i < 64; i += 7) {
            if (i == loc && i % 8 != 7) continue;
            if (i % 8 == 7) break;
            if (!checking(i, "Bishop")) {
                return false;
            } else if (pieceLoc.containsKey(i)) break;
        }
        return true;
    }
    public boolean checkKnight(int loc){
        int[] knightLoc1 = { loc - 10, loc - 6, loc + 6, loc + 10 };
        int[] knightLoc2 = { loc - 17, loc - 15, loc + 15, loc + 17 };
        for (int i : knightLoc1) {
            boolean inLine = Math.abs((loc / 8) - (i / 8)) == 1;
            boolean inBoundaries = 0 <= i && i < 64;
            if (inBoundaries && inLine && pieceHeld.equals("wKing") && pieceLoc.containsKey(i)) {
                if (pieceLoc.get(i).equals("bKnight")) {
                    return false;
                }
            } else if (inBoundaries && inLine && pieceHeld.equals("bKing") && pieceLoc.containsKey(i)) {
                if (pieceLoc.get(i).equals("wKnight")) {
                    return false;
                }
            }
        }
        for (int i : knightLoc2) {
            boolean inLine = Math.abs((loc / 8) - (i / 8)) == 2;
            boolean inBoundaries = 0 <= i && i < 64;
            if (inBoundaries && inLine && pieceHeld.equals("wKing") && pieceLoc.containsKey(i)) {
                if (pieceLoc.get(i).equals("bKnight")) {
                    return false;
                }
            } else if (inBoundaries && inLine && pieceHeld.equals("bKing") && pieceLoc.containsKey(i)) {
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
                if (0 <= i && pieceLoc.containsKey(i) && pieceLoc.get(i).equals("bPawn")
                        && Math.abs((loc / 8) - (i / 8)) == 1) {
                    return false;
                }
            }
        } else if (pieceHeld.equals("bKing")) {
            for (int i : whitePawnLoc) {
                if (i < 64 && pieceLoc.containsKey(i) && pieceLoc.get(i).equals("wPawn")
                        && Math.abs((loc / 8) - (i / 8)) == 1) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean checking(int loc, String secondary) {
        String piece1 = secondary.equals("Rook") ? "bRook" : "bBishop";
        String piece2 = secondary.equals("Rook") ? "wRook" : "wBishop";
        if (pieceHeld.equals("wKing") && pieceLoc.containsKey(loc)) {
            return !pieceLoc.get(loc).equals("bQueen") && !pieceLoc.get(loc).equals(piece1);
        } else if (pieceHeld.equals("bKing") && pieceLoc.containsKey(loc)) {
            return !pieceLoc.get(loc).equals("wQueen") && !pieceLoc.get(loc).equals(piece2);
        }
        return true;
    }
}
