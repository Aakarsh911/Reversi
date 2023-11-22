package cs3500.reversi.model;

import cs3500.reversi.view.ViewFeatures;

/**
 * Represents a player in the game.
 */
public interface Player {
  /**
   * Gets the color of the player.
   * @return the color of the player
   */
  String getColor();

  /**
   * Notifies the player that it is their turn.
   */
  void notifyTurn();

  void setFeaturesListener(ViewFeatures features);

  boolean isTurn();

  void madeMove();
}
