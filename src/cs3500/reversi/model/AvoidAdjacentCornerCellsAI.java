package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Represents a strategy that chooses a move that is not adjacent to the corner cells.
 */
public class AvoidAdjacentCornerCellsAI implements ReversiStrategy {
  @Override
  public Optional<List<Integer>> chooseMove(ReadOnlyModel model, Player player) {
    List<List<Integer>> corners = CornerAI.getCorners(model);

    ArrayList<ArrayList<Integer>> possibleMoves = new ArrayList<>();
    // Find all possible moves that are not adjacent to the border
    removeAllAdjacentMoves(model, corners, possibleMoves);

    if (possibleMoves.isEmpty()) {
      return Optional.empty();
    }

    // Return the move that flips the most pieces
    int maxFlips = Integer.MIN_VALUE;
    List<Integer> bestMove = getMoveWithMostFlips(model, possibleMoves,
            maxFlips, new ArrayList<>(), player);
    return Optional.of(bestMove);
  }

  private static boolean isValidCell(ReadOnlyModel board, int row, int col) {
    return row >= 0 && row < board.getBoard().size() && col >= 0
            && col < board.getBoard().get(row).size();
  }

  private static List<Integer> getMoveWithMostFlips(ReadOnlyModel board, ArrayList<ArrayList<Integer>>
          possibleMoves, int maxFlips, List<Integer> bestMove, Player player) {
    for (ArrayList<Integer> move : possibleMoves) {
      ReversiModel copy = board.copy();
      if (!copy.isLegalMove(move.get(0), move.get(1))) {
        continue;
      }
      copy.move(move.get(0), move.get(1));
      int score = player.getColor().equals("white") ? copy.getWhiteScore() : copy.getBlackScore();
      if (score > maxFlips) {
        maxFlips = score;
        bestMove = move;
      }
    }
    return bestMove;
  }

  private static void removeAllAdjacentMoves(ReadOnlyModel board,
                                             List<List<Integer>> adjacentBorderCell,
                                             ArrayList<ArrayList<Integer>> possibleMoves) {
    for (int rowNum = 0; rowNum < board.getBoard().size(); rowNum++) {
      for (int colNum = 0; colNum < board.getBoard().get(rowNum).size(); colNum++) {
        ArrayList<Integer> move = new ArrayList<>();
        move.add(rowNum);
        move.add(colNum);
        int[][] hexagonAdjacency = {
                {-1, -1}, {-1, 0}, {0, -1}, {0, 1}, {1, 0}, {1, 1}, {-1, 1}, {1, -1}
        };
        boolean isAdjacent = false;
        for (int i = 0; i < hexagonAdjacency.length; i++) {
          int[] adjacentCell = hexagonAdjacency[i];
          int adjacentRow = rowNum + adjacentCell[0];
          int adjacentCol = colNum + adjacentCell[1];
          if (isValidCell(board, adjacentRow, adjacentCol) &&
                  adjacentBorderCell.contains(Arrays.asList(adjacentRow, adjacentCol))) {
            isAdjacent = true;
            break;
          }
        }
        if (!isAdjacent) {
          possibleMoves.add(move);
        }
      }
    }
  }
}