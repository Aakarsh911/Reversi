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

  public ReversiPanel(ReadOnlyModel model) {
    this.model = model;
    MouseEventsListener listener = new MouseEventsListener();
    this.addMouseListener(listener);
    this.addMouseMotionListener(listener);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(350, 350);
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
    // Draw your calibration pattern here
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
