package cs3500.reversi.provider.strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cs3500.reversi.provider.model.Piece;
import cs3500.reversi.provider.model.ReadonlyReversiModel;
import cs3500.reversi.provider.model.TPRHex;

/**
 * A class for a TPRStrategy that chooses a move that captures the greatest number of pieces.
 */
public class AsManyPieces implements TPRStrategy {

  /**
   * Constructor that creates a hashMap that holds the pieceSizes.
   */
  public AsManyPieces(ReadonlyReversiModel model) {
    HashMap<TPRHex, Integer> pieceSizes = new HashMap<>();
  }

  @Override
  public TPRHex chooseMove(ReadonlyReversiModel model, Piece pieceToMove) {
    // first element of every list is num of pieces flipped
    // store in hashmap
    // get the size of that list
    // and pick the greatest one
    //if tie, pick upper-left most hex where q would be greater than r (i think)?
    // if list empty return null

    Map<TPRHex, Integer> movesToPossibleScore = new HashMap<>();

    Set<List<TPRHex>> allPossibleMoves = model.getAllValidMoves(pieceToMove);

    if (allPossibleMoves.isEmpty()) {
      return null; //no moves available
    }
    for (List<TPRHex> line : allPossibleMoves) {
      //compute the score for current line
      // add the first hexagon (the cell to move to) and the score to hashmap
      int value = line.size() - 1; //subtract 1 because we don't include the player's piece
      movesToPossibleScore.put(line.get(0), value);
    }

    //get the greatest score(s) in hashmap
    int maxValue = -Integer.MAX_VALUE;
    TPRHex hexWithGreatestValue = null;
    for (TPRHex hex : movesToPossibleScore.keySet()) {
      int currentScore = movesToPossibleScore.get(hex);
      if (currentScore > maxValue) {
        maxValue = movesToPossibleScore.get(hex);
        hexWithGreatestValue = hex;
      } else if (currentScore == maxValue) {
        //r has to be less and s has to be greater for uppermost leftmost hex
        if (hexWithGreatestValue != null && hex.getR() < hexWithGreatestValue.getR()
                && hex.getS() > hexWithGreatestValue.getS()) {
          hexWithGreatestValue = hex;
        }
      }

    }

    return hexWithGreatestValue;
  }
}
