import cs3500.reversi.model.Player;
import cs3500.reversi.view.ViewFeatures;

public class MockPlayer implements Player {
  public StringBuilder log = new StringBuilder();

  @Override
  public void notifyTurn() {
    log.append("notifyTurn() was called");
  }

  @Override
  public void setFeaturesListener(ViewFeatures features) {

  }

  @Override
  public void madeMove() {
    log.append("madeMove() was called");
  }

  @Override
  public boolean isTurn() {
    return true;
  }

  @Override
  public String getColor() {
    return "white";
  }
}
