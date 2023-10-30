import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.Hex;
import cs3500.reversi.model.ReversiCell;
import cs3500.reversi.view.ReversiTextualView;
import cs3500.reversi.view.ReversiView;

/**
 * Tests for the Reversi game.
 */
public class ReversiTests {
  BasicReversi m;
  Hex src;
  Hex rDirPos;
  Hex rDirNeg;
  Hex qDirPos;
  Hex qDirNeg;
  Hex sDirPos;
  Hex sDirNeg;

  private void initCells(){
    m = new BasicReversi(11);
    m.startGame();
    List<List<Hex>> board = m.getBoard();
    src = board.get(2).get(2);
    rDirPos = board.get(1).get(2);
    rDirNeg = board.get(1).get(0);
    qDirPos = board.get(0).get(0);
    qDirNeg = board.get(2).get(2);
    sDirPos = board.get(0).get(1);
    sDirNeg = board.get(2).get(1);
  }

  @Test
  public void testGetDirection() {
    initCells();
    Assert.assertEquals(new ReversiCell(1, 0, -1), src.getDirection(rDirPos));
    Assert.assertEquals(new ReversiCell(-1, 0, 1), src.getDirection(rDirNeg));
    Assert.assertEquals(new ReversiCell(0, -1, 1), src.getDirection(qDirPos));
    Assert.assertEquals(new ReversiCell(0, 1, -1), src.getDirection(qDirNeg));
    Assert.assertEquals(new ReversiCell(1, -1, 0), src.getDirection(sDirPos));
    Assert.assertEquals(new ReversiCell(-1, 1, 0), src.getDirection(sDirNeg));
  }



  @Test
  public void testGameOver() {
    initCells();
    BasicReversi m2 = new BasicReversi(11);
    m2.startGame();
    m2.move(4, 3);
    m2.move(3, 4);
    m2.pass();
    m2.move(4, 2);
    m2.pass();
    m2.move(6, 3);
    m2.pass();
    m2.move(6, 6);
    ReversiView view = new ReversiTextualView(m2);
    System.out.println(view.toString());
    m.move(6,3);
    m.move(7,2);
  }

  @Test
  public void testNoLegalMoveLeft() {
    initCells();
    BasicReversi m2 = new BasicReversi(5);
    m2.startGame();
    ReversiTextualView view = new ReversiTextualView(m2);
    m2.pass();
    m2.move(1, 0);
    m2.pass();
    m2.move(4, 1);
    m2.move(3, 3);
    m2.move(1, 3);
    m2.pass();
    System.out.println(view.toString());
  }

  @Test
  public void testMultipleDirections() {
    initCells();
    BasicReversi m2 = new BasicReversi(7);
    m2.startGame();
    m2.move(2, 4);
    m2.move(2, 5);
    m2.move(1, 2);
    ReversiTextualView view = new ReversiTextualView(m2);
    System.out.println(view.toString());
  }
}
