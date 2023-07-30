import java.awt.*;
import java.util.stream.IntStream;

public class King extends HelperFunctions{
    public static void move() {
        int[] kingLoc1 = {location - 1, location + 1};
        IntStream.range(0, 2)
                .filter(i -> 0 <= kingLoc1[i] && kingLoc1[i] < 64 && inLine(kingLoc1[i], 0))
                .mapToObj(i -> new Check(kingLoc1[i]))
                .forEach(Check::checkLoc);
        int[] kingLoc2 = {location - 9, location - 8, location - 7, location + 7, location + 8, location + 9};
        IntStream.range(0, 6)
                .filter(i -> 0 <= kingLoc2[i] && kingLoc2[i] < 64 && inLine(kingLoc2[i], 1))
                .mapToObj(i -> new Check(kingLoc2[i]))
                .forEach(Check::checkLoc);

    }
    public static void castle() {
        if (canBlackCastle1) {
            int count1 = 0;
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
            int count2 = 0;
            for (int i = location + 1; i < 7; i++) {
                if (pieceLoc.containsKey(i))
                    break;
                else
                    count2++;
            }
            if (count2 == 2 && pieceLoc.containsKey(7) && pieceLoc.get(7).equals("bRook") && pieceHeld.equals("bKing"))
                displayMoves(6, buttons, Color.yellow);
        }
        if (canWhiteCastle1) {
            int count1 = 0;
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
            int count2 = 0;
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
    private static boolean inLine(int locGiven, int distance){
        return Math.abs(location / 8 - locGiven / 8) == distance;
    }
}
