package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Represents a strategy that chooses the first corner it can.
 */
public class CornerAI implements ReversiStrategy {

  @Override
  public Optional<List<Integer>> chooseMove(BasicReversi model, Player player) {
    if (!model.anyLegalMovesForCurrentPlayer()) {
      model.pass();
      return Optional.empty();
    }
    List<List<Integer>> corners = getCorners(model);

    for (int i = 0; i < corners.size(); i++) {
      if (!model.getCellsToFlip(corners.get(i).get(0), corners.get(i).get(1)).isEmpty()) {
        return Optional.of(corners.get(i));
      }
    }
    return Optional.empty();
  }

  /**
   * Gets the corners of the board.
   *
   * @param model the model to get the corners from
   * @return the corners of the board
   */
  static List<List<Integer>> getCorners(BasicReversi model) {
    ArrayList<Integer> corner1 = new ArrayList<>(Arrays.asList(0, 0));
    ArrayList<Integer> corner2 = new ArrayList<>(Arrays.asList(0, (model.getBoard().size() - 1) / 2));
    ArrayList<Integer> corner3 = new ArrayList<>(Arrays.asList((model.getBoard().size() - 1) / 2, 0));
    ArrayList<Integer> corner4 = new ArrayList<>(Arrays.asList((model.getBoard().size() - 1) / 2, model.getBoard().size() - 1));
    ArrayList<Integer> corner5 = new ArrayList<>(Arrays.asList((model.getBoard().size() - 1), 0));
    ArrayList<Integer> corner6 = new ArrayList<>(Arrays.asList(model.getBoard().size() - 1, (model.getBoard().size() - 1) / 2));
    List<List<Integer>> corners = new ArrayList<>(Arrays.asList(corner1, corner2, corner3, corner4, corner5, corner6));
    return corners;
  }
}