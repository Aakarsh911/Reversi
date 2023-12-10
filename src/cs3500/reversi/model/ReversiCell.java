package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Represents a hexagonal cell in the game of Reversi.
 */
public class ReversiCell implements BoardPiece {
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
  public List<BoardPiece> neighbors() {
    return Arrays.asList(new ReversiCell(this.q, this.r + 1, this.s - 1),
            new ReversiCell(this.q, this.r   - 1 , this.s + 1),
            new ReversiCell(this.q   + 1 , this.r, this.s - 1) ,
            new ReversiCell(this.q   - 1 , this.r   , this.s + 1) ,
            new ReversiCell(this.q   + 1 , this.r   - 1 , this.s) ,
            new ReversiCell(this.q   - 1 , this.r   + 1 , this.s));
  }

  @Override
  public BoardPiece getDirection(BoardPiece boardPiece) {
    return new ReversiCell(boardPiece.getQ() - this.q, boardPiece.getR() - this.r, boardPiece.getS() - this.s);
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
  public List<BoardPiece> cellsInDirection(BoardPiece boardPiece, Map<BoardPiece, CellState> cellStates) {
    List<BoardPiece> cells = new ArrayList<>();
    int counter = 1; // counter to keep track of how many cells have been added
    while (cellStates.containsKey(new ReversiCell(this.q + boardPiece.getQ()
            * counter, this.r + boardPiece.getR() * counter, this.s + boardPiece.getS() * counter))) {
      cells.add(new ReversiCell(this.q + boardPiece.getQ() * counter,
              this.r + boardPiece.getR() * counter, this.s + boardPiece.getS() * counter));
      counter++;
    }
    return cells;
  }
}
