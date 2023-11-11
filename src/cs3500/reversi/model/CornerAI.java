package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CornerAI implements ReversiStrategy {
  private final AvoidAdjacentBorderCellsAI notAdjacentAI = new AvoidAdjacentBorderCellsAI();

  @Override
  public List<Integer> chooseMove(BasicReversi model, int player) {
    ArrayList<Integer> bestMove = new ArrayList<>();
    ArrayList<Integer> corner1 = new ArrayList<>(Arrays.asList(0, 0));
    ArrayList<Integer> corner2 = new ArrayList<>(Arrays.asList(0, (model.getBoard().size() - 1) / 2));
    ArrayList<Integer> corner3 = new ArrayList<>(Arrays.asList((model.getBoard().size() - 1) / 2, 0));
    ArrayList<Integer> corner4 = new ArrayList<>(Arrays.asList((model.getBoard().size() - 1) / 2, model.getBoard().size() - 1));
    ArrayList<Integer> corner5 = new ArrayList<>(Arrays.asList((model.getBoard().size() - 1), 0));
    ArrayList<Integer> corner6 = new ArrayList<>(Arrays.asList(model.getBoard().size() - 1, (model.getBoard().size() - 1) / 2));
    List<List<Integer>> corners = new ArrayList<>(Arrays.asList(corner1, corner2, corner3, corner4, corner5, corner6));
    ArrayList<ArrayList<Integer>> possibleMoves = new ArrayList<>();
    // Find all possible moves
    for (int rowNum = 0; rowNum < model.getBoard().size(); rowNum++) {
      for (int colNum = 0; colNum < model.getBoard().get(rowNum).size(); colNum++) {
        try {
          if (!model.getCellsToFlip(rowNum, colNum).isEmpty()) {
            ArrayList<Integer> move = new ArrayList<>();
            move.add(rowNum);
            move.add(colNum);
            possibleMoves.add(move);
            if (corners.contains(move)) {
              return move;
            }
          }
        } catch (IllegalStateException e) {
          continue;
        }
      }
    }
    if (possibleMoves.isEmpty()) {
      return notAdjacentAI.chooseMove(model, player);
    }
    return bestMove;
  }
}
