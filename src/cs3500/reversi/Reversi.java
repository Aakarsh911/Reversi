package cs3500.reversi;

import javax.swing.KeyStroke;

import cs3500.reversi.controller.BasicReversiController;
import cs3500.reversi.model.AIFactory;
import cs3500.reversi.model.AiDifficulty;
import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.Person;
import cs3500.reversi.model.Player;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.model.ReversiModelAdapter;
import cs3500.reversi.provider.view.gui.TPRFrame;
import cs3500.reversi.view.ReversiFrame;
import cs3500.reversi.view.ReversiGraphicalView;
import cs3500.reversi.view.ReversiViewAdapter;

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
    ReversiModelAdapter model = new ReversiModelAdapter(7);
    ReversiGraphicalView view = new ReversiFrame(model);
    ReversiGraphicalView view2 = new ReversiViewAdapter(model);
    Player player1;
    Player player2;
    if (args[0].equals("human")) {
      player1 = new Person("white");
    } else {
      player1 = AIFactory.createAIPlayer(AiDifficulty.valueOf(args[0].toUpperCase()),
              "white", model);
    }
    if (args[1].equals("human")) {
      player2 = new Person("black");
    } else {
      player2 = AIFactory.createAIPlayer(AiDifficulty.valueOf(args[1].toUpperCase()),
              "black", model);
    }
    BasicReversiController controller = new BasicReversiController(model, player1, view);
    controller.goNow();
    BasicReversiController controller2 = new BasicReversiController(model, player2, view2);
    controller2.goNow();
    model.startGame();
    view.setVisible(true);
    view2.setVisible(true);
  }
}