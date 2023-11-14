package cs3500.reversi.model;

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
}
