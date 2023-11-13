import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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

  static List<List<Integer>> getCorners(BasicReversi model) {
    ArrayList<Integer> corner1 = new ArrayList<>(Arrays.asList(0, 0));
    ArrayList<Integer> corner2 = new ArrayList<>(Arrays.asList(0, (model.getBoard().size() - 1) / 2));
    ArrayList<Integer> corner3 = new ArrayList<>(Arrays.asList((model.getBoard().size() - 1) / 2, 0));
    ArrayList<Integer> corner4 = new ArrayList<>(Arrays.asList((model.getBoard().size() - 1) / 2, model.getBoard().size() - 1));
    ArrayList<Integer> corner5 = new ArrayList<>(Arrays.asList((model.getBoard().size() - 1), 0));
    ArrayList<Integer> corner6 = new ArrayList<>(Arrays.asList(model.getBoard().size() - 1, (model.getBoard().size() - 1) / 2));
    List<List<Integer>> corners = new ArrayList<>(Arrays.asList(corner1, corner2, corner3, corner4, corner5, corner6));
    return corners;
  }

  @Test
  public void testAvoidAdjacentCornerCellsAI() {
    MockStrategy3 model = new MockStrategy3();
    AvoidAdjacentCornerCellsAI ai = new AvoidAdjacentCornerCellsAI();
    ai.chooseMove(model, 1);
    List<List<Integer>> corner = getCorners(model);
    List<List<Integer>> iterated = model.getIterated();
  }
}
