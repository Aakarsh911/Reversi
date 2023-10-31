import org.junit.Assert;
import org.junit.Test;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.view.ReversiTextualView;

public class ExampleReversi {
  private BasicReversi m;
  @Test
  public void examplePass() {
    m = new BasicReversi();
    Assert.assertEquals("White", m.getTurn());
    m.pass();
    Assert.assertEquals("Black", m.getTurn());
    m.pass();
    Assert.assertEquals("White", m.getTurn());
  }

  @Test
  public void exampleValidMove(){
    m = new BasicReversi();
    m.move(4, 1);
    Assert.assertEquals("Black", m.getTurn());
    Assert.assertEquals(2, m.getBlackScore());
    Assert.assertEquals(5, m.getWhiteScore());
    Assert.assertEquals("WHITE", m.getColor(m.getBoard().get(4).get(1)).toString());
  }

  @Test
  public void exampleInvalidMoveFilledCell(){
    m = new BasicReversi();
    Assert.assertThrows(IllegalStateException.class, () -> m.move(4, 3));
  }

  @Test
  public void exampleInvalidMoveNoEdge(){
    m = new BasicReversi();
    m.move(4, 1);
    Assert.assertThrows(IllegalStateException.class, () -> m.move(4, 0));
  }
}
