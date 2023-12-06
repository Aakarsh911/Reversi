package cs3500.reversi.provider.view.gui;

import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.JLabel;
import javax.swing.JPanel;


import cs3500.reversi.provider.model.Piece;
import cs3500.reversi.provider.model.ReadonlyReversiModel;
import cs3500.reversi.provider.model.TPRHex;

/**
 * A class for a panel for TwoPlayerReversi that implements TPRPanel and extends JPanel.
 */
public class TPRBoardPanel extends JPanel implements TPRPanel {
  private final ReadonlyReversiModel model;
  private JLabel playerScore;
  private JLabel currentTurn;
  TPRBoardCell currentCellSelected;

  /**
   * Constructor for TPRBoardPanel that takes in a ReadonlyReversiModel.
   *
   * @param model ReadonlyReversiModel for game state purposes
   */
  public TPRBoardPanel(ReadonlyReversiModel model) {
    this.setBackground(Color.DARK_GRAY);

    this.setPreferredSize(new Dimension(800, 800));

    this.model = model;
  }

  void addLabels(Piece playerPiece, int score, Piece currentTurnPiece) {
    JPanel topPanel = new JPanel();
    JLabel playerColor = new JLabel("Your Color: " + playerPiece);
    JLabel separator = new JLabel("\t\t\t\t");
    playerScore = new JLabel("Your Current Score: " + score);
    JLabel separator2 = new JLabel("\t\t\t\t");
    currentTurn = new JLabel("Current Turn: " + currentTurnPiece);

    topPanel.add(playerColor);
    topPanel.add(separator);
    topPanel.add(playerScore);
    topPanel.add(separator2);
    topPanel.add(currentTurn);
    this.add(topPanel);
  }

  private Dimension getPreferredLogicalSize() {
    return new Dimension(800, 800);
  }


  private AffineTransform logicalToPhysical() {
    AffineTransform trans = new AffineTransform();
    Dimension pref = getPreferredLogicalSize();

    trans.translate(getWidth() / 2.0, getHeight() / 2.0);
    trans.scale(getWidth() / pref.getWidth(), getHeight() / pref.getHeight());
    trans.scale(1, 1);
    return trans;
  }

  private AffineTransform physicalToLogical() {
    AffineTransform trans = new AffineTransform();
    Dimension pref = getPreferredLogicalSize();

    trans.scale(1, 1);
    trans.scale(getWidth() / pref.getWidth(), getHeight() / pref.getHeight());
    trans.translate(-getWidth() / 2.0, -getHeight() / 2.0);
    return trans;
  }

  private double getCellSize() {
    return getHeight() / (model.getSize() * 2.0 + 1.0) / 2.0;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g.create();
    g2d.transform(logicalToPhysical());

    for (TPRHex hex : model.getBoardGame().getKeys()) {
      TPRBoardCell currentHexCell = new TPRBoardCell(hex.getQ(), hex.getR(), getCellSize());

      drawOutlineOfHex(g2d, currentHexCell);
      fillInsideOfHex(g2d, currentHexCell, Color.LIGHT_GRAY);

      //add piece if they exist
      drawPieceAtCell(g2d, hex.getQ(), hex.getR(), currentHexCell);
    }

  }

  private void drawPieceAtCell(Graphics2D g2d, int q, int r, TPRBoardCell currentHexCell) {
    if (model.getBoardGame().getPieceAt(q, r) != null) {
      if (model.getBoardGame().getPieceAt(q, r) == Piece.BLACK) {
        //draw a black circle at the cell
        drawPieceAtCell(g2d, currentHexCell.getHexagonCoordinateQ(),
                currentHexCell.getHexagonCoordinateR(), currentHexCell.getSize(), Color.BLACK);
      } else if (model.getBoardGame().getPieceAt(q, r) == Piece.WHITE) {
        //draw a white circle at the cell
        drawPieceAtCell(g2d, currentHexCell.getHexagonCoordinateQ(),
                currentHexCell.getHexagonCoordinateR(), currentHexCell.getSize(), Color.WHITE);
      }
    }
  }

  private static void drawPieceAtCell(Graphics2D g2d, int q, int r, double size, Color c) {
    g2d.setColor(c);

    TPRBoardCell currentHexCell = new TPRBoardCell(q, r, size);
    //get center of cell coordinates
    double[] centerCellCoords = currentHexCell.getCenterCoordinates();
    //calculate radius of circle
    double radius = currentHexCell.getWidth() / 2.0;
    //use radius to calculate coordinates of upper-left corner (screen wise = bottom right corner)
    //distance between upper-left corner and center should be r * sqrt(2)
    // OR in screen coords:
    // upperLeftX = centerX - radius
    //upperLeftY = centerY - radius
    double upperLeftX = centerCellCoords[0] - radius / 2.0;
    double upperLeftY = centerCellCoords[1] - radius / 2.0;

    g2d.fill(new Ellipse2D.Double(upperLeftX, upperLeftY, radius, radius));
  }

  private static void fillInsideOfHex(Graphics2D g2d, TPRBoardCell currentHexCell, Color c) {
    //fill the inside
    g2d.setColor(c);
    g2d.fill(currentHexCell);
  }

  private static void drawOutlineOfHex(Graphics2D g2d, TPRBoardCell currentHexCell) {
    //create the outlines
    g2d.setColor(Color.BLACK);
    g2d.draw(currentHexCell);
  }

  @Override
  public void selectCell(Point screenCoords) {
    Point2D point = physicalToLogical().transform(screenCoords, null);
    System.out.println("logical x: " + point.getX());
    System.out.println("logical y: " + point.getY());
    System.out.println("size: " + getCellSize());

    Graphics2D g2d = (Graphics2D) this.getGraphics();
    g2d.transform(logicalToPhysical());

    int[] hexCoordsOfCell = convertScreenToHexCoords(point.getX(),
            point.getY() - getCellSize() / 2, getCellSize());
    //int[] hexCoordsOfCell = convertScreenToHexCoords(screenCoords.getX() - cellCenter,
    // screenCoords.getY() - (cellCenter + cellSize / 2), cellSize);
    //convert to hex coords
    int q = hexCoordsOfCell[0];
    int r = hexCoordsOfCell[1];
    if (Math.abs(q) <= model.getSize() && Math.abs(r) <= model.getSize()
            && Math.abs(-q - r) <= model.getSize()) {
      System.out.println("hex coordinates of cell clicked: " + q + ", "
              + r + ", " + hexCoordsOfCell[2]);
    } else {
      System.out.println("clicked outside of board bounds");
    }

    //check if a cell is already selected
    if (currentCellSelected == null) {
      if (Math.abs(q) <= model.getSize() && Math.abs(r) <= model.getSize()
              && Math.abs(-q - r) <= model.getSize()) {
        //cannot select cell if a piece is present
        if (model.getBoardGame().getPieceAt(q, r) == null) {
          currentCellSelected = new TPRBoardCell(q, r, getCellSize());

          drawOutlineOfHex(g2d, currentCellSelected);
          fillInsideOfHex(g2d, currentCellSelected, Color.CYAN);
          drawPieceAtCell(g2d, currentCellSelected.getHexagonCoordinateQ(),
                  currentCellSelected.getHexagonCoordinateR(), currentCellSelected);
        }
      }
    } else {
      //deselect cell
      drawOutlineOfHex(g2d, currentCellSelected);
      fillInsideOfHex(g2d, currentCellSelected, Color.LIGHT_GRAY);
      drawPieceAtCell(g2d, currentCellSelected.getHexagonCoordinateQ(),
              currentCellSelected.getHexagonCoordinateR(), currentCellSelected);

      //if clicking on cell other than selected one then highlight if there is no piece
      //aka not selecting the same cell and not selecting off the board
      if (model.getBoardGame().getPieceAt(q, r) == null
              && !currentCellSelected.equals(new TPRBoardCell(q, r, getCellSize()))
              && Math.abs(q) <= model.getSize() && Math.abs(r) <= model.getSize()
              && Math.abs(-q - r) <= model.getSize()) {
        currentCellSelected = new TPRBoardCell(q, r, getCellSize());
        drawOutlineOfHex(g2d, currentCellSelected);
        fillInsideOfHex(g2d, currentCellSelected, Color.CYAN);
        drawPieceAtCell(g2d, currentCellSelected.getHexagonCoordinateQ(),
                currentCellSelected.getHexagonCoordinateR(), currentCellSelected);
      } else {
        currentCellSelected = null;
      }
    }
  }


  private static int[] convertScreenToHexCoords(double x, double y, double size) {
    double fractionalQ = ((Math.sqrt(3) / 3.0 * x) - (1.0 / 3.0 * y)) / size;
    double fractionalR = (2.0 / 3.0 * y) / size;
    double fractionalS = -(fractionalQ + fractionalR);

    long q = Math.round(fractionalQ);
    long r = Math.round(fractionalR);
    long s = Math.round(fractionalS);

    double diffQ = Math.abs(q - fractionalQ);
    double diffR = Math.abs(r - fractionalR);
    double diffS = Math.abs(s - fractionalS);

    if (diffQ > diffR && diffQ > diffS) {
      q = -r - s;
    } else if (diffR > diffS) {
      r = -q - s;
    } else {
      s = -q - r;
    }
    return new int[]{(int) q, (int) r, (int) s};
  }

  void updateLabels(int currentScore, Piece currentTurn) {
    playerScore.setText("Your Current Score: " + currentScore);
    this.currentTurn.setText("Current Turn: " + currentTurn);
  }

  void updateTurnLabel(Piece p) {
    this.currentTurn.setText("Current Turn: " + p);
  }

  void updateScoreLabel(int score) {
    playerScore.setText("Your Current Score: " + score);
  }
}

