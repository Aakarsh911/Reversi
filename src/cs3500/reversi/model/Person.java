package cs3500.reversi.model;

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
