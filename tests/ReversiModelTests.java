import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.Hex;
import cs3500.reversi.view.ReversiTextualView;
import cs3500.reversi.view.ReversiView;

/**
 * Tests for the Reversi game.
 */
public class ReversiModelTests {
  private BasicReversi m;
  private Hex src;
  private Hex rDirPos;
  private Hex rDirNeg;
  private Hex qDirPos;
  private Hex qDirNeg;
  private Hex sDirPos;
  private Hex sDirNeg;

  private void initCells(){
    m = new BasicReversi(11);
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
  public void testGameOver() {
    initCells();
    BasicReversi m2 = new BasicReversi(11);
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
    m2.move(2, 4);
    m2.move(2, 5);
    m2.move(1, 2);
    ReversiTextualView view = new ReversiTextualView(m2);
    System.out.println(view.toString());
  }

  @Test
  public void testConstructor() {
    Assert.assertThrows(IllegalArgumentException.class, () -> new BasicReversi(4));
    Assert.assertThrows(IllegalArgumentException.class, () -> new BasicReversi(-7));
    Assert.assertThrows(IllegalArgumentException.class, () -> new BasicReversi(-2));
  }

  @Test
  public void testScore() {
    m = new BasicReversi(11);
    Assert.assertEquals(3, m.getBlackScore());
    Assert.assertEquals(3, m.getWhiteScore());
    m.move(4, 3);
    Assert.assertEquals(2, m.getBlackScore());
    Assert.assertEquals(5, m.getWhiteScore());
  }

  @Test
  public void testGetCellsToFlip() {
    m = new BasicReversi(11);
    List<Hex> cellsToFlip = m.getCellsToFlip(3, 3);
    List<Hex> cellsToFlip2 = m.getCellsToFlip(4, 3);
    Assert.assertEquals(0, cellsToFlip.size());
    Assert.assertEquals(1, cellsToFlip2.size());
    m.move(4, 3);
    cellsToFlip2 = m.getCellsToFlip(5, 3);
    Assert.assertEquals(0, cellsToFlip2.size());
  }

  @Test
  public void testPassAndGetTurn() {
    m = new BasicReversi(11);
    Assert.assertEquals("White", m.getTurn());
    m.pass();
    Assert.assertEquals("Black", m.getTurn());
    m.pass();
    Assert.assertEquals("White", m.getTurn());
  }

  @Test
  public void testGetBoard() {
    m = new BasicReversi(11);
    List<List<Hex>> board = m.getBoard();
    Assert.assertEquals(11, board.size());
    BasicReversi m2 = new BasicReversi(5);
    List<List<Hex>> board2 = m2.getBoard();
    Assert.assertEquals(5, board2.size());
  }

  @Test
  public void testGetColor() {
    m = new BasicReversi(11);
    Assert.assertEquals("EMPTY", m.getColor(m.getBoard().get(1).get(2)).toString());
    m.move(4, 3);
    Assert.assertEquals("WHITE", m.getColor(m.getBoard().get(4).get(3)).toString());
    m.move(3, 4);
    Assert.assertEquals("BLACK", m.getColor(m.getBoard().get(3).get(4)).toString());
  }
}
