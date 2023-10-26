package cs3500.reversi.view;

import cs3500.reversi.model.Hex;
import cs3500.reversi.model.ReadOnlyModel;
import cs3500.reversi.model.ReversiCell;
import cs3500.reversi.model.ReversiModel;

public class ReversiTextualView implements ReversiView {
  ReadOnlyModel model;

  public ReversiTextualView(ReversiModel model) {
    this.model = model;
  }

  private String drawCell(Hex c) {
    String ans = "";
    if (model.getColor(c).toString().equals("EMPTY")) {
      ans += "_ ";
    }
    else if (model.getColor(c).toString().equals("BLACK")) {
      ans += "X ";
    }
    else if (model.getColor(c).toString().equals("WHITE")) {
      ans += "O ";
    }
    return ans;
  }

  @Override
  public String toString() {
    String ans = "";
    for (int i = 0; i < model.getBoard().size(); i++) {
      for (int k = 0; k < Math.abs((model.getBoard().size() / 2) - i); k++) {
        ans += " ";
      }
      for (int j = 0; j < model.getBoard().get(i).size(); j++) {
        ans += drawCell(model.getBoard().get(i).get(j));
      }
      ans += "\n";
    }
    if (model.isGameOver()) {
      ans += "\nGame over!";
    }
    else {
      ans += model.getTurn() + "'s turn";
    }
    return ans;
  }
}
