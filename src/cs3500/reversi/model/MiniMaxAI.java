package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiniMaxAI implements ReversiStrategy {
  BasicAI basicAI;
  @Override
  public List<Integer> chooseMove(BasicReversi model, int player) {
    List<List<Hex>> board = model.getBoard();
    ArrayList<ArrayList<Integer>> possibleMoves = new ArrayList<>();
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
    BasicReversi copy = new BasicReversi(model.getNumRows(),1);
    copy.clearBoard();
    copy.clearCellStates();
    copy.initCells(model.getNumRows());
    copy.initColors();

    // make the move on the copy and calculate all the next possible moves and make a mao for each move
    Map<ArrayList<Integer>, ArrayList<ArrayList<Integer>>> possibleMovesMap = new HashMap<>();
    for (ArrayList<Integer> move : possibleMoves) {
      try {
        copy.move(move.get(0), move.get(1));
      } catch (Exception e) {
        continue;
      }
      ArrayList<ArrayList<Integer>> nextPossibleMoves = new ArrayList<>();
      for (int rowNum = 0; rowNum < copy.getBoard().size(); rowNum++) {
        for (int colNum = 0; colNum < copy.getBoard().get(rowNum).size(); colNum++) {
          try {
            if (!copy.getCellsToFlip(rowNum, colNum).isEmpty()) {
              ArrayList<Integer> nextMove = new ArrayList<>();
              nextMove.add(rowNum);
              nextMove.add(colNum);
              nextPossibleMoves.add(nextMove);
            }
          } catch (IllegalStateException e) {
            continue;
          }
        }
      }
      possibleMovesMap.put(move, nextPossibleMoves);
      copy.clearBoard();
      copy.clearCellStates();
      copy.initCells(model.getNumRows());
      copy.initColors();
    }
    // find the move that whose maximum number of flips of the vlaue in the mao is the minimum out of all values
    int min = Integer.MAX_VALUE;
    for (ArrayList<Integer> move : possibleMovesMap.keySet()) {
      int max = Integer.MIN_VALUE;
      for (ArrayList<Integer> nextMove : possibleMovesMap.get(move)) {
        try {
          if (model.getCellsToFlip(nextMove.get(0), nextMove.get(1)).size() > max) {
            max = model.getCellsToFlip(nextMove.get(0), nextMove.get(1)).size();
          }
        } catch (IllegalStateException e) {
          continue;
        }
      }
      if (max < min) {
        min = max;
      }
    }

    // find the move that has the minimum number of flips
    List<Integer> bestMove = new ArrayList<>();
    for (ArrayList<Integer> move : possibleMovesMap.keySet()) {
      int max = Integer.MIN_VALUE;
      for (ArrayList<Integer> nextMove : possibleMovesMap.get(move)) {
        try {
          if (model.getCellsToFlip(nextMove.get(0), nextMove.get(1)).size() > max) {
            max = model.getCellsToFlip(nextMove.get(0), nextMove.get(1)).size();
          }
        } catch (IllegalStateException e) {
          continue;
        }
      }
      if (max == min) {
        bestMove = move;
        break;
      }
    }
    if (bestMove.isEmpty()) {
      bestMove = basicAI.chooseMove(model, player);
    }
    return bestMove;
  }
}
