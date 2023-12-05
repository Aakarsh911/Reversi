package cs3500.reversi.provider.view.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs3500.reversi.provider.controller.PlayerActionObserver;
import cs3500.reversi.provider.model.Piece;
import cs3500.reversi.provider.model.ReadonlyReversiModel;


/**
 * A class for the frame of the GUI for TwoPlayerReversi. Holds all of the components that make up
 * the GUI.
 */
public class TPRFrame extends JFrame implements TPRView {
  private final List<PlayerActionObserver> observerList;
  private final TPRBoardPanel panel;

  /**
   * A constructor for TPRFrame that takes in a ReadonlyReversiModel so that the model is immutable.
   *
   * @param model ReadonlyReversiModel for getting the current state of the game
   */
  public TPRFrame(ReadonlyReversiModel model) {
    super();

    //this.setLayout(new BorderLayout());
    this.observerList = new ArrayList<>();
    this.panel = new TPRBoardPanel(model);
    this.add(panel);

    //this.setPreferredSize(new Dimension(810, 850));
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    this.addClickListener();
    //this.addKeyPressListener();

    this.pack();
  }

  @Override
  public void addLabels(Piece playerPiece, int score, Piece currentTurnPiece) {
    panel.addLabels(playerPiece, score, currentTurnPiece);
  }

  @Override
  public void setVisible(boolean b) {
    super.setVisible(b);
  }

  @Override
  public void updateAll(int currentScore, Piece currentTurn) {
    panel.updateLabels(currentScore, currentTurn);
    this.refresh();
  }

  @Override
  public void updateTurn(Piece p) {
    panel.updateTurnLabel(p);
    this.refresh();
  }

  @Override
  public void updateScore(int score) {
    panel.updateScoreLabel(score);
    this.refresh();
  }

  @Override
  public void displayGameOver(Piece winner) {
    this.addMessage("game over! " + winner + " wins!");
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void addClickListener() {
    TPRMouseAdapter mouseAdapter = new TPRMouseAdapter(panel);
    this.addMouseListener(mouseAdapter);
    this.refresh();
  }

  @Override
  public List<Integer> getSelectedCellCoordinates() {
    if (panel.currentCellSelected != null) {
      return List.of(panel.currentCellSelected.getHexagonCoordinateQ(),
              panel.currentCellSelected.getHexagonCoordinateR());
    }
    throw new IllegalStateException("no cell is selected");
  }

  @Override
  public void addMessage(String message) {
    JOptionPane popup = new JOptionPane();
    JOptionPane.showMessageDialog(popup, message, "", JOptionPane.INFORMATION_MESSAGE);
    this.add(popup);
    this.refresh();
  }

  @Override
  public void addPlayerListener(PlayerActionObserver observer) {
    this.observerList.add(observer);

    this.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        if (Character.toLowerCase(e.getKeyChar()) == 'm') {
          System.out.println("move pressed");
          movePressed(getSelectedCellCoordinates());
          //feature.movePiece();
          //do something to move
        } else if (Character.toLowerCase(e.getKeyChar()) == 'p') {
          System.out.println("pass pressed");
          passPressed();
          //feature.passTurn();
          //do something to pass
        }
      }
    });

  }

  @Override
  public void movePressed(List<Integer> hexCoordinates) {
    for (PlayerActionObserver obs : observerList) {
      obs.move(hexCoordinates);
    }
  }

  @Override
  public void passPressed() {
    for (PlayerActionObserver obs : observerList) {
      obs.pass();
    }
  }


}
