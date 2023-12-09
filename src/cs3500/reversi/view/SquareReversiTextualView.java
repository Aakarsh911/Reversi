package cs3500.reversi.view;

import cs3500.reversi.model.ReversiModel;

public class SquareReversiTextualView implements ReversiView {
  private final ReversiModel model;

  public SquareReversiTextualView(ReversiModel model) {
    this.model = model;
  }


  @Override
  public String toString() {
    String ans = "";
    for (int i = 0; i < model.getBoard().size(); i++) {
      for (int j = 0; j < model.getBoard().get(i).size(); j++) {
        if (model.getColor(model.getBoard().get(i).get(j)).equals("EMPTY")) {
          ans += "_ ";
        } else if (model.getColor(model.getBoard().get(i).get(j)).equals("BLACK")) {
          ans += "X ";
        } else if (model.getColor(model.getBoard().get(i).get(j)).equals("WHITE")) {
          ans += "O ";
        }
      }
      ans += "\n";
    }
    return ans;
  }
}
