package cs3500.reversi.view;

import java.awt.geom.Path2D;

public class SimpleHexagon extends Path2D.Double {

  public boolean isHighlighted = false;
  public SimpleHexagon(double x, double y, double radius) {
    moveTo(x + radius * Math.cos(Math.PI / 6), y + radius * Math.sin(Math.PI / 6));
    for (int i = 1; i <= 6; i++) {
      lineTo(x + radius * Math.cos(Math.PI / 6 + i * Math.PI / 3), y + radius * Math.sin(Math.PI / 6 + i * Math.PI / 3));
    }
    closePath();
  }
}
