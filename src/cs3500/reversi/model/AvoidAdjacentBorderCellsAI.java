package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AvoidAdjacentBorderCellsAI implements ReversiStrategy {
  @Override
  public Optional<List<Integer>> chooseMove(BasicReversi board, int player) {
    List<List<Integer>> corners = CornerAI.getCorners(board);
    List<List<Integer>> adjacentCornerCells = new ArrayList<>();
    for (int i = 0; i < corners.size(); i++) {
      adjacentCornerCells.add(new ArrayList<>(Arrays.asList(corners.get(i).get(0) + 1, corners.get(i).get(1))));
      adjacentCornerCells.add(new ArrayList<>(Arrays.asList(corners.get(i).get(0), corners.get(i).get(1) + 1)));
      adjacentCornerCells.add(new ArrayList<>(Arrays.asList(corners.get(i).get(0) + 1, corners.get(i).get(1) + 1)));

    }
    System.out.println(adjacentCornerCells);
    ArrayList<ArrayList<Integer>> possibleMoves = new ArrayList<>();
    // Find all possible moves that are not adjacent to the border
    removeAllAdjacentMoves(board, adjacentCornerCells, possibleMoves);
    if (possibleMoves.isEmpty()) {
      return Optional.empty();
    }
    // return the move that flips the most pieces
    int maxFlips = Integer.MIN_VALUE;
    List<Integer> bestMove = new ArrayList<>();
    bestMove = getMoveWithMostFlips(board, possibleMoves, maxFlips, bestMove);
    return Optional.of(bestMove);
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
            if (!adjacentBorderCell.contains(move)) {
              possibleMoves.add(move);
            }
          }
        } catch (IllegalStateException e) {
          continue;
        }
      }
    }
  }
}
