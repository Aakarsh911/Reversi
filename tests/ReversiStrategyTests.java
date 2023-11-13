import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.reversi.model.AvoidAdjacentCornerCellsAI;
import cs3500.reversi.model.BasicAI;
import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.CornerAI;
import cs3500.reversi.model.MockBasicStrategy;
import cs3500.reversi.model.MockStrategyTranscript;
import cs3500.reversi.model.Person;
import cs3500.reversi.model.ReversiStrategy;

public class ReversiStrategyTests {
  @Test
  public void testBasicStrategy() {
    ReversiStrategy ai = new BasicAI();
    BasicReversi model = new MockBasicStrategy();
    Assert.assertEquals(List.of(3, 6), ai.chooseMove(model, new Person()).get());
  }

  @Test
  public void testCornerAI() {
    ReversiStrategy ai = new CornerAI();
    MockStrategyTranscript model = new MockStrategyTranscript();
    ai.chooseMove(model, new Person());
    List<List<Integer>> list = List.of(List.of(0, 0), List.of(0, 3), List.of(3, 0), List.of(3, 6), List.of(6, 0), List.of(6, 3));
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
}
