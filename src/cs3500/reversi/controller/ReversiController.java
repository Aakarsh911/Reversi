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
    model.move(row, col);
  }

  @Override
  public void pass() {
    if (model.isGameOver()) {
      return;
    }
    model.pass();
  }
}
