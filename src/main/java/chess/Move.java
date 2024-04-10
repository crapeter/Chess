package chess;

public class Move extends PieceFunctionality {
  private static boolean whiteKingMoved = false;
  private static boolean blackKingMoved = false;

  public static void grab(int loc) {
    switch (pieceLoc.get(loc)) {
      case "wPawn" -> {
        grabPiece(loc, wPawn);
        Wpawn.display();
        pickUpPiece(loc, pieceHeld);
      }
      case "bPawn" -> {
        grabPiece(loc, bPawn);
        Bpawn.display();
        pickUpPiece(loc, pieceHeld);
      }
      case "wKing" -> {
        grabPiece(loc, wKing);
        King.display();
        King.castle();
        pickUpPiece(loc, pieceHeld);
      }
      case "bKing" -> {
        grabPiece(loc, bKing);
        King.display();
        King.castle();
        pickUpPiece(loc, pieceHeld);
      }
      case "wQueen" -> {
        grabPiece(loc, wQueen);
        Queen.display();
        pickUpPiece(loc, pieceHeld);
      }
      case "bQueen" -> {
        grabPiece(loc, bQueen);
        Queen.display();
        pickUpPiece(loc, pieceHeld);
      }
      case "wBishop" -> {
        grabPiece(loc, wBishop);
        Bishop.display();
        pickUpPiece(loc, pieceHeld);
      }
      case "bBishop" -> {
        grabPiece(loc, bBishop);
        Bishop.display();
        pickUpPiece(loc, pieceHeld);
      }
      case "wKnight" -> {
        grabPiece(loc, wKnight);
        Knight.display();
        pickUpPiece(loc, pieceHeld);
      }
      case "bKnight" -> {
        grabPiece(loc, bKnight);
        Knight.display();
        pickUpPiece(loc, pieceHeld);
      }
      case "wRook" -> {
        grabPiece(loc, wRook);
        Rook.display();
        pickUpPiece(loc, pieceHeld);
      }
      case "bRook" -> {
        grabPiece(loc, bRook);
        Rook.display();
        pickUpPiece(loc, pieceHeld);
      }
      default -> System.out.println("How the fuck did you get here?");
    }
  }

  public static void empty(int loc) {
    if (!buttons[loc].getText().equals("a")) {
      swapPiece(location, location, icon, pieceHeld);
      if (loc != location)
        System.out.println("Choose a legal move bozo");
    } else { // move is available
      switch (pieceHeld) {
        case "wPawn" -> {
          if (loc / 8 == 0) {
            promotePawn(loc, "White");
          } else if (loc == location - 7 || loc == location - 9) {
            boolean upLeft = (loc == location - 9); // location of opposing pawn
            removePiece(upLeft ? location - 1 : location + 1, "bPawn", true);
          }
          // setting en passant if applicable
          if (Math.abs(loc - location) == 16)
            whiteEnPassant[location % 8] = true;

          // ensuring that only one pawn is en passant able at a time
          for (int i = 0; i < 8; i++) {
            if (i == location % 8)
              continue;
            whiteEnPassant[i] = false;
          }
        }
        case "bPawn" -> {
          if (loc / 8 == 7) {
            promotePawn(loc, "Black");
          } else if (loc == location + 7 || loc == location + 9) {
            boolean downLeft = (loc == location + 7); // location of opposing pawn
            removePiece(downLeft ? location - 1 : location + 1, "wPawn", true);
          }
          // setting en passant if applicable
          if (Math.abs(loc - location) == 16)
            blackEnPassant[location % 8] = true;

          // ensuring that only one pawn is en passant able at a time
          for (int i = 0; i < 8; i++) {
            if (i == location % 8)
              continue;
            blackEnPassant[i] = false;
          }
        }
        case "wKing" -> {
          // checking for castling and then moving the Knight
          // the king gets moved at the end of the function
          if (loc == 57 && canWhiteCastle1 && !whiteKingMoved)
            swapPiece(56, 58, wRook, "wRook");
          if (loc == 62 && canWhiteCastle2 && !whiteKingMoved)
            swapPiece(63, 61, wRook, "wRook");
        }
        case "bKing" -> {
          // checking for castling and then moving the Knight
          // the king gets moved at the end of the function
          if (loc == 1 && canBlackCastle1 && !blackKingMoved)
            swapPiece(0, 2, bRook, "bRook");
          if (loc == 6 && canBlackCastle1 && !blackKingMoved)
            swapPiece(7, 5, bRook, "bRook");
        }
      }
      swapPiece(location, loc, icon, pieceHeld);
      currentlyWhite = !currentlyWhite;
      numberOfMoves++;
      checkBlackCastle();
      checkWhiteCastle();
    }
  }

  public static void take(int loc) {
    String[] blackPieces = { "bBishop", "bKing", "bKnight", "bPawn", "bQueen", "bRook" };
    String currentPieceColor = "white";
    String takingPieceColor = "white";

    // gets the color of the two pieces
    for (String piece : blackPieces) {
      if (pieceHeld.equals(piece))
        currentPieceColor = "black";
      if (pieceLoc.get(loc).equals(piece))
        takingPieceColor = "black";
    }

    if (!currentPieceColor.equals(takingPieceColor) && buttons[loc].getText().equals("a")) {
      /*
       * checking to see if the pawn is taking a piece on the top or bottom rows of
       * the board if the pawn is taking a piece and the piece is not the king, the
       * pawn will be promoted
       */
      if (pieceHeld.equals("wPawn") && loc / 8 == 0 && !pieceLoc.get(loc).equals("bKing")) {
        removePiece(loc, pieceLoc.get(loc), false);
        promotePawn(loc, "White");
      } else if (pieceHeld.equals("bPawn") && loc / 8 == 7 && !pieceLoc.get(loc).equals("wKing")) {
        removePiece(loc, pieceLoc.get(loc), false);
        promotePawn(loc, "Black");
      } else {
        removePiece(loc, pieceHeld, false);
        swapPiece(location, loc, icon, pieceHeld);
      }
      currentlyWhite = !currentlyWhite;
      numberOfMoves++;
    } else {
      swapPiece(location, location, icon, pieceHeld);
      System.out.println("Choose a legal move bozo");
    }
  }

  private static void promotePawn(int loc, String color) {
    Pawn pawn = new Pawn();
    promoting = true;
    swapPiece(location, loc, icon, pieceHeld);
    location = loc;
    pawn.change(color);
  }

  private static void checkWhiteCastle() {
    if (!whiteKingMoved) {
      if (!pieceLoc.containsKey(56) || (pieceLoc.containsKey(56) && !pieceLoc.get(56).equals("wRook")))
        canWhiteCastle1 = false;
      if (!pieceLoc.containsKey(63) || (pieceLoc.containsKey(63) && !pieceLoc.get(63).equals("wRook")))
        canWhiteCastle2 = false;
      if (!pieceLoc.containsKey(60) || (pieceLoc.containsKey(60) && !pieceLoc.get(60).equals("wKing"))) {
        System.out.println("Hello there");
        canWhiteCastle1 = false;
        canWhiteCastle2 = false;
        whiteKingMoved = true;
      }
    }
  }

  private static void checkBlackCastle() {
    if (!blackKingMoved) {
      if (!pieceLoc.containsKey(0) || pieceLoc.containsKey(0) && (!pieceLoc.get(0).equals("bRook")))
        canBlackCastle1 = false;
      if (!pieceLoc.containsKey(7) || pieceLoc.containsKey(7) && (!pieceLoc.get(7).equals("bRook")))
        canBlackCastle2 = false;
      if ((!pieceLoc.containsKey(4) || pieceLoc.containsKey(4) && (!pieceLoc.get(4).equals("bKing")))) {
        System.out.println("Hello there");
        canBlackCastle1 = false;
        canBlackCastle2 = false;
        blackKingMoved = true;
      }
    }
  }
}
