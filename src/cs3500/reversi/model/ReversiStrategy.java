package cs3500.reversi.model;

import java.util.List;

public interface ReversiStrategy {
  List<Integer> chooseMove(BasicReversi board, int player);
}

