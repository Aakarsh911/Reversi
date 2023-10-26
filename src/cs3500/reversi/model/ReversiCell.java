package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReversiCell implements Hex{
  private final int q;
  private final int r;
  private final int s;


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
      return this.q==c.q   && this.r ==c.r   && this.s == c.s;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.q * 10 + this.r * 100 + this.s * 1000;
  }

  public List<Hex> neighbors() {
    return Arrays.asList(new ReversiCell(this.q, this.r + 1, this.s- 1),
            new ReversiCell(this.q, this.r   - 1 , this.s + 1),
            new ReversiCell(this.q   + 1 , this.r, this.s - 1) ,
            new ReversiCell(this.q   - 1 , this.r   , this.s + 1) ,
            new ReversiCell(this.q   + 1 , this.r   - 1 , this.s) ,
            new ReversiCell(this.q   - 1 , this.r   + 1 , this.s));
  }

  public Hex getDirection(Hex c){
    return new ReversiCell(c.getQ() - this.q, c.getR() - this.r, c.getS() - this.s);
  }

  public int calcDistance(Hex c, int numRows) {
    int N = (numRows - 1) / 2;
    Hex direction = this.getDirection(c);
    System.out.println("direction: " + direction.getR());
    if (direction.getQ() == 0) {
      if(direction.getR() < 0) {
        return N - r;
      }else{
        System.out.println("N: " + N + " r: " + q);
        return N - q;
      }
    } else if (direction.getR() == 0) {
      if(direction.getQ() > 0){
        return N - q;
      }else{
        return N - s;
      }
    } else {
      if (direction.getR() > 0){
        return Math.abs(-N - q);
      }else{
        return Math.abs(-N - r);
      }
    }
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

  public List<Hex> cellsInDirection(Hex c, Map<Hex, CellState> cellStates) {
    List<Hex> cells = new ArrayList<>();
    int soFar = 1;
    while(cellStates.containsKey(new ReversiCell(this.q + c.getQ() * soFar, this.r + c.getR() * soFar, this.s + c.getS() * soFar))){
      cells.add(new ReversiCell(this.q + c.getQ() * soFar, this.r + c.getR() * soFar, this.s + c.getS() * soFar));
      soFar++;
    }
    return cells;
  }

}
