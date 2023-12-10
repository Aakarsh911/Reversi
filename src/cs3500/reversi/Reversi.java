package cs3500.reversi;


import cs3500.reversi.controller.BasicReversiController;
import cs3500.reversi.model.AIFactory;
import cs3500.reversi.model.AiDifficulty;
import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.Person;
import cs3500.reversi.model.Player;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.model.SquareReversi;
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
    if (args.length < 2) {
      throw new IllegalArgumentException("Must have 2 arguments");
    }

    ReversiModel model;
    ReversiGraphicalView view;
    ReversiGraphicalView view2;

    if (args.length > 2 && args[2].equals("square")) {
      if (args.length > 3) {
        model = new SquareReversi(Integer.parseInt(args[3]));
      } else {
        model = new SquareReversi();
      }
      view = new ReversiFrame(model, true);
      view2 = new ReversiFrame(model, true);
    } else if (args.length > 2) {
      model = new BasicReversi(Integer.parseInt(args[2]));
      view = new ReversiFrame(model);
      view2 = new ReversiFrame(model);
    } else {
      model = new BasicReversi();
      view = new ReversiFrame(model);
      view2 = new ReversiFrame(model);
    }
    Player player1;
    Player player2;
    player1 = createPlayer(args[0], model);
    player2 = createPlayer(args[1], model);
    BasicReversiController controller = new BasicReversiController(model, player1, view);
    controller.goNow();
    BasicReversiController controller2 = new BasicReversiController(model, player2, view2);
    controller2.goNow();
    model.startGame();
    view.setVisible(true);
    view2.setVisible(true);
  }

  private static Player createPlayer(String args, ReversiModel model) {
    Player player1;
    if (args.equals("human")) {
      player1 = new Person("white");
    } else {
      player1 = AIFactory.createAIPlayer(AiDifficulty.valueOf(args.toUpperCase()),
              "white", model);
    }
    return player1;
  }
}