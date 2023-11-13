package cs3500.reversi.model;

import java.util.List;
import java.util.Optional;

public class TryTwo implements ReversiStrategy {
  ReversiStrategy first;
  ReversiStrategy second;
  public TryTwo(ReversiStrategy first, ReversiStrategy second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public Optional<List<Integer>> chooseMove(BasicReversi board, int player) {
    if (first.chooseMove(board, player).isPresent()) {
      return first.chooseMove(board, player);
    }
    return second.chooseMove(board, player);
  }
}
