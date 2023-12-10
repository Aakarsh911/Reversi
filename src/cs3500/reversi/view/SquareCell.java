package cs3500.reversi.view;

import java.awt.geom.Path2D;

public class SquareCell extends Path2D.Double {
  private final int sideLength;
  private final int x;
  private final int y;

  public SquareCell(int x, int y, int sideLength) {
    this.x = x;
    this.y = y;
    this.sideLength = sideLength;
    drawSquare();
  }

  private void drawSquare() {
    moveTo(x, y);
    lineTo(x + sideLength, y);
    lineTo(x + sideLength, y + sideLength);
    lineTo(x, y + sideLength);
    closePath();
  }

  /**
   * Gets the side length of the square.
   * @return the side length of the square
   */
  public int getSideLength() {
    return sideLength;
  }

  /**
   * Gets the x coordinate of the square.
   * @return the x coordinate of the square
   */
  public int getX() {
    return x;
  }

  /**
   * Gets the y coordinate of the square.
   * @return the y coordinate of the square
   */
  public int getY() {
    return y;
  }
}
