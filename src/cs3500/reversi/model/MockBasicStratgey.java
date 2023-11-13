package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a mock strategy that returns a list of cells to flip that is the size of the sum of
 * the row and column.
 */
public class MockBasicStratgey extends BasicReversi{
  @Override
  List<Hex> getCellsToFlip(int row, int col) {
    List<Hex> list = new ArrayList<>();
    for (int i = 0; i < row+col; i++) {
      list.add(new ReversiCell(0,0,0));
    }
    return list;
  }
}
