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
    if (direction.getQ() == 0) {
      if(direction.getQ() < 0) {
        return N - c.getR();
      }else{
        return N - c.getQ();
      }
    } else if (direction.getR() == 0) {
      if(direction.getQ() > 0){
        return N - c.getQ();
      }else{
        return N - c.getS() + 1;
      }
    } else {
      if (direction.getR() > 0){
        return Math.abs(-N - c.getQ());
      }else{
        return Math.abs(-N - c.getR());
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

  public List<Hex> cellsInDirection(Hex c, int distance){
    List<Hex> cells = new ArrayList<>();
    for (int i = 1; i <= distance; i++) {
      cells.add(new ReversiCell(this.q + c.getQ() * i, this.r + c.getR() * i, this.s + c.getS() * i));
    }
    return cells;
  }

}
