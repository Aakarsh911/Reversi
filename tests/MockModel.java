import java.util.List;

import cs3500.reversi.model.Hex;
import cs3500.reversi.model.ModelListener;
import cs3500.reversi.model.ReversiModel;

/**
 * Represents a mock model for testing.
 */
public class MockModel implements ReversiModel {
  public StringBuilder log = new StringBuilder();

  @Override
  public void move(int row, int col) {
    log.append("move(").append(row).append(", ").append(col).append(") was called");
  }

  @Override
  public void pass() {
    log.append("pass() was called");
  }

  @Override
  public void addListener(ModelListener p) {
    // do nothing, need to implement for interface

  }

  @Override
  public void startGame() {
    // do nothing, need to implement for interface

  }

  @Override
  public List<List<Hex>> getBoard() {
    return null;
  }

  @Override
  public String getColor(Hex h) {
    return null;
  }

  @Override
  public String getTurn() {
    return "White";
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int getWhiteScore() {
    return 0;
  }

  @Override
  public int getBlackScore() {
    return 0;
  }

  @Override
  public int getNumRows() {
    return 0;
  }

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
    return null;
  }
}
