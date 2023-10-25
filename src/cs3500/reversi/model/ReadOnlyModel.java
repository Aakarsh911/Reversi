package cs3500.reversi.model;

import java.util.List;

public interface ReadOnlyModel {
  public List<List<Hex>> getBoard();
  public CellState getColor(Hex h);
  public String getTurn();
}
