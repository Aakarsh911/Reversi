package cs3500.reversi;

import javax.swing.KeyStroke;

import cs3500.reversi.controller.BasicReversiController;
import cs3500.reversi.model.AIFactory;
import cs3500.reversi.model.AiDifficulty;
import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.Person;
import cs3500.reversi.model.Player;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.view.ReversiFrame;
import cs3500.reversi.view.ReversiGraphicalView;

/**
 * Represents the main class for the Reversi game.
 **/
public final class Reversi {
  /**
   * The main method for the Reversi game.
   *
   * @param args the arguments for the game
   **/
  public static void main(String[] args) {
    ReversiModel model = new BasicReversi(11);
    ReversiGraphicalView view = new ReversiFrame(model);
    ReversiGraphicalView view2 = new ReversiFrame(model);
    Player player1 = null;
    Player player2 = null;
    if (args[0].equals("human")) {
      player1 = new Person("white");
    }
    if (args[1].equals("human")) {
      player2 = new Person("black");
    } else {
      player2 = new AIFactory().createAIPlayer(AiDifficulty.valueOf(args[1].toUpperCase()), "black", model);
    }
    BasicReversiController controller = new BasicReversiController(model, player1, view);
    controller.go();
    BasicReversiController controller2 = new BasicReversiController(model, player2, view2);
    controller2.go();
    view.setHotKey(KeyStroke.getKeyStroke("M"), "move");
    view.setHotKey(KeyStroke.getKeyStroke("P"), "pass");
    view2.setHotKey(KeyStroke.getKeyStroke("M"), "move");
    view2.setHotKey(KeyStroke.getKeyStroke("P"), "pass");
    model.startGame();
    view.setVisible(true);
    view2.setVisible(true);
  }
}