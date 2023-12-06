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


  private final BoardAdapter board = new BoardAdapter();

  private final cs3500.reversi.model.ReversiModel model;

  public ReversiModelAdapter() {
    super();
    this.model = new BasicReversi();
  }

  public ReversiModelAdapter(int size) {
    super(size);
    this.model = new BasicReversi(size);
  }
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
  public void move(int row, int col) {
    super.move(row, col);
    Piece turn;
    if (this.model.getTurn().equalsIgnoreCase("black")) {
      turn = Piece.BLACK;
    } else {
      turn = Piece.WHITE;
    }
    this.board.placePieceAt(this.getCurrentTurn(), new HexAdapter(this.model.getBoard().get(row).get(col)));
    List<Hex> cellsToFlip = this.model.getCellsToFlip(row, col);
    System.out.println(getCurrentTurn());
    for (Hex h : cellsToFlip) {
      this.board.placePieceAt(turn, new HexAdapter(h));
    }

    System.out.println("here");
    //this.board.move(row, col);
  }

  @Override
  public void movePiece(Piece piece, TPRHex location) throws IllegalStateException, IllegalArgumentException {
    List<Integer> coord = this.convertHex(location);
    if (this.model.getTurn().equalsIgnoreCase(piece.toString())) {
      this.model.move(coord.get(0), coord.get(1));
      // also make the move on the board field so that it gets updated
      this.board.placePieceAt(piece, location);
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

  TPRHex convertHex(List<Integer> coord) {
    List<List<Hex>> board = this.getBoard();
    return new HexAdapter(board.get(coord.get(0)).get(coord.get(1)));
  }
}
