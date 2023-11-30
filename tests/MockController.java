import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.model.ModelListener;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.view.ViewFeatures;

public class MockController implements ReversiController, ModelListener, ViewFeatures {
  public StringBuilder log = new StringBuilder();

  public MockController(ReversiModel model) {
    model.addListener(this);
  }

  @Override
  public void notifyTurn() {
    log.append("notifyTurn() was called");
  }

  @Override
  public void move(int row, int col) {
    log.append("move(").append(row).append(", ").append(col).append(") was called");
  }

  @Override
  public void pass() {
    log.append("pass() was called");
  }

  @Override
  public void go() {
    log.append("go() was called");
  }
}
