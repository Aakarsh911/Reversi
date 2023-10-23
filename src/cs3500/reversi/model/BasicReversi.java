package cs3500.reversi.model;

import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class BasicReversi implements ReversiModel {

  private ArrayList<ArrayList<ReversiCell>> board;
  private final int numRows;

  public BasicReversi(int numRows) {
    if (numRows % 2 == 0 || numRows < 3) {
      throw new IllegalArgumentException("numRows must be odd and less than 3");
    }
    this.numRows = numRows;
    initCells(numRows);
  }



  public void initCells(int numRows) {
    ArrayList<ArrayList<ReversiCell>> board = new ArrayList<ArrayList<ReversiCell>>();
    for (int i = 0; i < numRows; i++) {
       ArrayList<ReversiCell> row = new ArrayList<>();
       board.add(row);
    }
    int N = (numRows - 1) / 2;
    for (int q = -N; q <= N; q++) {
      int r1 = max(-N, -q - N);
      int r2 = min( N, -q + N);
      for (int r = r1; r <= r2; r++) {
        int s = -q - r;
        ReversiCell h = new ReversiCell(r, q, s);
        board.get(q + N).add(h);
      }
    }
  }

  public void startGame() {
  }

  public void move(int x, int y) {
  }

  public boolean isGameOver() {
    return false;
  }

  public ArrayList<ArrayList<ReversiCell>> getBoard() {
    return board;
  }

  private void addHex(ReversiCell h){

  }
}
