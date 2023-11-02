import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.Hex;
import cs3500.reversi.view.ReversiTextualView;

/**
 * Tests for the Reversi game.
 */
public class ReversiModelTests {
  private BasicReversi m;

  @Test
  public void testGameOver() {
    BasicReversi m2 = new BasicReversi(11);
    m2.move(4, 3);
    m2.move(3, 4);
    m2.pass();
    m2.move(4, 2);
    m2.pass();
    m2.move(6, 3);
    m2.pass();
    m2.move(6, 6);
    Assert.assertTrue(m2.isGameOver());
  }

  @Test
  public void testNoLegalMoveLeft() {
    BasicReversi m2 = new BasicReversi(5);
    ReversiTextualView view = new ReversiTextualView(m2);
    m2.pass();
    m2.move(1, 0);
    m2.pass();
    m2.move(4, 1);
    m2.move(3, 3);
    m2.move(1, 3);
    m2.pass();
    Assert.assertTrue(m2.isGameOver());
  }

  @Test
  public void testMultipleDirections() {
    BasicReversi m2 = new BasicReversi(7);
    m2.move(2, 4);
    m2.move(2, 5);
    m2.move(1, 2);
    Assert.assertEquals(6, m2.getWhiteScore());
    Assert.assertEquals(3, m2.getBlackScore());
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

  @Test
  public void testBasicAndSmartAi() {
    m = new BasicReversi(11);
    ReversiTextualView view = new ReversiTextualView(m);
    m.move(m.bestMoveWithMostFlips().get(0), m.bestMoveWithMostFlips().get(1));
    m.move(7, 4);
    m.move(6, 6);
    m.move(4, 3);
    m.move(6, 3);
    m.move(4, 6);
    m.move(3, 2);
    m.move(2, 1);
    System.out.println(m.bestMoveWithMostFlips().get(0) + " " + m.bestMoveWithMostFlips().get(1));
    System.out.println(m.maxMinSmartAi().get(0) + " " + m.maxMinSmartAi().get(1));
    System.out.println(view);
  }

  @Test
  public void testBasicAndSmartAi2() {
    m = new BasicReversi(15);
    ReversiTextualView view = new ReversiTextualView(m);
    m.move(m.bestMoveWithMostFlips().get(0), m.bestMoveWithMostFlips().get(1));
    m.move(4, 5);
    m.move(4, 6);
    System.out.println(m.bestMoveWithMostFlips().get(0) + " " + m.bestMoveWithMostFlips().get(1));
    System.out.println(m.maxMinSmartAi().get(0) + " " + m.maxMinSmartAi().get(1));
    System.out.println(view);
  }
}
