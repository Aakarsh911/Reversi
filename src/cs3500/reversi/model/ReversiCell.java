package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReversiCell implements Hex{
  public final int q;
  public final int r;
  public final int s;


  public ReversiCell(int q, int r, int s) {
    this.q = q;
    this.r = r;
    this.s = s;
  }
  public String toString() {
    return "ReversiCell(" + this.q + ", " + this.r + ", " + this.s + ")";
  }

  public boolean equals(Object o) {
    if (o instanceof ReversiCell) {
      ReversiCell c = (ReversiCell) o;
      return this.q==c.q   && this.r ==c.r   && this.s == c.s;
    }
    return false;
  }

  public int hashCode() {
    return this.q * 10 + this.r * 100 + this.s * 1000;
  }

  public List<ReversiCell> neighbors() {
    return Arrays.asList(new ReversiCell(this.q, this.r + 1, this.s- 1),
            new ReversiCell(this.q, this.r   - 1 , this.s + 1),
            new ReversiCell(this.q   + 1 , this.r, this.s - 1) ,
            new ReversiCell(this.q   - 1 , this.r   , this.s + 1) ,
            new ReversiCell(this.q   + 1 , this.r   - 1 , this.s) ,
            new ReversiCell(this.q   - 1 , this.r   + 1 , this.s));
  }

  public ReversiCell getDirection(ReversiCell c){
    return new ReversiCell(c.q - this.q, c.r - this.r, c.s - this.s);
  }

  public int calcDistance(ReversiCell c, int numRows) {
    int N = (numRows - 1) / 2;
    ReversiCell direction = this.getDirection(c);
    if (direction.q == 0) {
      if(direction.r < 0) {
        return N - c.r;
      }else{
        return N - c.s;
      }
    } else if (direction.r == 0) {
      if(direction.q > 0){
        return N - c.q;
      }else{
        return N - c.s;
      }
    } else {
      if (direction.r > 0){
        return Math.abs(-N - c.q);
      }else{
        return Math.abs(-N - c.r);
      }
    }
  }
  public List<ReversiCell> cellsInDirection(ReversiCell c, int distance){
    List<ReversiCell> cells = new ArrayList<>();
    for (int i = 1; i <= distance; i++) {
      cells.add(new ReversiCell(this.q + c.q * i, this.r + c.r * i, this.s + c.s * i));
    }
    return cells;
  }
}
