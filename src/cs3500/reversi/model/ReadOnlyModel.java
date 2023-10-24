package cs3500.reversi.model;

import java.util.List;

public interface ReadOnlyModel {
  public List<List<ReversiCell>> getBoard();
  public CellState getColor(Hex h);
}
