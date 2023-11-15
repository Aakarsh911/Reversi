package cs3500.reversi.view;

import java.awt.geom.Path2D;


/**
 * Represents a hexagon as a Path2D.double.
 */
public class SimpleHexagon extends Path2D.Double {
  public boolean isHighlighted = false;
  int x;
  int y;

  /**
   * Constructs a SimpleHexagon.
   * @param x the x coordinate of the center of the hexagon
   * @param y the y coordinate of the center of the hexagon
   * @param radius the radius of the hexagon
   */
  public SimpleHexagon(double x, double y, double radius) {
    this.isHighlighted = false;
    this.x = (int) x;
    this.y = (int) y;
    moveTo(x + radius * Math.cos(Math.PI / 6), y + radius * Math.sin(Math.PI / 6));
    for (int i = 1; i <= 6; i++) {
      lineTo(x + radius * Math.cos(Math.PI / 6 + i * Math.PI / 3), y + radius *
              Math.sin(Math.PI / 6 + i * Math.PI / 3));
    }
    closePath();
  }
}
