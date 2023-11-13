package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AvoidAdjacentBorderCellsAI implements ReversiStrategy {
  @Override
  public Optional<List<Integer>> chooseMove(BasicReversi board, int player) {
    ArrayList<ArrayList<Integer>> adjacentBorderCell = new ArrayList<>();
    for (int i = 0; i < board.getBoard().size(); i++) {
      for (int j = 0; j < board.getBoard().get(i).size(); j++) {
        if ((i != 0 && (j == 1 || j == board.getBoard().get(i).size() - 2) && i != board.getNumRows() - 1)) {
          adjacentBorderCell.add(new ArrayList<>(Arrays.asList(i, j)));
        }
      }
    }
    ArrayList<ArrayList<Integer>> possibleMoves = new ArrayList<>();
    // Find all possible moves that are not adjacent to the border
    for (int rowNum = 0; rowNum < board.getBoard().size(); rowNum++) {
      for (int colNum = 0; colNum < board.getBoard().get(rowNum).size(); colNum++) {
        try {
          if (!board.getCellsToFlip(rowNum, colNum).isEmpty()) {
            ArrayList<Integer> move = new ArrayList<>();
            move.add(rowNum);
            move.add(colNum);
            if (!adjacentBorderCell.contains(move)) {
              possibleMoves.add(move);
            }
          }
        } catch (IllegalStateException e) {
          continue;
        }
      }
    }
    if (possibleMoves.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(possibleMoves.get(0));
  }
}
