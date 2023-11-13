import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import cs3500.reversi.model.BasicAI;
import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.MockBasicStratgey;
import cs3500.reversi.model.ReversiStrategy;

public class ReversiStratgeyTests {
  @Test
  public void testBasicStrategy() {
    ReversiStrategy ai = new BasicAI();
    BasicReversi model = new MockBasicStratgey();
    Assert.assertEquals(List.of(3,6), ai.chooseMove(model, 1).get());
  }
}
