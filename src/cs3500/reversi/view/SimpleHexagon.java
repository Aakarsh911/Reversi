package cs3500.reversi.view;

import java.awt.geom.Path2D;

public class SimpleHexagon extends Path2D.Double {
  public SimpleHexagon(double x, double y, double radius) {
    moveTo(x + radius * Math.cos(0), y + radius * Math.sin(0));
    for (int i = 1; i < 6; i++) {
      lineTo(x + radius * Math.cos(i * 2 * Math.PI / 6), y + radius * Math.sin(i * 2 * Math.PI / 6));
    }
    closePath();
  }
}
