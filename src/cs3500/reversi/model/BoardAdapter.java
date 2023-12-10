package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cs3500.reversi.provider.model.Board;
import cs3500.reversi.provider.model.Piece;
import cs3500.reversi.provider.model.TPRHex;

/**
 * Represents an adapter for the provider's Board interface.
 */
public class BoardAdapter extends BasicReversi implements Board {

  /**
   * Constructs a BoardAdapter.
   */
  public BoardAdapter() {
    super();
  }

  /**
   * Constructs a BoardAdapter.
   *
   * @param board the board to adapt
   */
  public BoardAdapter(BoardAdapter board) {
    super(board);
  }

  @Override
  public boolean hasPieceAt(TPRHex hex) throws IllegalArgumentException {
    return !this.getColor(new BoardPieceAdapter(hex)).equalsIgnoreCase("empty");
  }

  @Override
  public Set<TPRHex> getKeys() {
    Set<BoardPiece> oldKeys = this.cellStates.keySet();
    Set<TPRHex> newKeys = new HashSet<>();
    for (BoardPiece h : oldKeys) {
      newKeys.add(new BoardPieceAdapter(h.getQ(), h.getR(), h.getS()));
    }
    return newKeys;
  }

  @Override
  public List<TPRHex> getStraightLine(TPRHex location, TPRHex direction) {
    List<BoardPiece> oldList = new BoardPieceAdapter(location).cellsInDirection(
            new BoardPieceAdapter(direction),
            this.cellStates);
    List<TPRHex> newList = new ArrayList<>();
    for (BoardPiece h : oldList) {
      newList.add(new BoardPieceAdapter(h));
    }
    return newList;
  }

  @Override
  public Board getCopy() {
    return new BoardAdapter(this);
  }

  @Override
  public Piece getPieceAt(TPRHex hex) throws IllegalArgumentException {
    BoardPieceAdapter boardPieceAdapter = new BoardPieceAdapter(hex);
    if (!this.cellStates.containsKey(boardPieceAdapter)) {
      throw new IllegalArgumentException("Invalid hexagonal position");
    }
    String color = this.getColor(boardPieceAdapter).toLowerCase();
    switch (color) {
      case "black":
        return Piece.BLACK;
      case "white":
        return Piece.WHITE;
      default:
        return null;
    }
  }

  @Override
  public Piece getPieceAt(int q, int r) throws IllegalArgumentException {
    BoardPieceAdapter boardPieceAdapter = new BoardPieceAdapter(q, r, -q - r);
    return this.getPieceAt(boardPieceAdapter);
  }

  @Override
  public void placePieceAt(Piece piece, TPRHex hex) {
    BoardPieceAdapter boardPieceAdapter = new BoardPieceAdapter(hex);
    CellState state = piece == Piece.BLACK ? CellState.BLACK : CellState.WHITE;
    if (this.cellStates.containsKey(boardPieceAdapter)) {
      this.cellStates.replace(boardPieceAdapter, state);
    } else {
      throw new IllegalArgumentException("Invalid hexagonal position");
    }
  }

  @Override
  public List<TPRHex> getCorners() {
    List<BoardPiece> oldCorners = CornerAI.getCorners(this).stream()
            .map(h -> this.getBoard().get(h.get(0)).get(h.get(1))).collect(Collectors.toList());
    List<TPRHex> newCorners = new ArrayList<>();
    for (BoardPiece h : oldCorners) {
      newCorners.add(new BoardPieceAdapter(h));
    }
    return newCorners;
  }
}
