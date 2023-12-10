package cs3500.reversi.view;

import cs3500.reversi.model.BoardPiece;
import cs3500.reversi.model.ReadOnlyModel;
import cs3500.reversi.model.ReversiModel;

/**
 * Represents a textual view for the Reversi game.
 */
public class ReversiTextualView implements ReversiView {
  ReadOnlyModel model; // the model to be rendered

  /**
   * Constructs a textual view for the given model.
   * @param model the model to be rendered
   */
  public ReversiTextualView(ReversiModel model) {
    this.model = model;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof ReversiTextualView) {
      ReversiTextualView v = (ReversiTextualView) o;
      return this.model.equals(v.model);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.model.hashCode();
  }

  private String drawCell(BoardPiece c) {
    String ans = "";
    if (model.getColor(c).equals("EMPTY")) {
      ans += "_ ";
    }
    else if (model.getColor(c).equals("BLACK")) {
      ans += "X ";
    }
    else if (model.getColor(c).equals("WHITE")) {
      ans += "O ";
    }
    return ans;
  }

  @Override
  public String toString() {
    String ans = "";
    for (int rowNum = 0; rowNum < model.getBoard().size(); rowNum++) {
      for (int currSpaceCount = 0; currSpaceCount <
              Math.abs((model.getBoard().size() / 2) - rowNum); currSpaceCount++) {
        ans += " ";
      }
      for (int numCol = 0; numCol < model.getBoard().get(rowNum).size(); numCol++) {
        ans += drawCell(model.getBoard().get(rowNum).get(numCol));
      }
      ans += "\n";
    }
    if (model.isGameOver()) {
      ans += "\nGame over!";
    }
    else {
      ans += model.getTurn() + "'s turn\n";
    }
    ans += "\nScore: Black - " + model.getBlackScore() + " , White - " + model.getWhiteScore();
    return ans;
  }
}
