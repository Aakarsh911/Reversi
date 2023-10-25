import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.ReversiCell;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.view.ReversiTextualView;
import cs3500.reversi.view.ReversiView;

public class ReversiTests {
  BasicReversi m;
  ReversiCell src;
  ReversiCell rDirPos;
  ReversiCell rDirNeg;
  ReversiCell qDirPos;
  ReversiCell qDirNeg;
  ReversiCell sDirPos;
  ReversiCell sDirNeg;
  private void initCells(){

    m = new BasicReversi(11);
    m.startGame();
    List<List<ReversiCell>> board = m.getBoard();
    src = board.get(2).get(2);
    rDirPos = board.get(1).get(2);
    rDirNeg = board.get(1).get(0);
    qDirPos = board.get(0).get(0);
    qDirNeg = board.get(2).get(2);
    sDirPos = board.get(0).get(1);
    sDirNeg = board.get(2).get(1);
  }
  @Test
  public void testGetDirection() {
    initCells();
    Assert.assertEquals(new ReversiCell(1, 0, -1), src.getDirection(rDirPos));
    Assert.assertEquals(new ReversiCell(-1, 0, 1), src.getDirection(rDirNeg));
    Assert.assertEquals(new ReversiCell(0, -1, 1), src.getDirection(qDirPos));
    Assert.assertEquals(new ReversiCell(0, 1, -1), src.getDirection(qDirNeg));
    Assert.assertEquals(new ReversiCell(1, -1, 0), src.getDirection(sDirPos));
    Assert.assertEquals(new ReversiCell(-1, 1, 0), src.getDirection(sDirNeg));
  }

  @Test
  public void testCellsInDirection() {
    initCells();
    List<ReversiCell> rDirPosList = List.of(new ReversiCell(1,-1,0), new ReversiCell(2,-1,-1));
    Assert.assertEquals(rDirPosList, src.cellsInDirection(new ReversiCell(1,0,-1), 3));
  }

  @Test
  public void testLegalMove() {
    initCells();
    BasicReversi m2 = new BasicReversi(15);
    m2.startGame();
    ReversiView view = new ReversiTextualView(m2);
    System.out.println(view.toString());
    m.move(6,3);
    m.move(7,2);
  }

}
