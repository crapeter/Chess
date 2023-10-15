package chess;

import java.util.stream.*;

public class Knight extends PieceUtils {
  public static void display() {
    int[] knightLoc = {location - 10, location - 6, location + 6, location + 10,
            location - 17, location - 15, location + 15, location + 17};
    IntStream.range(0, 8).forEach(i -> canPlace(knightLoc[i], location, i < 4 ? 1 : 2));
  }

  public static void canPlace(int loc, int location, int spaceBetween) {
    if (0 <= loc && loc < 64 && Math.abs((location / 8) - (loc / 8)) == spaceBetween) {
      displayMoves(loc, buttons);
    }
  }
}

