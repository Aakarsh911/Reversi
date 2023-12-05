package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cs3500.reversi.provider.controller.ModelStatusObserver;
import cs3500.reversi.provider.model.Board;
import cs3500.reversi.provider.model.Piece;
import cs3500.reversi.provider.model.ReversiModel;
import cs3500.reversi.provider.model.TPRHex;

public class ReversiModelAdapter extends BasicReversi implements ReversiModel {

  private final Board board = new BoardAdapter();

  @Override
  public int getSize() {
    return this.getNumRows();
  }

  @Override
  public boolean canMove(Piece piece, TPRHex location) throws IllegalArgumentException {
    return false;
  }

  @Override
  public Board getBoardGame() {
    return this.board;
  }

  @Override
  public Piece getCurrentTurn() {
    return this.getTurn().equalsIgnoreCase("black") ? Piece.BLACK : Piece.WHITE;
  }

  @Override
  public Set<List<TPRHex>> getAllValidMoves(Piece color) {
    return null;
  }

  @Override
  public int getScore(Piece color) {
    if (color == Piece.BLACK) {
      return this.getBlackScore();
    } else {
      return this.getWhiteScore();
    }
  }

  @Override
  public void movePiece(Piece piece, TPRHex location) throws IllegalStateException, IllegalArgumentException {

  }

  @Override
  public void passTurn(Piece player) {

  }

  @Override
  public void addStatusListener(ModelStatusObserver obs) {

  }

  @Override
  public void announceNextTurn(Piece p) {

  }

  @Override
  public void announceGameOver(Piece winner) {

  }

  @Override
  public void updateScore(Piece p, int score) {

  }

  List<Integer> convertHex(TPRHex hex) {
    List<Integer> result = new ArrayList<>();
    List<List<Hex>> board = this.getBoard();
    for (int i = 0; i < board.size(); i++) {
      for (int j = 0; j < board.get(i).size(); j++) {
        if (board.get(i).get(j).equals(hex)) {
          result.add(i);
          result.add(j);
          return result;
        }
      }
    }
    return result;
  }
}
