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

/**
 * Represents an adapter for the provider's ReversiModel interface.
 */
public class ReversiModelAdapter implements ReversiModel, cs3500.reversi.model.ReversiModel {


  private final BoardAdapter board = new BoardAdapter();

  private final cs3500.reversi.model.ReversiModel model;


  /**
   * Constructs a ReversiModelAdapter.
   *
   * @param size the size of the board
   */
  public ReversiModelAdapter(int size) {
    this.model = new BasicReversi(size);
  }

  /**
   * Constructs a ReversiModelAdapter.
   *
   * @param model the model to adapt
   */
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
  public boolean isGameOver() {
    return model.isGameOver();
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
      return this.getBlackScore();
    } else {
      return this.getWhiteScore();
    }
  }

  @Override
  public void move(int row, int col) {
    List<Hex> cellsToFlip = this.model.getCellsToFlip(row, col);
    for (Hex h : cellsToFlip) {
      this.board.placePieceAt(this.getCurrentTurn(), new HexAdapter(h));
    }
    this.board.placePieceAt(this.getCurrentTurn(), new HexAdapter(this.model.getBoard().get(row).get(col)));
    this.model.move(row, col);
  }

  @Override
  public void pass() {
    this.model.pass();
  }

  @Override
  public void addListener(ModelListener p) {
    this.model.addListener(p);
  }

  @Override
  public void startGame() {
    model.startGame();
  }

  @Override
  public void movePiece(Piece piece, TPRHex location) throws IllegalStateException, IllegalArgumentException {
    List<Integer> coord = this.convertHex(location);
    if (this.model.getTurn().equalsIgnoreCase(piece.toString())) {
      this.model.move(coord.get(0), coord.get(1));
      // also make the move on the board field so that it gets updated
      this.board.placePieceAt(piece, location);
    } else {
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

  /**
   * Converts a TPRHex to a List of Integers representing the coordinates of the hex on our board.
   *
   * @param hex the TPRHex to convert
   * @return the List of Integers representing the coordinates of the hex in row,col format
   */
  List<Integer> convertHex(TPRHex hex) {
    List<Integer> result = new ArrayList<>();
    List<List<Hex>> board = this.model.getBoard();
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

  @Override
  public List<List<Hex>> getBoard() {
    return model.getBoard();
  }

  @Override
  public List<Hex> getCellsToFlip(int row, int col) {
    return model.getCellsToFlip(row, col);
  }

  @Override
  public String getColor(Hex h) {
    return model.getColor(h);
  }

  @Override
  public String getTurn() {
    return model.getTurn();
  }

  @Override
  public int getWhiteScore() {
    return model.getWhiteScore();
  }

  @Override
  public int getBlackScore() {
    return model.getBlackScore();
  }

  @Override
  public int getNumRows() {
    return model.getNumRows();
  }

  @Override
  public boolean isLegalMove(int row, int col) {
    return model.isLegalMove(row, col);
  }

  @Override
  public boolean anyLegalMovesForCurrentPlayer() {
    return model.anyLegalMovesForCurrentPlayer();
  }

  @Override
  public cs3500.reversi.model.ReversiModel copy() {
    return new ReversiModelAdapter(this.model.copy());
  }
}
