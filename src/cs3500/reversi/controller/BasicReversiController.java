package cs3500.reversi.controller;

import cs3500.reversi.model.ModelListener;
import cs3500.reversi.model.Player;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.view.ReversiGraphicalView;
import cs3500.reversi.view.ViewFeatures;

/**
 * Represents a controller for the game of Reversi.
 * This controller is responsible for handling user input and updating the model and view
 */
public class BasicReversiController implements ViewFeatures, ModelListener, ReversiController {
  private final ReversiModel model;
  private final Player player;
  private final ReversiGraphicalView view;

  /**
   * Constructs a BasicReversiController.
   *
   * @param model  the model
   * @param player the player
   * @param view   the view
   */
  public BasicReversiController(ReversiModel model, Player player, ReversiGraphicalView view) {
    this.model = model;
    this.player = player;
    this.view = view;
  }

  @Override
  public void move(int row, int col) {
    boolean isSuccessful = true;
    if (model.isGameOver()) {
      view.showErrorMessage("Game is over!");
      return;
    }
    if (!player.isTurn()) {
      view.showErrorMessage("Not your turn!");
      return;
    }
    isSuccessful = tryMove(row, col, isSuccessful);
    if (isSuccessful) {
      if (model.getTurn().equalsIgnoreCase(player.getColor())) {
        view.updateTurn("Your turn");
      } else {
        view.updateTurn("Opponent's turn");
      }
    }
  }

  private boolean tryMove(int row, int col, boolean isSuccessful) {
    try {
      player.madeMove();
      if (row == -1 && col == -1) {
        throw new UnsupportedOperationException("Not implemented yet!");
      }
      model.move(row, col);
    } catch (IllegalStateException e) {
      isSuccessful = false;
      player.notifyTurn();
      view.showErrorMessage("Not a valid move for " + player.getColor() + "!");
    } catch (UnsupportedOperationException e) {
      isSuccessful = false;
      player.notifyTurn();
      view.showErrorMessage("No cell seleced!");
    }
    return isSuccessful;
  }

  @Override
  public void pass() {
    if (model.isGameOver()) {
      view.showErrorMessage("Game is over!");
      return;
    }
    if (!player.isTurn()) {
      view.showErrorMessage("Not your turn!");
      return;
    }
    player.madeMove();
    model.pass();
    if (model.getTurn().equalsIgnoreCase(player.getColor())) {
      view.updateTurn("Your turn");
    } else {
      view.updateTurn("Opponent's turn");
    }
  }

  @Override
  public void notifyTurn() {
    if (model.getTurn().equalsIgnoreCase(player.getColor())) {
      view.updateTurn("Your turn");
    } else {
      view.updateTurn("Opponent's turn");
    }
    this.view.refresh();
    player.notifyTurn();
  }

  @Override
  public void goNow() {
    this.player.setFeaturesListener(this);
    this.view.addFeaturesListener(this);
    this.model.addListener(this);
  }
}
