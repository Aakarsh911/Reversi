package cs3500.reversi.model;

import java.util.List;

public interface Hex {
  public String toString();
  public List<Hex> neighbors();
  public Hex getDirection(Hex c);
  public int calcDistance(Hex c, int numRows);
  public List<Hex> cellsInDirection(Hex c, int distance);
  public int getQ();
  public int getR();
  public int getS();
}
