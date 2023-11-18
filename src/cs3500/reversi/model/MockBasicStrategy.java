package cs3500.reversi.model;

import java.util.List;

/**
 * Represents a mock strategy that returns a list of cells to flip that is the size of the sum of
 * the row and column.
 */
public class MockBasicStrategy implements ReversiModel {
  private int fakeScore = 0;

  @Override
  public boolean isLegalMove(int row, int col) {
    return true;
  }

  @Override
  public boolean anyLegalMovesForCurrentPlayer() {
    return true;
  }

  @Override
  public ReversiModel copy() {
    return new MockBasicStrategy();
  }

  @Override
  public void move(int row, int col) {
    this.fakeScore = row + col;
  }

  @Override
  public void pass() {
    // do nothing, need to implement for interface
  }

  @Override
  public void addPlayer(Player p) {

  }

  @Override
  public void startGame() {

  }

  @Override
  public List<List<Hex>> getBoard() {
    return List.of(List.of(new ReversiCell(0, 0, 0), new ReversiCell(0, 0, 0)),
            List.of(new ReversiCell(0, 0, 0), new ReversiCell(0, 0, 0)),
            List.of(new ReversiCell(0, 0, 0)));
  }

  @Override
  public String getColor(Hex h) {
    return null;
  }

  @Override
  public String getTurn() {
    return null;
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int getWhiteScore() {
    return fakeScore;
  }

  @Override
  public int getBlackScore() {
    return fakeScore;
  }

  @Override
  public int getNumRows() {
    return 0;
  }
}