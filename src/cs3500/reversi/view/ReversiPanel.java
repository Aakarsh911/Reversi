package cs3500.reversi.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import cs3500.reversi.model.ReadOnlyModel;

/**
 * Represents the panel for the Reversi game.
 */
public class ReversiPanel extends JPanel {
  private final ReadOnlyModel model;

  private final List<ViewFeatures> featuresList = new ArrayList<>();

  private final ArrayList<ArrayList<SimpleHexagon>> hexagons = new ArrayList<>();


  private final int[] selectedCell = new int[2];
  private final int[] hoveredCell = new int[]{-1, -1};

  private boolean hintMode = false;


  /**
   * Constructs a ReversiPanel.
   * @param model the model to use
   */
  public ReversiPanel(ReadOnlyModel model) {
    this.selectedCell[0] = -1;
    this.selectedCell[1] = -1;
    setDoubleBuffered(true);
    this.model = model;
    this.initHexagons();
    MouseEventsListener listener = new MouseEventsListener();
    this.getInputMap().put(KeyStroke.getKeyStroke("M"), "move");
    this.getInputMap().put(KeyStroke.getKeyStroke("P"), "pass");
    this.getInputMap().put(KeyStroke.getKeyStroke("H"), "hint");
    this.addMouseListener(listener);
    this.addMouseMotionListener(listener);
    this.getActionMap().put("move", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (!featuresList.isEmpty()) {
          for (ViewFeatures features : featuresList) {
            features.move(selectedCell[0], selectedCell[1]);
          }
        }
      }
    });
    this.getActionMap().put("pass", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (!featuresList.isEmpty()) {
          for (ViewFeatures features : featuresList) {
            features.pass();
          }
        }
      }
    });
    this.getActionMap().put("hint", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        hintMode = !hintMode;
      }
    });
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(model.getBoard().size() * 34 + 5 + this.getSize().width
            , model.getBoard().size() * 31 + this.getSize().height);
  }

  public void addFeaturesListener(ViewFeatures features) {
    this.featuresList.add(features);
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setColor(Color.DARK_GRAY);
    g2d.fill(new Rectangle(getPreferredSize()));
    drawHexagons(g2d);
  }

  private void initHexagons() {
    int k;
    for (int i = 0; i < model.getBoard().size(); i++) {
      ArrayList<SimpleHexagon> row = new ArrayList<>();
      for (int j = 0; j < model.getBoard().get(i).size(); j++) {
        k = Math.abs(model.getBoard().size() / 2 - i);
        int x =  k * 17 + 17 + (j * 34) + this.getSize().width / 2 - model.getBoard().size() * 17;
        int y = 15 + (i * 30) + this.getSize().height / 2 - model.getBoard().size() * 15;
        SimpleHexagon hex = new SimpleHexagon(x, y, 20);
        row.add(hex);
      }
      this.hexagons.add(row);
    }
  }

  private void drawHexagons(Graphics2D g2d) {
    hexagons.clear();
    initHexagons();
    for (int row = 0; row < hexagons.size(); row++) {
      for (int col = 0; col < hexagons.get(row).size(); col++) {
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fill(hexagons.get(row).get(col));
        g2d.setColor(Color.BLACK);
        g2d.draw(hexagons.get(row).get(col));
        SimpleHexagon hex = hexagons.get(row).get(col);
        if (model.getColor(model.getBoard().get(row).get(col)).equals("BLACK")) {
          g2d.setColor(Color.BLACK);
          g2d.fillOval(hex.x - 10, hex.y - 10, 20, 20);
        } else if (model.getColor(model.getBoard().get(row).get(col)).equals("WHITE")) {
          g2d.setColor(Color.WHITE);
          g2d.fillOval(hex.x - 10, hex.y - 10, 20, 20);
        }
        if (row == hoveredCell[0] && col == hoveredCell[1]) {
          if (model.isLegalMove(hoveredCell[0], hoveredCell[1])) {
            if (model.getTurn().equals("White")) {
              g2d.setColor(new Color(255, 255, 255, 175));
            }
            else {
              g2d.setColor(new Color(0, 0, 0, 175));
            }
            g2d.fillOval(hexagons.get(row).get(col).x - 10, hexagons.get(row).get(col).y - 10,
                    20, 20);
          }
        }
      }
    }
    String color = "";
    if (selectedCell[0] != -1 && selectedCell[1] != -1) {
      color = model.getColor(model.getBoard().get(selectedCell[0]).get(selectedCell[1]));
    }
    if (selectedCell[0] != -1 && selectedCell[1] != -1 && color.equals("EMPTY")) {
      g2d.setColor(Color.CYAN);
      g2d.fill(hexagons.get(selectedCell[0]).get(selectedCell[1]));
      g2d.setColor(Color.BLACK);
      g2d.draw(hexagons.get(selectedCell[0]).get(selectedCell[1]));
      if (hintMode) {
        g2d.drawString(model.getCellsToFlip(selectedCell[0], selectedCell[1]).size() + "",
                hexagons.get(selectedCell[0]).get(selectedCell[1]).x - 5,
                hexagons.get(selectedCell[0]).get(selectedCell[1]).y + 5);
      }
    }
  }

  private class MouseEventsListener extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
      boolean isCellClicked = false;
      for (int i = 0; i < hexagons.size(); i++) {
        for (int j = 0; j < hexagons.get(i).size(); j++) {
          if (hexagons.get(i).get(j).contains(e.getPoint())) {
            isCellClicked = true;
            if (i == selectedCell[0] && j == selectedCell[1]) {
              selectedCell[0] = -1;
              selectedCell[1] = -1;
            } else {
              selectedCell[0] = i;
              selectedCell[1] = j;
            }
            System.out.println("Clicked on " + i + " " + j);
          }
        }
      }
      if (!isCellClicked) {
        selectedCell[0] = -1;
        selectedCell[1] = -1;
      }
      repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      hoveredCell[0] = -1;
      hoveredCell[1] = -1;

      for (int i = 0; i < hexagons.size(); i++) {
        for (int j = 0; j < hexagons.get(i).size(); j++) {
          if (hexagons.get(i).get(j).contains(e.getPoint())) {
            hoveredCell[0] = i;
            hoveredCell[1] = j;
          }
        }
      }
      repaint();
    }
  }
}