package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a MiniMax AI for Reversi. This strategy outputs the move that flips the least cells
 * for the next player.
 */
public class MiniMaxAI implements ReversiStrategy {
  private final BasicAI basicAI = new BasicAI();

  @Override
  public Optional<List<Integer>> chooseMove(ReadOnlyModel model, Player player) {
    List<List<Hex>> board = model.getBoard();
    ArrayList<ArrayList<Integer>> possibleMoves = new ArrayList<>();
    // Find all possible moves
    for (int rowNum = 0; rowNum < board.size(); rowNum++) {
      for (int colNum = 0; colNum < board.get(rowNum).size(); colNum++) {
        try {
          if (model.isLegalMove(rowNum, colNum)) {
            ArrayList<Integer> move = new ArrayList<>();
            move.add(rowNum);
            move.add(colNum);
            possibleMoves.add(move);
          }
        } catch (IllegalStateException e) {
          continue;
        }
      }
    }
    // Find the move with the minimum flips for the next player using BasicAI's evaluation
    int minFlips = Integer.MAX_VALUE;
    List<Integer> bestMove = new ArrayList<>();

    for (ArrayList<Integer> move : possibleMoves) {
      try {
        // Create a copy of the board and play the move on the copy
        ReversiModel copyModel = model.copy();
        copyModel.move(move.get(0), move.get(1));

        // Use BasicAI to evaluate the move on the copied board
        List<Integer> basicMove = basicAI.chooseMove(copyModel, player).get();
        copyModel.move(basicMove.get(0), basicMove.get(1));
        int flips = player.getColor().equals("white") ? copyModel.getWhiteScore()
                : copyModel.getBlackScore();

        if (flips < minFlips) {
          minFlips = flips;
          bestMove = move;
        }
      } catch (Exception e) {
        continue;
      }
    }
    if (bestMove.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(bestMove);
  }
}