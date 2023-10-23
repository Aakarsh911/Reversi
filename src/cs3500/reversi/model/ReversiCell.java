package cs3500.reversi.model;

public class ReversiCell {
  public int q;
  public int r;
  public int s;
  public ReversiCell(int q, int r, int s) {
    this.q = q;
    this.r = r;
    this.s = s;
  }
  public String toString() {
    return "ReversiCell(" + this.q + ", " + this.r + ", " + this.s + ")";
  }
}
