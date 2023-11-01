package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Represents a hexagonal cell in the game of Reversi.
 */
public class ReversiCell implements Hex {
  private final int q; // q coordinate from the cube coordinate system
  private final int r; // r coordinate from the cube coordinate system
  private final int s; // s coordinate from the cube coordinate system

  /**
   * Constructs a ReversiCell.
   * @param q the q coordinate
   * @param r the r coordinate
   * @param s the s coordinate
   */
  public ReversiCell(int q, int r, int s) {
    this.q = q;
    this.r = r;
    this.s = s;
  }

  public String toString() {
    return "ReversiCell(" + this.q + ", " + this.r + ", " + this.s + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof ReversiCell) {
      ReversiCell c = (ReversiCell) o;
      return this.q == c.q   && this.r == c.r   && this.s == c.s;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.q * 10 + this.r * 100 + this.s * 1000;
  }

  @Override
  public List<Hex> neighbors() {
    return Arrays.asList(new ReversiCell(this.q, this.r + 1, this.s - 1),
            new ReversiCell(this.q, this.r   - 1 , this.s + 1),
            new ReversiCell(this.q   + 1 , this.r, this.s - 1) ,
            new ReversiCell(this.q   - 1 , this.r   , this.s + 1) ,
            new ReversiCell(this.q   + 1 , this.r   - 1 , this.s) ,
            new ReversiCell(this.q   - 1 , this.r   + 1 , this.s));
  }

  @Override
  public Hex getDirection(Hex hex) {
    return new ReversiCell(hex.getQ() - this.q, hex.getR() - this.r, hex.getS() - this.s);
  }

  @Override
  public int getQ() {
    return this.q;
  }

  @Override
  public int getR() {
    return this.r;
  }

  @Override
  public int getS() {
    return this.s;
  }

  @Override
  public List<Hex> cellsInDirection(Hex hex, Map<Hex, CellState> cellStates) {
    List<Hex> cells = new ArrayList<>();
    int counter = 1; // counter to keep track of how many cells have been added
    while (cellStates.containsKey(new ReversiCell(this.q + hex.getQ()
            * counter, this.r + hex.getR() * counter, this.s + hex.getS() * counter))) {
      cells.add(new ReversiCell(this.q + hex.getQ() * counter,
              this.r + hex.getR() * counter, this.s + hex.getS() * counter));
      counter++;
    }
    return cells;
  }
}
