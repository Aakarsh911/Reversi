package cs3500.reversi.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs3500.reversi.model.ReadOnlyModel;

public class SquarePanel extends JPanel {
  /**
   * Constructs a ReversiPanel.
   *
   * @param model the model to use
   */
  ReadOnlyModel model;
  ArrayList<ArrayList<SquareCell>> squares;
  private final List<ViewFeatures> featuresList = new ArrayList<>();
  private final int[] selectedCell = new int[]{-1, -1};
  private final int[] hoveredCell = new int[]{-1, -1};

  private boolean hintMode = false;
  public SquarePanel(ReadOnlyModel model) {
    this.model = model;
    this.squares = new ArrayList<>();
    setDoubleBuffered(true);
    this.initSquares();
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
            selectedCell[0] = -1;
            selectedCell[1] = -1;
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


  private void initSquares() {
    for (int i = 0; i < model.getNumRows(); i++) {
      ArrayList<SquareCell> row = new ArrayList<>();
      for (int j = 0; j < model.getNumRows(); j++) {
        int x = 30*i + this.getSize().width/2 - model.getBoard().size() * 15;
        int y = 30*j + this.getSize().height/2 - model.getBoard().size() * 15;
        row.add(new SquareCell(x, y, 30));
      }
      squares.add(row);
    }
  }

  public void addFeaturesListener(ViewFeatures features) {
    this.featuresList.add(features);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(400, 400);
  }

  @Override
  public void paintComponent(Graphics g) {
    squares.clear();
    initSquares();
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setColor(Color.DARK_GRAY);
    g2d.fill(new Rectangle(getSize()));
    for (int i = 0; i < model.getNumRows(); i++) {
      for (int j = 0; j < model.getNumRows(); j++) {
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fill(squares.get(i).get(j));
        g2d.setColor(Color.BLACK);
        g2d.draw(squares.get(i).get(j));
        if (model.getColor(model.getBoard().get(i).get(j)).equals("BLACK")) {
          g2d.setColor(Color.BLACK);
          g2d.fillOval(squares.get(i).get(j).x + 5, squares.get(i).get(j).y + 5, 20, 20);
        } else if (model.getColor(model.getBoard().get(i).get(j)).equals("WHITE")) {
          g2d.setColor(Color.WHITE);
          g2d.fillOval(squares.get(i).get(j).x + 5, squares.get(i).get(j).y + 5, 20, 20);
        }
      }
    }
    g2d.setColor(Color.CYAN);
    if (selectedCell[0] != -1 && selectedCell[1] != -1) {
      g2d.fill(squares.get(selectedCell[0]).get(selectedCell[1]));
      g2d.setColor(Color.BLACK);
      g2d.draw(squares.get(selectedCell[0]).get(selectedCell[1]));
    }
  }
  private class MouseEventsListener extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
      boolean isCellClicked = false;
      for (int i = 0; i < squares.size(); i++) {
        for (int j = 0; j < squares.get(i).size(); j++) {
          if (squares.get(i).get(j).contains(e.getPoint())) {
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

//    @Override
//    public void mouseMoved(MouseEvent e) {
//      hoveredCell[0] = -1;
//      hoveredCell[1] = -1;
//
//      for (int i = 0; i < squares.size(); i++) {
//        for (int j = 0; j < squares.get(i).size(); j++) {
//          if (squares.get(i).get(j).contains(e.getPoint())) {
//            hoveredCell[0] = i;
//            hoveredCell[1] = j;
//          }
//        }
//      }
//      repaint();
//    }
  }
}
