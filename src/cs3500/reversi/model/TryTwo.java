package cs3500.reversi.model;

import java.util.List;

public class TryTwo implements ReversiStrategy {
  ReversiStrategy first;
  ReversiStrategy second;
  public TryTwo(ReversiStrategy first, ReversiStrategy second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public List<Integer> chooseMove(BasicReversi board, int player) throws IllegalStateException {
    try {
      return first.chooseMove(board, player);
    } catch (IllegalStateException e) {
      return second.chooseMove(board, player);
    }
  }
}
