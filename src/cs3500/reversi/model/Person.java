package cs3500.reversi.model;

/**
 * Represents a person playing the game.
 */
public class Person implements Player {

  String color;

  public Person(String color) {
    this.color = color;
  }

  @Override
  public void notifyTurn() {

  }

  @Override
  public String getColor() {
    return color;
  }
}
