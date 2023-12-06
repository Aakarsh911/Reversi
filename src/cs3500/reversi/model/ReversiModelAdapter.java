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

  private final cs3500.reversi.model.ReversiModel model;
  public ReversiModelAdapter(ReadOnlyModel model) {
    this.model = model.copy();
  }

  @Override
  public int getSize() {
    return this.getNumRows();
  }

  @Override
  public boolean canMove(Piece piece, TPRHex location) throws IllegalArgumentException {
    List<Integer> coord = this.convertHex(location);
    return this.model.isLegalMove(coord.get(0), coord.get(1));
  }

  @Override
  public Board getBoardGame() {
    return this.board;
  }

  @Override
  public Piece getCurrentTurn() {
    return this.model.getTurn().equalsIgnoreCase("black") ? Piece.BLACK : Piece.WHITE;
  }

  @Override
  public Set<List<TPRHex>> getAllValidMoves(Piece color) {
    Set<List<TPRHex>> result = new HashSet<>();
    List<List<Hex>> board = this.model.getBoard();
    for (int i = 0; i < board.size(); i++) {
      for (int j = 0; j < board.get(i).size(); j++) {
        if (this.model.isLegalMove(i, j)) {
          List<TPRHex> move = new ArrayList<>();
          move.add(new HexAdapter(board.get(i).get(j)));
          for (Hex h : this.model.getCellsToFlip(i, j)) {
            move.add(new HexAdapter(h));
          }
          System.out.println(move);
          result.add(move);
        }
      }
    }
    return result;
  }

  @Override
  public int getScore(Piece color) {
    if (color == Piece.BLACK) {
      return this.model.getBlackScore();
    } else {
      return this.model.getWhiteScore();
    }
  }

  @Override
  public void movePiece(Piece piece, TPRHex location) throws IllegalStateException, IllegalArgumentException {
    List<Integer> coord = this.convertHex(location);
    if (this.model.getTurn().equalsIgnoreCase(piece.toString())) {
      this.model.move(coord.get(0), coord.get(1));
    }
    else {
      throw new IllegalStateException("Not your turn!");
    }
  }

  @Override
  public void passTurn(Piece player) {
    this.model.pass();
  }

  @Override
  public void addStatusListener(ModelStatusObserver obs) {
    // don't need this
  }

  @Override
  public void announceNextTurn(Piece p) {
    // don't need this
  }

  @Override
  public void announceGameOver(Piece winner) {
    // don't need this
  }

  @Override
  public void updateScore(Piece p, int score) {
    // unnecessary
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
