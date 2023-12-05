package cs3500.reversi.provider.view.gui;

import java.util.List;


import cs3500.reversi.provider.model.Piece;

/**
 * A view interface responsible for holding all elements required to display a TPR GUI. It detects
 * all mouse clicks and keyboard presses and refreshes the view when updated.
 */
public interface TPRView extends TPRViewSubject {
  /**
   * Makes the view visible or not.
   *
   * @param b boolean for visibility: true for visibile, false for not visible
   */
  void setVisible(boolean b);

  void displayGameOver(Piece winner);

  /**
   * Refreshes the screen.
   */
  void refresh();

  /**
   * Adds a click listener to the view.
   */
  void addClickListener();


  List<Integer> getSelectedCellCoordinates();

  void addMessage(String message);

  void addLabels(Piece color, int score, Piece currentTurn);

  void updateAll(int currentScore, Piece currentTurn);

  void updateTurn(Piece p);

  void updateScore(int score);

}

