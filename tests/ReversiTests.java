import org.junit.Test;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.ReversiModel;

public class ReversiTests {
  @Test
  public void test1() {
    BasicReversi m = new BasicReversi(7);
    m.initCells(7);
  }
}
