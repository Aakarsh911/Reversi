package cs3500.reversi;

import javax.swing.KeyStroke;

import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.model.AIPlayer;
import cs3500.reversi.model.BasicAI;
import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.CornerAI;
import cs3500.reversi.model.MiniMaxAI;
import cs3500.reversi.model.Person;
import cs3500.reversi.model.Player;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.model.TryTwo;
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
    ReversiGraphicalView view2 = new ReversiFrame(model);
    Player player1 = new Person("white");
    Player player2 = new AIPlayer(new TryTwo(new CornerAI(), new BasicAI()), "black", model);
    ReversiController controller = new ReversiController(model, player1, view);
    ReversiController controller2 = new ReversiController(model, player2, view2);
    view.setHotKey(KeyStroke.getKeyStroke("M"), "move");
    view.setHotKey(KeyStroke.getKeyStroke("P"), "pass");
    view2.setHotKey(KeyStroke.getKeyStroke("M"), "move");
    view2.setHotKey(KeyStroke.getKeyStroke("P"), "pass");
    model.addPlayer(player1);
    model.addPlayer(player2);
    model.startGame();
    view.setVisible(true);
    view2.setVisible(true);
  }
}
