package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a basic AI for Reversi. This strategy outputs the move that flips the most cells.
 */
public class BasicAI implements ReversiStrategy {

  @Override
  public Optional<List<Integer>> chooseMove(BasicReversi model, Player player) {
    if (!model.anyLegalMovesForCurrentPlayer()) {
      model.pass();
      return Optional.empty();
    }
    ArrayList<Integer> bestMove = new ArrayList<>();
    List<List<Hex>> board = model.getBoard();
    int bestScore = Integer.MIN_VALUE;
    for (int rowNum = 0; rowNum < board.size(); rowNum++) {
      for (int colNum = 0; colNum < board.get(rowNum).size(); colNum++) {
        try {
          if (model.getCellsToFlip(rowNum, colNum).size() > bestScore) {
            bestMove.removeAll(bestMove);
            bestMove.add(rowNum);
            bestMove.add(colNum);
            bestScore = model.getCellsToFlip(rowNum, colNum).size();
          }
        } catch (IllegalStateException e) {
          continue;
        }
      }
    }
    return Optional.of(bestMove);
  }
}