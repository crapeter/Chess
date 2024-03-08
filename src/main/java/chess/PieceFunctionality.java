package chess;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class PieceFunctionality extends PieceUtils implements ActionListener, SetupVars, Images {
  public static int[][] whitePawnMove = new int[2][8];
  public static int[][] blackPawnMove = new int[2][8];

  public void addFunctionality() {
    int firstWhitePawn = 48;
    int firstBlackPawn = 8;
    currentlyWhite = true;
    for (int i = 0; i < 64; i++) {
      buttons[i].addActionListener(this);
    }
    for (int i = 0; i < 8; i++) {
      whitePawnMove[0][i] = firstWhitePawn + i;
      blackPawnMove[0][i] = firstBlackPawn + i;
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    boolean whiteKing = false;
    boolean blackKing = false;
    String[] whitePieces = {"wPawn", "wRook", "wKnight", "wBishop", "wQueen", "wKing"};
    List<String> whitePiece = Arrays.asList(whitePieces);
    for (int i = 0; i < 64; i++) {
      if (e.getSource() == buttons[i]) {
        if (gameOver) {
          break;
        }
        if (numberOfMoves == 100) {
          gameOver = true;
          for (int j = 0; j < 64; j++) {
            buttons[j].removeActionListener(this);
          }
          textField.setText("Stalemate");
          break;
        }
        else if (pieceLoc.containsKey(i) && !holdingPiece && !promoting) {
          if ( (currentlyWhite && whitePiece.contains(pieceLoc.get(i))) || (!currentlyWhite && !whitePiece.contains(pieceLoc.get(i))) ) {
            Move.grab(i);
          } else {
            String color = currentlyWhite ? "white" : "black";
            System.out.println("It is " + color + "'s turn to move");
          }
          break;
        } else if (pieceLoc.containsKey(i) && holdingPiece && !promoting) {
          if ( (currentlyWhite && whitePiece.contains(pieceLoc.get(i))) || (!currentlyWhite && !whitePiece.contains(pieceLoc.get(i))) ) {
            placePiece(location, icon, pieceHeld);
            resetBoardColor();
            Move.grab(i);
            break;
          } else {
            Move.take(i);
            resetBoardColor();
            Attacking attacking = new Attacking(i);
            attacking.isAttacking();
          }
        } else if (!pieceLoc.containsKey(i) && holdingPiece && !promoting) {
          Move.empty(i);
          resetBoardColor();
          Attacking attacking = new Attacking(i);
          attacking.isAttacking();
        }
        for (int j = 0; j < 64; j++) {
          if (!buttons[j].getText().isEmpty()) {
            buttons[j].setText("");
          }
          if (pieceLoc.containsKey(j) && pieceLoc.get(j).equals("bKing")) {
            blackKing = true;
          }
          if (pieceLoc.containsKey(j) && pieceLoc.get(j).equals("wKing")) {
            whiteKing = true;
          }
        }
        if (!whiteKing || !blackKing) {
          gameOver = true;
          numberOfMoves = 0;
          for (int j = 0; j < 64; j++) {
            buttons[j].removeActionListener(this);
          }
          Font font = forfeit.getFont();
          Font newFont = font.deriveFont(18f);
          forfeit.setFont(newFont);
          forfeit.setText("Restart");
          textField.setText(whiteKing ? "White wins" : "Black wins");
        }
        String columns = "abcdefgh";
        String rows = "87654321";
        String move = String.valueOf(columns.charAt(i % 8)) + rows.charAt(i / 8);
        String pName = pieceName();
        System.out.println(pName + " moved to: " + move);
      }
    }
  }
  private static String pieceName() {
    String temp = "";
    switch (pieceHeld) {
      case "wPawn" -> temp = "White Pawn";
      case "bPawn" -> temp = "Black Pawn";
      case "wKing" -> temp = "White King";
      case "bKing" -> temp = "Black King";
      case "wQueen" -> temp = "White Queen";
      case "bQueen" -> temp = "Black Queen";
      case "wBishop" -> temp = "White Bishop";
      case "bBishop" -> temp = "Black Bishop";
      case "wKnight" -> temp = "White Knight";
      case "bKnight" -> temp = "Black Knight";
      case "wRook" -> temp = "White Rook";
      case "bRook" -> temp = "Black Rook";
      default -> System.out.println("How the fuck did you get here?");
    }
    return temp;
  }
}