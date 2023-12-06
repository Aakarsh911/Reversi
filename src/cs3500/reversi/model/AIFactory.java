package cs3500.reversi.model;


/**
 * Represents a factory for creating AI players.
 */
public class AIFactory {
  /**
   * Creates an AI player with the given difficulty and color.
   * @param diff the difficulty of the AI
   * @param color the color of the Player's pieces
   * @param model the model to be used by the AI
   * @return the AI player
   */
  public static Player createAIPlayer(AiDifficulty diff, String color, ReadOnlyModel model) {
    ReversiStrategy strat;
    if (diff == AiDifficulty.EASY) {
      strat = new BasicAI();
    } else if (diff == AiDifficulty.MEDIUM) {
      strat = new TryTwo(new CornerAI(), new BasicAI());
    } else if (diff == AiDifficulty.HARD) {
      strat = new TryTwo(new CornerAI(), new TryTwo(new AvoidAdjacentCornerCellsAI(),
              new BasicAI()));
    } else if (diff == AiDifficulty.EXTREME) {
      strat = new TryTwo(new CornerAI(),
              new TryTwo(new AvoidAdjacentCornerCellsAI(), new TryTwo(new MiniMaxAI(),
                      new BasicAI())));
    }
    else if (diff == AiDifficulty.PS1) {
      strat = new ProviderStrategy1(new ReversiModelAdapter(model));
    }
    else if (diff == AiDifficulty.PS2) {
      strat = new ProviderStrategy2(new ReversiModelAdapter(model));
    }
    else {
      throw new IllegalArgumentException("Invalid difficulty");
    }
    return new AIPlayer(strat, color, model);
  }
}
