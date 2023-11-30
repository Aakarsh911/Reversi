import org.junit.Assert;
import org.junit.Test;

import cs3500.reversi.controller.BasicReversiController;
import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.Person;
import cs3500.reversi.model.Player;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.view.ReversiFrame;
import cs3500.reversi.view.ReversiGraphicalView;

/**
 * Tests for the BasicReversiController class.
 */
public class BasicReversiControllerTests {
  ReversiModel model = new BasicReversi();
  MockModel mock = new MockModel();
  Player p1 = new Person("white");
  Player p2 = new Person("black");
  MockPlayer mockPlayer = new MockPlayer();
  ReversiGraphicalView view1 = new ReversiFrame(model);
  ReversiGraphicalView view2 = new ReversiFrame(model);
  BasicReversiController controller1 = new BasicReversiController(model, p1, view1);
  BasicReversiController controller2 = new BasicReversiController(model, p2, view2);
  BasicReversiController mockModelController = new BasicReversiController(mock, p1, view1);

  BasicReversiController mockPlayerController = new BasicReversiController(mock, mockPlayer, view1);

  MockController mockController = new MockController(model);

  @Test
  public void testMove() {
    mockModelController.notifyTurn();
    mockModelController.move(0, 0);
    Assert.assertEquals("move(0, 0) was called", mock.log.toString());
  }

  @Test
  public void testPass() {
    mockModelController.notifyTurn();
    mockModelController.pass();
    Assert.assertEquals("pass() was called", mock.log.toString());
  }

  @Test
  public void testNotifyPlayer() {
    mockPlayerController.notifyTurn();
    Assert.assertEquals("notifyTurn() was called", mockPlayer.log.toString());
  }

  @Test
  public void testPlayerMove() {
    mockPlayerController.move(0, 0);
    Assert.assertEquals("madeMove() was called", mockPlayer.log.toString());
  }

  @Test
  public void testNotifyController() {
    model.startGame();
    Assert.assertEquals("notifyTurn() was called", mockController.log.toString());
  }
}
