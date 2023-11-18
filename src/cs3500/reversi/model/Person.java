package cs3500.reversi.model;

import cs3500.reversi.view.ViewFeatures;

/**
 * Represents a person playing the game.
 */
public class Person implements Player {

  private final String color;

  private boolean isTurn = false;



  public Person(String color) {
    this.color = color;
  }

  @Override
  public void notifyTurn() {
    //TO-DO: Implement
  }

  @Override
  public String getColor() {
    return color;
  }

  @Override
  public void setFeaturesListener(ViewFeatures features) {
    //
  }
}
