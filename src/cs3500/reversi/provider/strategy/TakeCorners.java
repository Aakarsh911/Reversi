package cs3500.reversi.provider.strategy;

import cs3500.reversi.provider.model.Board;
import cs3500.reversi.provider.model.Piece;
import cs3500.reversi.provider.model.ReadonlyReversiModel;
import cs3500.reversi.provider.model.TPRHex;


/**
 * A class implementing TPRStrategy that moves to a corner when available.
 */
public class TakeCorners implements TPRStrategy {
  private final Board board;
  private final int size;
  private final ReadonlyReversiModel model;

  /**
   * Constructor for TakeCorners strategy that takes in a ReadonlyReversiModel.
   *
   * @param model ReadonlyReversiModel to enact the strategy on
   */
  public TakeCorners(ReadonlyReversiModel model) {
    board = model.getBoardGame();
    this.size = model.getSize();
    this.model = model;
  }

  @Override
  public TPRHex chooseMove(ReadonlyReversiModel model, Piece pieceToMove) {
    //check if any of the corners are available to move to
    //choose the upperleft most corner
    //or choose the corner that will flip the most pieces?
    //make method in AsManyPieces that takes in a list of possible moves and returns the one
    //with highest score

    for(TPRHex hex : board.getCorners()){
      if(board.getPieceAt(hex) == null){
        return hex;
      }
    }
      return new AsManyPieces(this.model).chooseMove(model, pieceToMove);

  }
}
