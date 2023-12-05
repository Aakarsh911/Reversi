package cs3500.reversi.provider.view.gui;

import java.awt.geom.Path2D;
import java.util.Arrays;


/**
 * A class to make a board cell for the TPR GUI that extends Path2D.Double.
 */
public class TPRBoardCell extends Path2D.Double {
  private final double[] cellCenterCoordinates = new double[2];
  private final double height;
  private final double width;
  private final int q;
  private final int r;
  private final double size;

  /**
   * Constructor for TPRBoardCell that takes in Hexagon coordinates and the desired size of the
   * Hexagon.
   *
   * @param q    int for the q coordinate in axial coordinate system of the Hexagon
   * @param r    int for the r coordinate in axial coordinate system of the Hexagon
   * @param size double for the size of the hexagon, which is half of the height
   */
  public TPRBoardCell(int q, int r, double size) {
    this.q = q;
    this.r = r;
    this.size = size;
    this.height = 2 * size;
    this.width = Math.sqrt(3) * size;

    double qToX = translateHexToPixelX(q, r, size);
    double rToY = translateHexToPixelY(r, size);

    //translate to center of board
    double x = qToX;
    double y = rToY;

    cellCenterCoordinates[0] = x;
    cellCenterCoordinates[1] = y;

    //get coordinates of top point
    //x remains same
    double topY = y - 0.5 * height;

    //move to middle of board
    moveTo(x, topY);
    //draw lines to 6 corners of hexagon
    lineTo(x + 0.5 * width, topY + .25 * height);
    lineTo(x + 0.5 * width, topY + .75 * height);
    lineTo(x, topY + height);
    lineTo(x - 0.5 * width, topY + .75 * height);
    lineTo(x - 0.5 * width, topY + .25 * height);
    lineTo(x, topY);

    closePath();
  }

  private double translateHexToPixelX(int q, int r, double size) {
    return size * (Math.sqrt(3) * q + Math.sqrt(3) / 2.0 * r);
  }

  private double translateHexToPixelY(int r, double size) {
    return size * (3.0 / 2.0 * r);
  }

  /**
   * Gets the center coordinates.
   *
   * @return coordinates that form the center.
   */
  public double[] getCenterCoordinates() {
    return Arrays.copyOf(cellCenterCoordinates, cellCenterCoordinates.length);
  }

  /**
   * Gets the width of hexagon.
   *
   * @return the width.
   */
  public double getWidth() {
    return this.width;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof TPRBoardCell) {
      TPRBoardCell casted = (TPRBoardCell) o;
      return this.height == casted.height && this.width == casted.width
              && Arrays.equals(this.getCenterCoordinates(), casted.getCenterCoordinates());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return (int) ((this.cellCenterCoordinates[0] - this.height)
            * (this.width + this.cellCenterCoordinates[1]));
  }

  /**
   * Gets the hexagon q coordinate of a specific hexagon.
   *
   * @return the current hexagon q coordinate
   */
  public int getHexagonCoordinateQ() {
    return this.q;
  }

  /**
   * Gets the hexagon r coordinate of a specific hexagon.
   *
   * @return the current hexagon q coordinate
   */
  public int getHexagonCoordinateR(){
    return this.r;
  }

  public double getSize(){
    return this.size;
  }
}
