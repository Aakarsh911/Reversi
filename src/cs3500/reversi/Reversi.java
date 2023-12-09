package cs3500.reversi;


import cs3500.reversi.controller.BasicReversiController;
import cs3500.reversi.model.AIFactory;
import cs3500.reversi.model.AiDifficulty;
import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.Person;
import cs3500.reversi.model.Player;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.model.ReversiModelAdapter;
import cs3500.reversi.model.SquareReversi;
import cs3500.reversi.view.ReversiFrame;
import cs3500.reversi.view.ReversiGraphicalView;
import cs3500.reversi.view.ReversiTextualView;
import cs3500.reversi.view.ReversiView;
import cs3500.reversi.view.ReversiViewAdapter;
import cs3500.reversi.view.SquareFrame;
import cs3500.reversi.view.SquareReversiTextualView;

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
    ReversiGraphicalView view2 = new ReversiFrame(model);
    ReversiModel sqModel = new SquareReversi(8);
    ReversiGraphicalView view3 = new SquareFrame(sqModel);
    ReversiGraphicalView view4 = new SquareFrame(sqModel);
    Player player1 = null;
    Player player2 = null;
    ReversiView sqView = new SquareReversiTextualView(sqModel);
    System.out.println(sqView.toString());
    if (args[0].equals("human")) {
      player1 = new Person("white");
    } else {
      player1 = AIFactory.createAIPlayer(AiDifficulty.valueOf(args[0].toUpperCase()),
              "white", sqModel);
    }
    if (args[1].equals("human")) {
      player2 = new Person("black");
    } else {
      player2 = AIFactory.createAIPlayer(AiDifficulty.valueOf(args[1].toUpperCase()),
              "black", sqModel);
    }
    BasicReversiController controller = new BasicReversiController(model, player1, view);
    controller.goNow();
    BasicReversiController controller2 = new BasicReversiController(model, player2, view2);
    controller2.goNow();
    model.startGame();
    BasicReversiController controller3 = new BasicReversiController(sqModel, player1, view3);
    controller3.goNow();
    BasicReversiController controller4 = new BasicReversiController(sqModel, player2, view4);
    controller4.goNow();
    sqModel.startGame();
//    view.setVisible(true);
//    view2.setVisible(true);
    view3.setVisible(true);
    view4.setVisible(true);
  }
}