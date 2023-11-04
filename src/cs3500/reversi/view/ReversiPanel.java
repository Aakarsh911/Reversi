package cs3500.reversi.view;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import cs3500.reversi.model.ReadOnlyModel;

public class ReversiPanel extends JPanel {
  private final ReadOnlyModel model;

  private final List<ViewFeatures> featuresList = new ArrayList<>();

  private boolean mouseIsDown;

  private ArrayList<ArrayList<SimpleHexagon>> hexagons = new ArrayList<>();

  private int xOfHighlightedHexagon = 0;

  private int yOfHighlightedHexagon = 0;

  private int x = 0;
  private int y = 0;

  public ReversiPanel(ReadOnlyModel model) {
    setDoubleBuffered(true);
    this.model = model;
    MouseEventsListener listener = new MouseEventsListener();
    this.addMouseListener(listener);
    this.addMouseMotionListener(listener);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(model.getBoard().size() * 35, model.getBoard().size() * 31);
  }

  private Dimension getPreferredLogicalSize() {
    return new Dimension(40, 40);
  }


  public void addFeaturesListener(ViewFeatures features) {
    this.featuresList.add(features);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.hexagons = new ArrayList<>();
    Graphics2D g2d = (Graphics2D) g.create();
    int k;
    for (int i = 0; i < model.getBoard().size(); i++) {
      ArrayList<SimpleHexagon> row = new ArrayList<>();
      for (int j = 0; j < model.getBoard().get(i).size(); j++) {
        k = Math.abs(model.getBoard().size() / 2 - i);
        int x = k * 17 + 20 + (j * 34);
        int y = 20 + (i * 30);
        SimpleHexagon hex = new SimpleHexagon(x, y, 20);
        row.add(hex);
        g2d.setColor(Color.GRAY);
        g2d.fill(hex);
        g2d.setColor(Color.BLACK);
        g2d.draw(hex);
        if (model.getColor(model.getBoard().get(i).get(j)).toString().equals("BLACK")) {
          g2d.setColor(Color.BLACK);
          g2d.fillOval(x - 10, y - 10, 20, 20);
        }
        else if (model.getColor(model.getBoard().get(i).get(j)).toString().equals("WHITE")) {
          g2d.setColor(Color.WHITE);
          g2d.fillOval(x - 10, y - 10, 20, 20);
        }
      }
      this.hexagons.add(row);
    }
    int yIndex = (x - Math.abs(model.getBoard().size() / 2 - ((y - 20) / 30)) * 17 - 20) / 34;
    if (x != 0 && y != 0) {
      if(model.getColor(model.getBoard().get((y - 20) / 30).get(yIndex)).toString().equals("EMPTY")) {
        if (x == xOfHighlightedHexagon && y == yOfHighlightedHexagon) {
          g2d.setColor(Color.GRAY);
          xOfHighlightedHexagon = 0;
          yOfHighlightedHexagon = 0;
        }
        else {
          g2d.setColor(Color.CYAN);
          xOfHighlightedHexagon = x;
          yOfHighlightedHexagon = y;
        }
        if (x != 0 && y != 0) {
          g2d.fill(new SimpleHexagon(x, y, 20));
          g2d.setColor(Color.BLACK);
          g2d.draw(new SimpleHexagon(x, y, 20));
        }
      }
    }
  }

  private class MouseEventsListener extends MouseInputAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
      ReversiPanel.this.mouseIsDown = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      ReversiPanel.this.mouseIsDown = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      for (int i = 0; i < hexagons.size(); i++) {
        for (int j = 0; j < hexagons.get(i).size(); j++) {
          hexagons.get(i).get(j).isHighlighted = false;
          if (hexagons.get(i).get(j).contains(e.getPoint())) {
            hexagons.get(i).get(j).isHighlighted = true;
            x = hexagons.get(i).get(j).x;
            y = hexagons.get(i).get(j).y;
            System.out.println("Clicked on " + i + " " + j);
          }
        }
      }
      repaint();
    }

  }
}
