import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import cs3500.reversi.model.AvoidAdjacentCornerCellsAI;
import cs3500.reversi.model.BasicAI;
import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.CornerAI;
import cs3500.reversi.model.MiniMaxAI;
import cs3500.reversi.model.MockBasicStrategy;
import cs3500.reversi.model.MockStrategyTranscript;
import cs3500.reversi.model.Person;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.model.ReversiStrategy;
import cs3500.reversi.model.TryTwo;

/**
 * Tests for the ReversiStrategy interface.
 */
public class ReversiStrategyTests {
  @Test
  public void testBasicStrategyWithMockModelAndResolveTies() {
    ReversiStrategy ai = new BasicAI();
    ReversiModel model = new MockBasicStrategy();
    // this test shows that the BasicAI resolves ties correctly as (2, 0) would also flip the
    // same number of cells as (1, 1) but because (1, 1) is the first move that flips the most
    // cells, it is chosen
    Assert.assertEquals(List.of(1, 1), ai.chooseMove(model, new Person("white")).get());
  }

  @Test
  public void testCornerAI() {
    ReversiStrategy ai = new CornerAI();
    MockStrategyTranscript model = new MockStrategyTranscript();
    ai.chooseMove(model, new Person("white"));
    List<List<Integer>> list = List.of(List.of(0, 0),
            List.of(0, 3), List.of(3, 0), List.of(3, 6), List.of(6, 0), List.of(6, 3));
    Assert.assertEquals(list, model.getIterated());
  }

  @Test
  public void testAvoidAdjacentCornerCellsAI() {
    MockStrategyTranscript model = new MockStrategyTranscript();
    AvoidAdjacentCornerCellsAI ai = new AvoidAdjacentCornerCellsAI();
    List<Integer> move = ai.chooseMove(model, new Person("white")).get();
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
    List<Integer> move = ai.chooseMove(model, new Person("white")).get();
    Assert.assertEquals(List.of(1, 2), move);
    model.move(move.get(0), move.get(1));
    move = ai.chooseMove(model, new Person("white")).get();
    Assert.assertEquals(List.of(0, 1), move);
  }

  @Test
  public void testDifferenceBetweenAIs() {
    BasicReversi m = new BasicReversi(11);
    ReversiStrategy ai1 = new MiniMaxAI();
    ReversiStrategy ai2 = new BasicAI();
    m.move(3, 4);
    m.move(2, 3);
    List<Integer> move1 = ai1.chooseMove(m, new Person("white")).get();
    List<Integer> move2 = ai2.chooseMove(m, new Person("white")).get();
    Assert.assertEquals(List.of(2, 4), move1);
    Assert.assertEquals(List.of(4, 6), move2);
    Assert.assertNotEquals(move1, move2);
  }

  @Test
  public void testTryTwoTriesTheNextCorrectly() {
    BasicReversi m = new BasicReversi(11);
    ReversiStrategy ai1 = new TryTwo(new CornerAI(), new MiniMaxAI());
    ReversiStrategy ai2 = new MiniMaxAI();
    m.move(3, 4);
    m.move(2, 3);
    List<Integer> move1 = ai1.chooseMove(m, new Person("white")).get();
    List<Integer> move2 = ai2.chooseMove(m, new Person("white")).get();
    Assert.assertEquals(List.of(2, 4), move1);
    Assert.assertEquals(List.of(2, 4), move2);
    Assert.assertEquals(move1, move2);
  }
}