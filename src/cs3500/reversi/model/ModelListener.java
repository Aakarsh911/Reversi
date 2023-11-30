package cs3500.reversi.model;

/**
 * Represents a listener for the model.
 */
public interface ModelListener {
  /**
   * Notifies the listener that it is their player's turn.
   */
  void notifyTurn();
}
