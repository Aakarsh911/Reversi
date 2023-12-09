import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import cs3500.reversi.model.BasicAI;
import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.CornerAI;
import cs3500.reversi.model.MiniMaxAI;
import cs3500.reversi.model.Person;
import cs3500.reversi.model.ReversiStrategy;
import cs3500.reversi.model.SquareReversi;
import cs3500.reversi.model.TryTwo;

/**
 * Tests for the Strategies on SquareReversi interface.
 */
public class SquareStrategyTests {
  @Test
  public void testMiniMaxAI() {
    BasicReversi model = new SquareReversi(8);
    ReversiStrategy ai = new MiniMaxAI();
    List<Integer> move = ai.chooseMove(model, new Person("white")).get();
    Assert.assertEquals(List.of(2, 3), move);
    model.move(move.get(0), move.get(1));
    move = ai.chooseMove(model, new Person("white")).get();
    Assert.assertEquals(List.of(2, 2), move);
  }

  @Test
  public void testTryTwoTriesTheNextCorrectly() {
    BasicReversi m = new SquareReversi(10);
    ReversiStrategy ai1 = new TryTwo(new CornerAI(), new MiniMaxAI());
    ReversiStrategy ai2 = new MiniMaxAI();
    m.move(5, 6);
    m.move(6, 4);
    List<Integer> move1 = ai1.chooseMove(m, new Person("white")).get();
    List<Integer> move2 = ai2.chooseMove(m, new Person("white")).get();
    Assert.assertEquals(List.of(3, 3), move1);
    Assert.assertEquals(List.of(3, 3), move2);
    Assert.assertEquals(move1, move2);
  }

  @Test
  public void testBasicStrategy() {
    BasicReversi model = new SquareReversi(8);
    ReversiStrategy ai = new BasicAI();
    List<Integer> move = ai.chooseMove(model, new Person("white")).get();
    Assert.assertEquals(List.of(2, 3), move);
  }
}
