package cs3500.reversi.controller;

import cs3500.reversi.model.Player;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.view.ReversiGraphicalView;
import cs3500.reversi.view.ViewFeatures;

public class ReversiController implements ViewFeatures {
  private final ReversiModel model;
  private final Player player;
  private final ReversiGraphicalView view;

  public ReversiController(ReversiModel model, Player player, ReversiGraphicalView view) {
    this.model = model;
    this.player = player;
    this.view = view;
    this.player.setFeaturesListener(this);
    this.view.addFeaturesListener(this);
  }

  @Override
  public void move(int row, int col) {
    if (model.isGameOver()) {
      return;
    }
    if (!player.isTurn()) {
      view.showErrorMessage("Not your turn!");
      return;
    }
    try {
      player.madeMove();
      model.move(row, col);
    }
    catch (IllegalStateException e) {
      player.notifyTurn();
      view.showErrorMessage("Not a valid move for " + player.getColor() + "!");
    }
    catch (Exception e) {
      player.notifyTurn();
      view.showErrorMessage("No cell seleced!");
    }
  }

  @Override
  public void pass() {
    if (model.isGameOver()) {
      return;
    }
    if (!player.isTurn()) {
      view.showErrorMessage("Not your turn!");
      return;
    }
    player.madeMove();
    model.pass();
  }
}
