import java.awt.*;

public class King extends HelperFunctions{
    public static void move() {
        int[] kingLocs = {location - 9, location - 8, location - 7, location - 1,
                          location + 1, location + 7, location + 8, location + 9};
        for (int i = 0; i < 8; i++) {
            if (0 <= kingLocs[i] && kingLocs[i] < 64) {
                Check check = new Check(kingLocs[i]);
                check.checkLoc();
            }
        }
    }
    public static void castle() {
        int count1 = 0;
        int count2 = 0;
        if (canBlackCastle1) {
            for (int i = location - 1; i > 0; i--) {
                if (pieceLoc.containsKey(i))
                    break;
                else
                    count1++;
            }
            if (count1 == 3 && pieceLoc.containsKey(0) && pieceLoc.get(0).equals("bRook") && pieceHeld.equals("bKing"))
                displayMoves(1, buttons, Color.yellow);
        }
        if (canBlackCastle2) {
            for (int i = location + 1; i < 7; i++) {
                if (pieceLoc.containsKey(i))
                    break;
                else
                    count2++;
            }
            if (count2 == 2 && pieceLoc.containsKey(7) && pieceLoc.get(7).equals("bRook") && pieceHeld.equals("bKing"))
                displayMoves(6, buttons, Color.yellow);
        }
        count1 = 0;
        count2 = 0;
        if (canWhiteCastle1) {
            for (int i = location - 1; i > 56; i--) {
                if (pieceLoc.containsKey(i))
                    break;
                else
                    count1++;
            }
            if (count1 == 3 && pieceLoc.containsKey(56) && pieceLoc.get(56).equals("wRook") && pieceHeld.equals("wKing"))
                displayMoves(57, buttons, Color.yellow);
        }
        if (canWhiteCastle2) {
            for (int i = location + 1; i < 63; i++) {
                if (pieceLoc.containsKey(i))
                    break;
                else
                    count2++;
            }
            if (count2 == 2 && pieceLoc.containsKey(63) && pieceLoc.get(63).equals("wRook") && pieceHeld.equals("wKing"))
                displayMoves(62, buttons, Color.yellow);
        }
    }
}