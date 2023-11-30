import cs3500.reversi.model.Player;
import cs3500.reversi.view.ViewFeatures;

/**
 * Represents a mock player for testing.
 */
public class MockPlayer implements Player {
  public StringBuilder log = new StringBuilder();

  @Override
  public void notifyTurn() {
    log.append("notifyTurn() was called");
  }

  @Override
  public void setFeaturesListener(ViewFeatures features) {
    // do nothing, need to implement for interface
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
