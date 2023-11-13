package cs3500.reversi.model;

import java.util.List;
import java.util.Optional;

public interface ReversiStrategy {
  Optional<List<Integer>> chooseMove(BasicReversi board, int player);
}

