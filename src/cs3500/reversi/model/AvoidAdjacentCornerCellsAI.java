package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AvoidAdjacentCornerCellsAI implements ReversiStrategy {
  @Override
  public Optional<List<Integer>> chooseMove(BasicReversi board, int player) {
    List<List<Integer>> corners = CornerAI.getCorners(board);

    ArrayList<ArrayList<Integer>> possibleMoves = new ArrayList<>();
    // Find all possible moves that are not adjacent to the border
    removeAllAdjacentMoves(board, corners, possibleMoves);
    System.out.println(possibleMoves);

    if (possibleMoves.isEmpty()) {
      return Optional.empty();
    }

    // Return the move that flips the most pieces
    int maxFlips = Integer.MIN_VALUE;
    List<Integer> bestMove = getMoveWithMostFlips(board, possibleMoves, maxFlips, new ArrayList<>());
    return Optional.of(bestMove);
  }

  private static boolean isValidCell(BasicReversi board, int row, int col) {
    return row >= 0 && row < board.getBoard().size() && col >= 0 && col < board.getBoard().get(row).size();
  }

  private static List<Integer> getMoveWithMostFlips(BasicReversi board, ArrayList<ArrayList<Integer>>
          possibleMoves, int maxFlips, List<Integer> bestMove) {
    for (ArrayList<Integer> move : possibleMoves) {
      try {
        if (board.getCellsToFlip(move.get(0), move.get(1)).size() > maxFlips) {
          maxFlips = board.getCellsToFlip(move.get(0), move.get(1)).size();
          bestMove = move;
        }
      } catch (IllegalStateException e) {
        continue;
      }
    }
    return bestMove;
  }

  private static void removeAllAdjacentMoves(BasicReversi board, List<List<Integer>>
          adjacentBorderCell, ArrayList<ArrayList<Integer>> possibleMoves) {
    for (int rowNum = 0; rowNum < board.getBoard().size(); rowNum++) {
      for (int colNum = 0; colNum < board.getBoard().get(rowNum).size(); colNum++) {
        try {
          if (!board.getCellsToFlip(rowNum, colNum).isEmpty()) {
            ArrayList<Integer> move = new ArrayList<>();
            move.add(rowNum);
            move.add(colNum);
            int[][] hexagonAdjacency = {
                    {-1, -1}, {-1, 0}, {0, -1}, {0, 1}, {1, 0}, {1, 1}, {-1, 1}, {1, -1}
            };
            for (int i = 0; i < hexagonAdjacency.length; i++) {
              int[] adjacentCell = hexagonAdjacency[i];
              if (isValidCell(board, rowNum + adjacentCell[0], colNum + adjacentCell[1])) {
                ArrayList<Integer> adjacentCellList = new ArrayList<>(Arrays.asList(rowNum + adjacentCell[0], colNum + adjacentCell[1]));
                if (!adjacentBorderCell.contains(adjacentCellList)) {
                  possibleMoves.add(move);
                }
              }
            }
          }
        } catch (IllegalStateException e) {
          continue;
        }
      }
    }
  }
}
