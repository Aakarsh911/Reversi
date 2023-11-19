package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a basic AI for Reversi. This strategy outputs the move that flips the most cells.
 */
public class BasicAI implements ReversiStrategy {

  @Override
  public Optional<List<Integer>> chooseMove(ReadOnlyModel model, Player player) {
    ArrayList<Integer> bestMove = new ArrayList<>();
    List<List<Hex>> board = model.getBoard();
    int bestScore = Integer.MIN_VALUE;
    for (int rowNum = 0; rowNum < board.size(); rowNum++) {
      for (int colNum = 0; colNum < board.get(rowNum).size(); colNum++) {
        ReversiModel copy = model.copy();
        if (!copy.isLegalMove(rowNum, colNum)) {
          continue;
        }
        copy.move(rowNum, colNum);
        int score = player.getColor().equals("white")
                ? copy.getWhiteScore() : copy.getBlackScore();
        if (score > bestScore) {
          bestMove.removeAll(bestMove);
          bestMove.add(rowNum);
          bestMove.add(colNum);
          bestScore = score;
        }
      }
    }
    if (bestMove.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(bestMove);
  }
}