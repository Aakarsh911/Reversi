package cs3500.reversi.model;

/**
 * Represents a player in the game.
 */
public interface Player {
  String getColor();
  void notifyTurn();
}
