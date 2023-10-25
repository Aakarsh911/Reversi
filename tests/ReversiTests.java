import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.Hex;
import cs3500.reversi.model.ReversiCell;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.view.ReversiTextualView;
import cs3500.reversi.view.ReversiView;

public class ReversiTests {
  BasicReversi m;
  Hex src;
  Hex rDirPos;
  Hex rDirNeg;
  Hex qDirPos;
  Hex qDirNeg;
  Hex sDirPos;
  Hex sDirNeg;
  private void initCells(){

    m = new BasicReversi(11);
    m.startGame();
    List<List<Hex>> board = m.getBoard();
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
    List<Hex> rDirPosList = List.of(new ReversiCell(1,-1,0), new ReversiCell(2,-1,-1));
    Assert.assertEquals(rDirPosList, src.cellsInDirection(new ReversiCell(1,0,-1), 3));
  }

  @Test
  public void testLegalMove() {
    initCells();
    BasicReversi m2 = new BasicReversi(11);
    m2.startGame();
    m2.move(4, 3);
    m2.move(3, 4);
    m2.pass();
    m2.move(4, 2);
    m2.pass();
    ReversiView view = new ReversiTextualView(m2);
    System.out.println(view.toString());
    m.move(6,3);
    m.move(7,2);
  }

}
