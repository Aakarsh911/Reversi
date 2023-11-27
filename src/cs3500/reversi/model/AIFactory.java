package cs3500.reversi.model;

import cs3500.reversi.Reversi;

public class AIFactory {
  public static Player createAIPlayer(AiDifficulty diff, String color, ReadOnlyModel model) {
    ReversiStrategy strat;
    if (diff == AiDifficulty.EASY) {
      strat = new BasicAI();
    } else if (diff == AiDifficulty.MEDIUM) {
      strat = new TryTwo(new CornerAI(), new BasicAI());
    } else if (diff == AiDifficulty.HARD) {
      strat = new TryTwo(new CornerAI(), new TryTwo(new AvoidAdjacentCornerCellsAI(), new BasicAI()));
    } else {
      strat = new TryTwo(new CornerAI(),
              new TryTwo(new AvoidAdjacentCornerCellsAI(), new TryTwo(new MiniMaxAI(), new BasicAI())));
    }
    return new AIPlayer(strat, color, model);
  }
}
