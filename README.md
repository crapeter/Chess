# Scuffed_Chess
Chess game built in Java using the jSwing GUI framework

This game of chess has all the features of a normal chess board except Check and Checkmate. I was unable to find a way to have check and checkmate run quickly, this is mainly due to the poor way that I set up the initial project. Despite the poor setup, en passant and castling are available in this verison of chess. The way the game works is by displaying the available moves on the chess board and then checking to see if the move available. If the move is available, the game removes the icon from the previous spot and removes the location and name from a hashmap, updating the board and hashmap with the new location.
