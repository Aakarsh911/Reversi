package cs3500.reversi.view;

import cs3500.reversi.model.ReadOnlyModel;
import cs3500.reversi.model.ReversiCell;
import cs3500.reversi.model.ReversiModel;

public class ReversiTextualView implements ReversiView {
  ReadOnlyModel model;

  public ReversiTextualView(ReversiModel model) {
    this.model = model;
  }

  private String drawCell(ReversiCell c) {
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
    int spaces = (model.getBoard().size() - 1) / 2;
    for (int i = 0; i < model.getBoard().size(); i++) {
      for (int k = 0; k < spaces + 1; k++) {
        ans += " ";
      }
      for (int j = 0; j < model.getBoard().get(i).size(); j++) {
        ans += drawCell(model.getBoard().get(i).get(j));
      }
      ans += "\n";
      if (i < (model.getBoard().size() - 1) / 2) {
        spaces--;
      }
      if (i == (model.getBoard().size() - 1) / 2) {
        spaces = 1;
      }
      if (i > (model.getBoard().size() - 1) / 2) {
        spaces++;
      }
    }
    return ans;
  }
}
