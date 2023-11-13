import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListResourceBundle;

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
    Assert.assertEquals(List.of(3,6), ai.chooseMove(model, 1).get());
  }

  @Test
  public void testCornerAI() {
    ReversiStrategy ai = new CornerAI();
    MockStrategy3 model = new MockStrategy3();
    ai.chooseMove(model, 1);
    List<List<Integer>> list = List.of([0, 0], [0, 1], [0, 2], [0, 3], [1, 0], [1, 1], [1, 2], [1, 3], [1, 4], [2, 0], [2, 1], [2, 2], [2, 3], [2, 4], [2, 5], [3, 0], [3, 1], [3, 2], [3, 3], [3, 4], [3, 5], [3, 6], [4, 0], [4, 1], [4, 2], [4, 3], [4, 4], [4, 5], [5, 0], [5, 1], [5, 2], [5, 3], [5, 4], [6, 0], [6, 1], [6, 2], [6, 3])
    Assert.assertEquals(possibleMoves, model.getIterated());
  }
}
