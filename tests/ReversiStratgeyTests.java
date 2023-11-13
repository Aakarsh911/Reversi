import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import cs3500.reversi.model.AvoidAdjacentCornerCellsAI;
import cs3500.reversi.model.BasicAI;
import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.CornerAI;
import cs3500.reversi.model.MockBasicStratgey;
import cs3500.reversi.model.MockStrategy3;
import cs3500.reversi.model.ReversiStrategy;

public class ReversiStratgeyTests {
  @Test
  public void testBasicStrategy() {
    ReversiStrategy ai = new BasicAI();
    BasicReversi model = new MockBasicStratgey();
    Assert.assertEquals(List.of(3, 6), ai.chooseMove(model, 1).get());
  }

  @Test
  public void testCornerAI() {
    ReversiStrategy ai = new CornerAI();
    MockStrategy3 model = new MockStrategy3();
    ai.chooseMove(model, 1);
    List<List<Integer>> list = List.of(List.of(0, 0), List.of(0, 3), List.of(3, 0), List.of(3, 6), List.of(6, 0), List.of(6, 3));
    Assert.assertEquals(list, model.getIterated());
  }

  @Test
  public void test() {
    BasicReversi model = new BasicReversi(11);
    AvoidAdjacentCornerCellsAI ai2 = new AvoidAdjacentCornerCellsAI();
    ai2.chooseMove(model, 1);
  }
}
