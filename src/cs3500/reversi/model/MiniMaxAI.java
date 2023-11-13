package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MiniMaxAI implements ReversiStrategy {
  private final BasicAI basicAI = new BasicAI();

  @Override
  public Optional<List<Integer>> chooseMove(BasicReversi model, int player) {
    List<List<Hex>> board = model.getBoard();
    ArrayList<ArrayList<Integer>> possibleMoves = new ArrayList<>();
    // Find all possible moves
    for (int rowNum = 0; rowNum < board.size(); rowNum++) {
      for (int colNum = 0; colNum < board.get(rowNum).size(); colNum++) {
        try {
          if (!model.getCellsToFlip(rowNum, colNum).isEmpty()) {
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
        BasicReversi copyModel = model.copy();
        copyModel.move(move.get(0), move.get(1));

        // Use BasicAI to evaluate the move on the copied board
        List<Integer> basicMove = basicAI.chooseMove(copyModel, player).isPresent() ? basicAI.chooseMove(copyModel, player).get() : new ArrayList<>();
        int flips = copyModel.getCellsToFlip(basicMove.get(0), basicMove.get(1)).size();

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
