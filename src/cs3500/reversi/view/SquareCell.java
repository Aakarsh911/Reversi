package cs3500.reversi.view;

import java.awt.geom.Path2D;

public class SquareCell extends Path2D.Double {
  int sideLength;
  int x;
  int y;

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
}
