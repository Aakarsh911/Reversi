package cs3500.reversi.view;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import cs3500.reversi.model.ReadOnlyModel;

public class ReversiPanel extends JPanel {
  private final ReadOnlyModel model;

  private final List<ViewFeatures> featuresList = new ArrayList<>();

  private boolean mouseIsDown;

  private ArrayList<SimpleHexagon> hexagons = new ArrayList<SimpleHexagon>();

  public ReversiPanel(ReadOnlyModel model) {
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
    Graphics2D g2d = (Graphics2D) g.create();
    // draw the board with correct spacing
    int k = (model.getBoard().size() - 1) / 2;
    for (int i = 0; i < model.getBoard().size(); i++) {
      for (int j = 0; j < model.getBoard().get(i).size(); j++) {
        g2d.setColor(Color.GRAY);
        g2d.fill(new SimpleHexagon(k * 17 + 20 + (j * 34), 20 + (i * 30), 20));
        this.hexagons.add(new SimpleHexagon(k * 17 + 20 + (j * 34), 20 + (i * 30), 20));
        g2d.setColor(Color.BLACK);
        g2d.draw(new SimpleHexagon(k * 17 + 20 + (j * 34), 20 + (i * 30), 20));
        if (model.getColor(model.getBoard().get(i).get(j)).toString().equals("BLACK")) {
          g2d.setColor(Color.BLACK);
          g2d.fillOval(k * 17 + 20 + (j * 34) - 10, 20 + (i * 30) - 10, 20, 20);
        }
        else if (model.getColor(model.getBoard().get(i).get(j)).toString().equals("WHITE")) {
          g2d.setColor(Color.WHITE);
          g2d.fillOval(k * 17 + 20 + (j * 34) - 10, 20 + (i * 30) - 10, 20, 20);
        }
      }
      if (i < model.getBoard().size() / 2) {
        k--;
      }
      else if (i == model.getBoard().size() / 2) {
        k = 1;
      }
      else {
        k++;
      }
    }
//    g2d.setColor(Color.GRAY);
//    g2d.fill(new SimpleHexagon(20, 20, 20));
//    g2d.fill(new SimpleHexagon(54, 20, 20));
//    g2d.fill(new SimpleHexagon(88, 20, 20));
//    g2d.fill(new SimpleHexagon(37, 50, 20));
//    g2d.fill(new SimpleHexagon(71, 50, 20));
//    g2d.setColor(Color.BLACK);
//    g2d.draw(new SimpleHexagon(20, 20, 20));
//    g2d.draw(new SimpleHexagon(54, 20, 20));
//    g2d.draw(new SimpleHexagon(88, 20, 20));
//    g2d.draw(new SimpleHexagon(37, 50, 20));
//    g2d.draw(new SimpleHexagon(71, 50, 20));
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
  }
}
