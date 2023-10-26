package cs3500.reversi.model;

import java.util.List;
import java.util.Map;

public interface ReadOnlyModel {
  public List<List<Hex>> getBoard();
  public CellState getColor(Hex h);
  public String getTurn();
  public boolean isGameOver();

  public Map<Hex, CellState> getBoardState();
}
