import org.junit.Assert;
import org.junit.Test;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.view.ReversiTextualView;

/**
 * Tests for the Reversi game.
 */
public class ReversiViewTests {

  @Test
  public void testInitialView() {
    BasicReversi m = new BasicReversi(11);
    ReversiTextualView view = new ReversiTextualView(m);
    Assert.assertEquals("" +
            "     _ _ _ _ _ _ \n" +
            "    _ _ _ _ _ _ _ \n" +
            "   _ _ _ _ _ _ _ _ \n" +
            "  _ _ _ _ _ _ _ _ _ \n" +
            " _ _ _ _ X O _ _ _ _ \n" +
            "_ _ _ _ O _ X _ _ _ _ \n" +
            " _ _ _ _ X O _ _ _ _ \n" +
            "  _ _ _ _ _ _ _ _ _ \n" +
            "   _ _ _ _ _ _ _ _ \n" +
            "    _ _ _ _ _ _ _ \n" +
            "     _ _ _ _ _ _ \n" +
            "White's turn\n" +
            "\n" +
            "Score: Black - 3 , White - 3", view.toString());
  }

  @Test
  public void testViewAfterMove() {
    BasicReversi m = new BasicReversi(11);
    ReversiTextualView view = new ReversiTextualView(m);
    m.move(4, 3);
    System.out.println(view.toString());
    Assert.assertEquals("     _ _ _ _ _ _ \n" +
            "    _ _ _ _ _ _ _ \n" +
            "   _ _ _ _ _ _ _ _ \n" +
            "  _ _ _ _ _ _ _ _ _ \n" +
            " _ _ _ O O O _ _ _ _ \n" +
            "_ _ _ _ O _ X _ _ _ _ \n" +
            " _ _ _ _ X O _ _ _ _ \n" +
            "  _ _ _ _ _ _ _ _ _ \n" +
            "   _ _ _ _ _ _ _ _ \n" +
            "    _ _ _ _ _ _ _ \n" +
            "     _ _ _ _ _ _ \n" +
            "Black's turn\n" +
            "\n" +
            "Score: Black - 2 , White - 5", view.toString());
  }

  @Test
  public void testViewAfterGameOver() {
    BasicReversi m2 = new BasicReversi(11);
    ReversiTextualView view = new ReversiTextualView(m2);
    m2.move(4, 3);
    m2.move(3, 4);
    m2.pass();
    m2.move(4, 2);
    m2.pass();
    m2.move(6, 3);
    m2.pass();
    m2.move(6, 6);
    Assert.assertEquals("     _ _ _ _ _ _ \n" +
            "    _ _ _ _ _ _ _ \n" +
            "   _ _ _ _ _ _ _ _ \n" +
            "  _ _ _ _ X _ _ _ _ \n" +
            " _ _ X X X X _ _ _ _ \n" +
            "_ _ _ _ X _ X _ _ _ _ \n" +
            " _ _ _ X X X X _ _ _ \n" +
            "  _ _ _ _ _ _ _ _ _ \n" +
            "   _ _ _ _ _ _ _ _ \n" +
            "    _ _ _ _ _ _ _ \n" +
            "     _ _ _ _ _ _ \n" +
            "\n" +
            "Game over!\n" +
            "Score: Black - 11 , White - 0", view.toString());
  }
}
