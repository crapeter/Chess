package chess;

import java.awt.*;
import java.util.*;

class Check extends PieceUtils {
  private static int kingLoc;
  private static boolean takeable;
  private static final int BOARD_SIZE = 64;
  public static final int ROW_LENGTH = 8;
  private static final ArrayList<Integer> bCheckLoc = new ArrayList<>();
  private static final ArrayList<Integer> wCheckLoc = new ArrayList<>();
  private static final ArrayList<Boolean> kingsMovesProtected = new ArrayList<>();

  public Check(int loc) {
    kingLoc = loc;
  }

  public void checkLoc() {
    if (isKingSafe()) {
      displayMoves(kingLoc, buttons);
    } else if (!pieceLoc.containsKey(kingLoc)) {
      buttons[kingLoc].setBackground(Color.red.darker());
    }
  }

  public static boolean causesCheck() {
    return !isKingSafe();
  }

  public static void checkForCheck(int loc) {
    kingsMovesProtected.clear();
    blackCheckLocations.clear();
    whiteCheckLocations.clear();
    kingLoc = loc;

    int[] kingLocations = getKingLocation();

    ArrayList<Integer> locations = new ArrayList<>();
    for (int location : kingLocations) {
      if (0 <= location && location < BOARD_SIZE) {
        locations.add(location);
      }
    }

    ArrayList<Boolean> isSafe = new ArrayList<>();
    for (int i = 0; i < locations.size(); i++) {
      isSafe.add(true);
    }

    if (!isKingSafe()) {
      bCheckLoc.addAll(blackCheckLocations.values());
      wCheckLoc.addAll(whiteCheckLocations.values());
      int tempLoc = kingLoc;
      for (int i = 0; i < locations.size(); i++) {
        kingLoc = locations.get(i);
        if (currentlyWhite) {
          if (bCheckLoc.contains(locations.get(i))) {
            continue;
          }
        } else {
          if (wCheckLoc.contains(locations.get(i))) {
            continue;
          }
        }
        if (pieceLoc.containsKey(locations.get(i))) continue;
        if (isKingSafe()) break;
        isSafe.set(i, false);
      }

      // checks to see if the attack piece can be taken
      if (currentlyWhite) {
        for (String key : blackCheckLocations.keySet()) {
          int location = blackCheckLocations.get(key);
          Capture capture = new Capture(location);
          takeable = capture.canBeTaken();
        }
      } else {
        for (String key : whiteCheckLocations.keySet()) {
          int location = whiteCheckLocations.get(key);
          Capture capture = new Capture(location);
          takeable = capture.canBeTaken();
        }
      }

      // checks to see if the kings direct moves can be protected
      for (int i = 0; i < isSafe.size(); i++) {
        if (isSafe.get(i)) continue;
        int location = locations.get(i);
        Protect protect = new Protect(location);
        boolean canProtect = protect.canProtect();
        kingsMovesProtected.add(canProtect);
      }

      // check to see if the attacking path can be protected
      if (currentlyWhite) {
        ArrayList<Boolean> canProtect = protectable(bCheckLoc, whiteKingLocation);
        if (!kingsMovesProtected.contains(true) && canProtect.contains(false) && !takeable) {
          CheckMate("White's been checkmated");
          return;
        }
      } else {
        ArrayList<Boolean> canProtect = protectable(wCheckLoc, blackKingLocation);
        if (!kingsMovesProtected.contains(true) && canProtect.contains(false) && !takeable) {
          CheckMate("Black's been checkmated");
          return;
        }
      }

      kingLoc = tempLoc;
      textField.setText(currentlyWhite ? "White's in check" : "Black's in check");
    }
  }

  private static void CheckMate(String message) {
    gameOver = true;
    numberOfMoves = 0;
    Font textFont = textField.getFont();
    Font newTextFont = textFont.deriveFont(75f);
    textField.setFont(newTextFont);
    textField.setText(message);
    Font font = forfeit.getFont();
    Font newFont = font.deriveFont(18f);
    forfeit.setFont(newFont);
    forfeit.setText("Restart");
  }

  private static ArrayList<Boolean> protectable(ArrayList<Integer> checkLocations, int kingLocation) {
    ArrayList<Boolean> canProtect = new ArrayList<>();
    for (int location : checkLocations) {
      ArrayList<Boolean> protectPerTile = new ArrayList<>();
      int dir = getCheckDirection(location);
      if (dir == 0) continue;
      for (int i = kingLocation + dir; 0 <= i && i < 64; i += dir) {
        if ((dir == 9 || dir == -7) && i % 8 == 0) break;
        else if ((dir == -9 || dir == 7) && i % 8 == 7) break;
        else if ((dir == 1 || dir == -1) && i / 8 != whiteKingLocation / 8) break;

        if (i == location) break;

        Protect protect = new Protect(i);
        boolean can_Protect = protect.canProtect();
        protectPerTile.add(can_Protect);
      }
      if (protectPerTile.contains(true)) {
        canProtect.add(true);
      } else {
        canProtect.add(false);
      }
    }
    return canProtect;
  }

  private static int[] getKingLocation() {
    int[] kingLocations;
    if (currentlyWhite) {
      kingLocations = new int[]{whiteKingLocation - 1, whiteKingLocation + 1,
          whiteKingLocation - 9, whiteKingLocation - 8, whiteKingLocation - 7,
          whiteKingLocation + 7, whiteKingLocation + 8, whiteKingLocation + 9};
    } else {
      kingLocations = new int[]{blackKingLocation - 1, blackKingLocation + 1,
          blackKingLocation - 9, blackKingLocation - 8, blackKingLocation - 7,
          blackKingLocation + 7, blackKingLocation + 8, blackKingLocation + 9};
    }
    return kingLocations;
  }

  private static boolean isKingSafe() {
    return vertical() && horizontal()
        && rightDiagonal() && leftDiagonal()
        && checkKnight(kingLoc) && checkPawn(kingLoc);
  }

  private static boolean vertical() {
    return moveDirectionally(-ROW_LENGTH) && moveDirectionally(ROW_LENGTH);
  }

  private static boolean horizontal() {
    return moveHorizontally(-1) && moveHorizontally(1);
  }

  private static boolean rightDiagonal() {
    return moveDiagonally(-ROW_LENGTH + 1) && moveDiagonally(ROW_LENGTH + 1);
  }

  private static boolean leftDiagonal() {
    return moveDiagonally(-ROW_LENGTH - 1) && moveDiagonally(ROW_LENGTH - 1);
  }

  private static boolean checkKnight(int loc) {
    int[] knightLoc1 = {loc - 10, loc - 6, loc + 6, loc + 10};
    int[] knightLoc2 = {loc - 17, loc - 15, loc + 15, loc + 17};
    for (int i : knightLoc1) {
      // checks the locations of possible knights that are within 1 row of the new
      // location
      boolean inLine = Math.abs((loc / 8) - (i / 8)) == 1;
      boolean inBoundaries = 0 <= i && i < 64;
      if (inBoundaries && inLine && pieceHeld.equals("wKing") && pieceLoc.containsKey(i)) {
        if (pieceLoc.get(i).equals("bKnight")) {
          blackCheckLocations.put("bKnight", i);
          return false;
        }
      } else if (inBoundaries && inLine && pieceHeld.equals("bKing") && pieceLoc.containsKey(i)) {
        if (pieceLoc.get(i).equals("wKnight")) {
          whiteCheckLocations.put("wKnight", i);
          return false;
        }
      }
    }
    for (int i : knightLoc2) {
      boolean inLine = Math.abs((loc / 8) - (i / 8)) == 2;
      boolean inBoundaries = 0 <= i && i < 64;
      // checks the locations of possible knights that are within 2 row of the new
      // location
      if (inBoundaries && inLine && currentlyWhite && pieceLoc.containsKey(i)) {
        if (pieceLoc.get(i).equals("bKnight")) {
          blackCheckLocations.put("bKnight", i);
          return false;
        }
      } else if (inBoundaries && inLine && !currentlyWhite && pieceLoc.containsKey(i)) {
        if (pieceLoc.get(i).equals("wKnight")) {
          whiteCheckLocations.put("wKnight", i);
          return false;
        }
      }
    }
    return true;
  }

  private static boolean checkPawn(int loc) {
    int[] blackPawnLoc = {loc - 9, loc - 7};
    int[] whitePawnLoc = {loc + 9, loc + 7};
    if (currentlyWhite) {
      // checks both possible locations that a pawn could be if the new location
      for (int i : blackPawnLoc) {
        if (0 <= i && pieceLoc.containsKey(i) && pieceLoc.get(i).equals("bPawn")
            && Math.abs((loc / 8) - (i / 8)) == 1) {
          blackCheckLocations.put("bPawn", i);
          return false;
        }
      }
    } else {
      // checks both possible locations that a pawn could be if the new location
      for (int i : whitePawnLoc) {
        if (i < 64 && pieceLoc.containsKey(i) && pieceLoc.get(i).equals("wPawn")
            && Math.abs((loc / 8) - (i / 8)) == 1) {
          whiteCheckLocations.put("wPawn", i);
          return false;
        }
      }
    }
    return true;
  }

  private static boolean moveDirectionally(int increment) {
    for (int i = kingLoc + increment; i >= 0 && i < BOARD_SIZE; i += increment) {
      // return false if the piece is a rook because the king isn't safe
      if (checking(i, "Rook")) {
        if (currentlyWhite)
          blackCheckLocations.put("Bishop", i);
        else
          whiteCheckLocations.put("Bishop", i);
        return false;
      } else if (pieceLoc.containsKey(i) && !pieceLoc.get(i).equals("wKing") && !pieceLoc.get(i).equals("bKing")) {
        return true;
      }
    }
    return true;
  }

  private static boolean moveHorizontally(int increment) {
    for (int i = kingLoc + increment; i >= 0 && i < BOARD_SIZE
        && i / ROW_LENGTH == kingLoc / ROW_LENGTH; i += increment) {
      // return false if the piece is a rook because the king isn't safe
      if (checking(i, "Rook")) {
        if (currentlyWhite)
          blackCheckLocations.put("Bishop", i);
        else
          whiteCheckLocations.put("Bishop", i);
        return false;
      } else if (pieceLoc.containsKey(i) && !pieceLoc.get(i).equals("wKing") && !pieceLoc.get(i).equals("bKing")) {
        return true;
      }
    }
    return true;
  }

  private static boolean moveDiagonally(int increment) {
    for (int i = kingLoc + increment; i >= 0 && i < BOARD_SIZE; i += increment) {
      // checks if the bottom right or top right diags go out of bounds
      // otherwise checks if the bottom left or top left diags go out of bounds
      if ((increment == 9 || increment == -7) && i % ROW_LENGTH == 0) {
        break;
      } else if ((increment == -9 || increment == 7) && i % ROW_LENGTH == 7) {
        break;
      }
      // return false if the piece is a bishop because the king isn't safe
      if (checking(i, "Bishop")) {
        if (currentlyWhite)
          blackCheckLocations.put("Bishop", i);
        else
          whiteCheckLocations.put("Bishop", i);
        return false;
      } else if (pieceLoc.containsKey(i) && !pieceLoc.get(i).equals("wKing") && !pieceLoc.get(i).equals("bKing")) {
        return true;
      }
    }
    return true;
  }

  private static boolean checking(int loc, String secondary) {
    String opponentQueen = currentlyWhite ? "bQueen" : "wQueen";
    String opponentPiece = currentlyWhite ? "b" + secondary : "w" + secondary;

    if (pieceLoc.containsKey(loc)) {
      String pieceAtLoc = pieceLoc.get(loc);
      return pieceAtLoc.equals(opponentQueen) || pieceAtLoc.equals(opponentPiece);
    }
    return false;
  }
}