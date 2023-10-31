package cs3500.reversi.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Tests for the Reversi game.
 */
public class ModelPackageTests {

  private BasicReversi m;

  @Test
  public void testGetCellsToFlipInit() {
    m = new BasicReversi(11);
    List<Hex> cellsToFlip = m.getCellsToFlip(3, 3);
    List<Hex> cellsToFlip2 = m.getCellsToFlip(4, 3);
    Assert.assertEquals(0, cellsToFlip.size());
    Assert.assertEquals(1, cellsToFlip2.size());
  }

  @Test
  public void testGetCellsToFlipAfterMove() {
    m = new BasicReversi(11);
    m.move(4, 3);
    List<Hex> cellsToFlip = m.getCellsToFlip(5, 3);
    Assert.assertEquals(0, cellsToFlip.size());
  }
}
