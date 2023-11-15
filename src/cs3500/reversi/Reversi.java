package cs3500.reversi;

import javax.swing.KeyStroke;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.view.ReversiFrame;
import cs3500.reversi.view.ReversiGraphicalView;

/**
 * Represents the main class for the Reversi game.
 */
public final class Reversi {
  /**
   * The main method for the Reversi game.
   * @param args the arguments for the game
   */
  public static void main(String[] args) {
    ReversiModel model = new BasicReversi(7);
    ReversiGraphicalView view = new ReversiFrame(model);
    view.setHotKey(KeyStroke.getKeyStroke("M"), "move");
    view.setHotKey(KeyStroke.getKeyStroke("P"), "pass");
    view.setVisible(true);
  }
}
