package cs3500.reversi.controller;

import cs3500.reversi.model.ModelListener;
import cs3500.reversi.model.Player;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.view.ReversiGraphicalView;
import cs3500.reversi.view.ViewFeatures;

public class ReversiController implements ViewFeatures, ModelListener {
  private final ReversiModel model;
  private final Player player;
  private final ReversiGraphicalView view;

  public ReversiController(ReversiModel model, Player player, ReversiGraphicalView view) {
    this.model = model;
    this.player = player;
    this.view = view;
    this.player.setFeaturesListener(this);
    this.view.addFeaturesListener(this);
    this.model.addListener(this);
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
      }
      else {
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
    }
    catch (IllegalStateException e) {
      isSuccessful = false;
      player.notifyTurn();
      view.showErrorMessage("Not a valid move for " + player.getColor() + "!");
    }
    catch (UnsupportedOperationException e) {
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
    }
    else {
      view.updateTurn("Opponent's turn");
    }
  }

  @Override
  public void notifyTurn() {
    if (model.getTurn().equalsIgnoreCase(player.getColor())) {
      view.updateTurn("Your turn");
    }
    else {
      view.updateTurn("Opponent's turn");
    }
    this.view.refresh();
    player.notifyTurn();
  }
}
