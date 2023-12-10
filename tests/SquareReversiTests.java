import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.BoardPiece;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.model.SquareReversi;

/**
 * Tests for the SquareReversi game.
 */
public class SquareReversiTests {
  ReversiModel m = new SquareReversi(8);

  @Test
  public void testGameOver() {
    m.pass();
    m.pass();
    Assert.assertTrue(m.isGameOver());
  }

  @Test
  public void testConstructor() {
    Assert.assertThrows(IllegalArgumentException.class, () -> new SquareReversi(5));
    Assert.assertThrows(IllegalArgumentException.class, () -> new SquareReversi(-7));
    Assert.assertThrows(IllegalArgumentException.class, () -> new SquareReversi(-2));
  }

  @Test
  public void testScore() {
    Assert.assertEquals(2, m.getBlackScore());
    Assert.assertEquals(2, m.getWhiteScore());
    m.move(5, 4);
    Assert.assertEquals(1, m.getBlackScore());
    Assert.assertEquals(4, m.getWhiteScore());
  }



  @Test
  public void testPassAndGetTurn() {
    Assert.assertEquals("White", m.getTurn());
    m.pass();
    Assert.assertEquals("Black", m.getTurn());
    m.pass();
    Assert.assertEquals("White", m.getTurn());
  }

  @Test
  public void testGetBoard() {
    List<List<BoardPiece>> board = m.getBoard();
    Assert.assertEquals(8, board.size());
    BasicReversi m2 = new SquareReversi(10);
    List<List<BoardPiece>> board2 = m2.getBoard();
    Assert.assertEquals(10, board2.size());
  }

  @Test
  public void testGetColor() {
    Assert.assertEquals("EMPTY", m.getColor(m.getBoard().get(1).get(2)).toString());
    m.move(5, 4);
    Assert.assertEquals("WHITE", m.getColor(m.getBoard().get(4).get(3)).toString());
    m.move(3, 5);
    Assert.assertEquals("BLACK", m.getColor(m.getBoard().get(3).get(4)).toString());
  }

  @Test
  public void testGetNumRows() {
    Assert.assertEquals(8, m.getNumRows());
  }

  @Test
  public void testIsLegalMove() {
    Assert.assertTrue(m.isLegalMove(5, 4));
    Assert.assertFalse(m.isLegalMove(4, 4));
    Assert.assertFalse(m.isLegalMove(0, 0));
    Assert.assertFalse(m.isLegalMove(-2, 7));
    Assert.assertFalse(m.isLegalMove(-2, -4));
    Assert.assertFalse(m.isLegalMove(2, 27));
  }

  @Test
  public void testAnyLegalMovesForCurrentPlayer() {
    m.pass();
    Assert.assertTrue(m.anyLegalMovesForCurrentPlayer());
    m.pass();
    Assert.assertTrue(m.anyLegalMovesForCurrentPlayer());
  }
}
