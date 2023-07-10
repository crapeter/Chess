import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class BuildChess implements ActionListener, Images {
    public static HashMap<Integer, String> pieceLoc = new HashMap<>();
    public JFrame frame = new JFrame();
    public JLabel textField = new JLabel();
    public JPanel titlePanel = new JPanel();
    public static JButton[] buttons = new JButton[64];
    public JPanel panel = new JPanel();
    public boolean white = true;

    public static int location = 0;
    public static final int bKingLocation = 4;
    public static final int wKingLocation = 60;

    public static int[][] whitePawnMove = new int[2][8];
    public static int[][] blackPawnMove = new int[2][8];
    public boolean holdingPiece = false;
    public boolean canBlackCastle = true;
    public boolean canWhiteCastle = true;
    public boolean gameOver = false;
    public String pieceHeld = "";
    public String takingPieceColor = "";
    public String currentPieceColor = "";
    public String[] blackPieces = { "bBishop", "bKing", "bKnight", "bPawn", "bQueen", "bRook" };
    public ImageIcon icon = null;

    public void constructChess() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 950);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        textField.setBackground(Color.white.darker());
        textField.setForeground(Color.black);
        textField.setFont(new Font("Verdana", Font.PLAIN, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Chess");
        textField.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);

        panel.setLayout(new GridLayout(8, 8));
        panel.setEnabled(false);
        panel.setBackground(Color.black);

        for (int i = 0; i < 64; i++) {
            buttons[i] = new JButton();
            panel.add(buttons[i]);
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);

            if (white)
                buttons[i].setBackground(new Color(95, 126, 250).brighter());
            else
                buttons[i].setBackground(new Color(0, 31, 156));

            if (i % 8 != 7)
                white = !white;

            if (i / 8 == 1)
                placePiece(i, bPawn, "bPawn");

            if (i / 8 == 6)
                placePiece(i, wPawn, "wPawn");
        }

        for (int i = 0; i < 64; i++) {
            if (i == 0 || i == 7)
                placePiece(i, bRook, "bRook");
            else if (i == 1 || i == 6)
                placePiece(i, bKnight, "bKnight");
            else if (i == 2 || i == 5)
                placePiece(i, bBishop, "bBishop");
            else if (i == 3)
                placePiece(i, bQueen, "bQueen");
            else if (i == 4)
                placePiece(i, bKing, "bKing");
            else if (i == 56 || i == 63)
                placePiece(i, wRook, "wRook");
            else if (i == 57 || i == 62)
                placePiece(i, wKnight, "wKnight");
            else if (i == 58 || i == 61)
                placePiece(i, wBishop, "wBishop");
            else if (i == 59)
                placePiece(i, wQueen, "wQueen");
            else if (i == 60)
                placePiece(i, wKing, "wKing");
        }

        titlePanel.add(textField);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(panel);
        frame.setVisible(true);

        for (int i = 0; i < 8; i++) {
            int firstWhitePawn = 48;
            int firstBlackPawn = 8;

            whitePawnMove[0][i] = firstWhitePawn + i;
            whitePawnMove[1][i] = 0;
            blackPawnMove[0][i] = firstBlackPawn + i;
            blackPawnMove[1][i] = 0;
        }
    }

    public void pieceMov(int oldLoc, int newLoc, ImageIcon currentIcon, String name) {
        pieceLoc.remove(oldLoc, name);
        pieceLoc.put(newLoc, name);
        buttons[oldLoc].setIcon(null);
        buttons[newLoc].setIcon(currentIcon);
        holdingPiece = false;
    }
    public void removePiece(int loc, String pieceName) {
        pieceLoc.remove(loc, pieceName);
        buttons[loc].setIcon(null);
    }
    public void pickUpPiece(int loc, String pieceName) {
        pieceLoc.remove(loc, pieceName);
        holdingPiece = true;
    }
    public static void placePiece(int loc, ImageIcon newIcon, String pieceName) {
        pieceLoc.put(loc, pieceName);
        buttons[loc].setIcon(newIcon);
    }
    public void grabPiece(int loc, ImageIcon newIcon) {
        pieceHeld = pieceLoc.get(loc);
        location = loc;
        icon = newIcon;
    }
    public void deconstructChess() {
        for (int i = 0; i < 64; i++)
            buttons[i].removeActionListener(this);
        textField.setText("Game Over");
        gameOver = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 64; i++) {
            if (e.getSource() == buttons[i]) {
                if (pieceLoc.containsKey(i) && !holdingPiece) {
                    if (pieceLoc.get(i).equals("wRook") || pieceLoc.get(i).equals("bRook")) {
                        if (pieceLoc.get(i).equals("wRook"))
                            grabPiece(i, wRook);
                        else
                            grabPiece(i, bRook);

                        Rook.move(location, buttons, pieceLoc);

                        pickUpPiece(i, pieceHeld);
                        break;
                    }
                    else if (pieceLoc.get(i).equals("wKnight") || pieceLoc.get(i).equals("bKnight")) {
                        if (pieceLoc.get(i).equals("wKnight"))
                            grabPiece(i, wKnight);
                        else
                            grabPiece(i, bKnight);

                        Knight.move(location, buttons, pieceLoc);

                        pickUpPiece(i, pieceHeld);
                        break;
                    }
                    else if (pieceLoc.get(i).equals("wBishop") || pieceLoc.get(i).equals("bBishop")) {
                        if (pieceLoc.get(i).equals("wBishop"))
                            grabPiece(i, wBishop);
                        else
                            grabPiece(i, bBishop);

                        Bishop.move(location, buttons, pieceLoc);

                        pickUpPiece(i, pieceHeld);
                        break;
                    }
                    else if (pieceLoc.get(i).equals("wQueen") || pieceLoc.get(i).equals("bQueen")) {
                        if (pieceLoc.get(i).equals("wQueen"))
                            grabPiece(i, wQueen);
                        else
                            grabPiece(i, bQueen);

                        Queen.move(location, buttons, pieceLoc);

                        pickUpPiece(i, pieceHeld);
                        break;
                    }
                    else if (pieceLoc.get(i).equals("wKing") || pieceLoc.get(i).equals("wKing")) {
                        if (pieceLoc.get(i).equals("wKing"))
                            grabPiece(i, wKing);
                        else
                            grabPiece(i, bKing);

                        King.move(location, buttons, pieceLoc);
                        Castle.canCastle(location, buttons, pieceLoc, canWhiteCastle, canBlackCastle);

                        pickUpPiece(i, pieceHeld);
                        break;
                    }
                    else if (pieceLoc.get(i).equals("bPawn")) {
                        grabPiece(i, bPawn);

                        Bpawn.move(location, buttons, pieceLoc);

                        pickUpPiece(i, pieceHeld);
                        break;
                    }
                    else if (pieceLoc.get(i).equals("wPawn")) {
                        grabPiece(i, wPawn);

                        Wpawn.move(location, buttons, pieceLoc);

                        pickUpPiece(i, pieceHeld);
                        break;
                    }
                }
                else if (!pieceLoc.containsKey(i)) {
                    if (buttons[i].getText().equals("a")) {
                        if (pieceHeld.equals("bPawn")) {
                            if (i / 8 == 7) {
                                Bpawn bPawn = new Bpawn();
                                removePiece(location, "bPawn");
                                location = i;
                                bPawn.displayChange();
                            }
                            if (i == location + 7) {
                                pieceMov(location, i, bPawn, "bPawn");
                                removePiece(location - 1, "wPawn");
                            }
                            else if (i == location + 9) {
                                pieceMov(location, i, bPawn, "bPawn");
                                removePiece(location + 1, "wPawn");
                            }
                            else if (i != location + 9 && i != location + 7){
                                pieceMov(location, i, icon, pieceHeld);

                                if (blackPawnMove[1][location % 8] == 0)
                                    blackPawnMove[1][location % 8] = i;
                            }
                            else
                                pieceMov(location, location, icon, pieceHeld);
                        }
                        else if (pieceHeld.equals("wPawn")) {
                            if (location / 8 == 0) {
                                Wpawn wPawn = new Wpawn();
                                removePiece(location, "wPawn");
                                location = i;
                                wPawn.displayChange();
                            }
                            if (i == location - 7) {
                                pieceMov(location, i, wPawn, "wPawn");
                                removePiece(location + 1, "bPawn");
                            }
                            else if (i == location - 9) {
                                pieceMov(location, i, wPawn, "wPawn");
                                removePiece(location - 1, "bPawn");
                            }
                            else if (i != location - 9 && i != location - 7){
                                pieceMov(location, i, icon, pieceHeld);

                                if (whitePawnMove[1][location % 8] == 0)
                                    whitePawnMove[1][location % 8] = i;
                            }
                            else
                                pieceMov(location, location, icon, pieceHeld);
                        }
                        else if (pieceHeld.equals("wKing") && (i == 57 || i == 62) && location == wKingLocation && canWhiteCastle) {
                            if (i == 57)
                                pieceMov(56, 58, wRook, "wRook");
                            else
                                pieceMov(63, 61, wRook, "wRook");

                            pieceMov(location, i, icon, pieceHeld);
                            canWhiteCastle = false;
                        }
                        else if (pieceHeld.equals("bKing") && (i == 1 || i == 6) && location == bKingLocation && canBlackCastle) {
                            if (i == 1)
                                pieceMov(0, 2, bRook, "bRook");
                            else
                                pieceMov(7, 5, bRook, "bRook");

                            pieceMov(location, i, icon, pieceHeld);
                            canBlackCastle = false;
                        }
                        else
                            pieceMov(location, i, icon, pieceHeld);
                    }
                    else if (holdingPiece)
                        pieceMov(location, location, icon, pieceHeld);
                }
                else {
                    boolean isCurrentPieceBlack = false;
                    boolean isTakingPieceBlack = false;

                    for (String piece : blackPieces) {
                        if (pieceHeld.equals(piece))
                            isCurrentPieceBlack = true;

                        if (pieceLoc.get(i).equals(piece))
                            isTakingPieceBlack = true;
                    }

                    if (isCurrentPieceBlack)
                        currentPieceColor = "black";
                    else
                        currentPieceColor = "white";

                    if (isTakingPieceBlack)
                        takingPieceColor = "black";
                    else
                        takingPieceColor = "white";

                    if (!currentPieceColor.equals(takingPieceColor) && buttons[i].getText().equals("a")) {
                        if (pieceLoc.get(i).equals("bKing") || pieceLoc.get(i).equals("wKing"))
                            deconstructChess();

                        if (pieceHeld.equals("bPawn") && i / 8 == 7 && !gameOver) {
                            Bpawn bPawn = new Bpawn();
                            removePiece(i, pieceLoc.get(i));
                            removePiece(location, "bPawn");
                            location = i;
                            bPawn.displayChange();
                        }
                        else if (pieceHeld.equals("wPawn") && i / 8 == 0 && !gameOver) {
                            Wpawn wPawn = new Wpawn();
                            removePiece(i, pieceLoc.get(i));
                            removePiece(location, "wPawn");
                            location = i;
                            wPawn.displayChange();
                        }
                        pieceMov(location, i, icon, pieceHeld);
                    }
                    else
                        pieceMov(location, location, icon, pieceHeld);
                }

                for (int j = 0; j < 64; j++)
                    if (!buttons[j].getText().equals(""))
                        buttons[j].setText("");

                System.out.println(pieceHeld + " " + i);
            }
        }
    }
}
