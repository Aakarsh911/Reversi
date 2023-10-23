package cs3500.reversi.model;

import java.util.ArrayList;

public interface ReversiModel {
  public void startGame();
  public void move(int x, int y);
  public boolean isGameOver();
  public ArrayList<ArrayList<ReversiCell>> getBoard();
}
