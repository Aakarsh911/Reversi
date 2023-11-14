import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cs3500.reversi.model.AvoidAdjacentCornerCellsAI;
import cs3500.reversi.model.BasicAI;
import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.CornerAI;
import cs3500.reversi.model.MiniMaxAI;
import cs3500.reversi.model.MockBasicStrategy;
import cs3500.reversi.model.MockStrategyTranscript;
import cs3500.reversi.model.Person;
import cs3500.reversi.model.ReversiStrategy;

/**
 * Tests for the ReversiStrategy interface.
 */
public class ReversiStrategyTests {
  @Test
  public void testBasicStrategyWithMockModelAndResolveTies() {
    ReversiStrategy ai = new BasicAI();
    BasicReversi model = new MockBasicStrategy();
    // this test shows that the BasicAI resolves ties correctly as (6, 3) would also flip the
    // same number of cells as (3, 6) but because (3, 6) is the first move that flips the most
    // cells, it is chosen
    Assert.assertEquals(List.of(3, 6), ai.chooseMove(model, new Person()).get());
  }

  @Test
  public void testCornerAI() {
    ReversiStrategy ai = new CornerAI();
    MockStrategyTranscript model = new MockStrategyTranscript();
    ai.chooseMove(model, new Person());
    List<List<Integer>> list = List.of(List.of(0, 0),
            List.of(0, 3), List.of(3, 0), List.of(3, 6), List.of(6, 0), List.of(6, 3));
    Assert.assertEquals(list, model.getIterated());
  }

  @Test
  public void testAvoidAdjacentCornerCellsAI() {
    MockStrategyTranscript model = new MockStrategyTranscript();
    AvoidAdjacentCornerCellsAI ai = new AvoidAdjacentCornerCellsAI();
    List<Integer> move = ai.chooseMove(model, new Person()).get();
    List<List<Integer>> adjacent = List.of(List.of(0, 1), List.of(1, 0), List.of(1, 1),
            List.of(0, 2), List.of(1, 2), List.of(1, 3), List.of(2,0), List.of(3, 1),
            List.of(4,0), List.of(3,5), List.of(2,6), List.of(4,5), List.of(6,1),
            List.of(6,2),List.of(5,0), List.of(5,1), List.of(5,4), List.of(5,3));
    List<List<Integer>> iterated = model.getIterated();
    Assert.assertFalse(adjacent.contains(move));
    Assert.assertTrue(iterated.contains(move));
    for (List<Integer> l : adjacent) {
      Assert.assertFalse(iterated.contains(l));
    }
  }

  @Test
  public void testMiniMaxAI() {
    BasicReversi model = new BasicReversi();
    ReversiStrategy ai = new MiniMaxAI();
    List<Integer> move = ai.chooseMove(model, new Person()).get();
    Assert.assertEquals(List.of(1, 2), move);
    model.move(move.get(0), move.get(1));
    move = ai.chooseMove(model, new Person()).get();
    Assert.assertEquals(List.of(0, 1), move);
  }

  @Test
  public void testStrategyPass() {
    BasicReversi m = new BasicReversi(5,2);
    ReversiStrategy ai = new MiniMaxAI();
    m.pass();
    m.move(1, 0);
    m.pass();
    m.move(4, 1);
    m.move(3, 3);
    m.move(1, 3);
    m.pass();
    String current = m.getTurn();
    Assert.assertEquals("Black", current);
    ai.chooseMove(m, new Person());
    Assert.assertEquals("White", m.getTurn());
  }

  @Test
  public void testDifferenceBetweenAIs() {
    BasicReversi m = new BasicReversi(11);
    ReversiStrategy ai1 = new MiniMaxAI();
    ReversiStrategy ai2 = new BasicAI();
    m.move(3, 4);
    m.move(2, 3);
    List<Integer> move1 = ai1.chooseMove(m, new Person()).get();
    List<Integer> move2 = ai2.chooseMove(m, new Person()).get();
    Assert.assertEquals(List.of(2, 4), move1);
    Assert.assertEquals(List.of(4, 6), move2);
    Assert.assertNotEquals(move1, move2);
  }
}