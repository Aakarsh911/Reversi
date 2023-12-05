package cs3500.reversi.provider.controller;

import java.util.List;



/**
 * Interface for observers of a player's actions in a game of Reversi.
 */
public interface PlayerActionObserver {
  /**
   * Called when the player decides to place a piece.
   * @param hexCoordinates List of Hexagon coordinates of the location that the player chose
   */
  void move(List<Integer> hexCoordinates);

  /**
   * Called when the player decides to pass their turn.
   */
  void pass();
}
