package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cs3500.reversi.provider.model.Board;
import cs3500.reversi.provider.model.Piece;
import cs3500.reversi.provider.model.TPRHex;

public class BoardAdapter extends BasicReversi implements Board {
  public BoardAdapter() {
    super();
  }
  public BoardAdapter(BoardAdapter board) {
    super(board);
  }
  @Override
  public boolean hasPieceAt(TPRHex hex) throws IllegalArgumentException {
    return !this.getColor(new HexAdapter(hex)).equalsIgnoreCase("empty");
  }

  @Override
  public Set<TPRHex> getKeys() {
    Set<Hex> oldKeys = this.cellStates.keySet();
    Set<TPRHex> newKeys = new HashSet<>();
    for (Hex h : oldKeys) {
      newKeys.add(new HexAdapter(h.getQ(), h.getR(), h.getS()));
    }
    return newKeys;
  }

  @Override
  public List<TPRHex> getStraightLine(TPRHex location, TPRHex direction) {
    List<Hex> oldList = new HexAdapter(location).cellsInDirection(new HexAdapter(direction),
            this.cellStates);
    List<TPRHex> newList = new ArrayList<>();
    for (Hex h : oldList) {
      newList.add(new HexAdapter(h));
    }
    return newList;
  }

  @Override
  public Board getCopy() {
    return new BoardAdapter(this);
  }

  @Override
  public Piece getPieceAt(TPRHex hex) throws IllegalArgumentException {
    HexAdapter hexAdapter = new HexAdapter(hex);
    return this.getColor(hexAdapter).equalsIgnoreCase("black") ? Piece.BLACK
            : Piece.WHITE;
  }

  @Override
  public Piece getPieceAt(int q, int r) throws IllegalArgumentException {
    HexAdapter hexAdapter = new HexAdapter(q, r, -q - r);
    return this.getPieceAt(hexAdapter);
  }

  @Override
  public void placePieceAt(Piece piece, TPRHex hex) {
    HexAdapter hexAdapter = new HexAdapter(hex);
    CellState state = piece == Piece.BLACK ? CellState.BLACK : CellState.WHITE;
    if (this.cellStates.containsKey(hexAdapter)) {
      this.cellStates.replace(hexAdapter, state);
    } else {
      throw new IllegalArgumentException("Invalid hexagonal position");
    }
  }

  @Override
  public List<TPRHex> getCorners() {
    List<Hex> oldCorners = CornerAI.getCorners(this).stream()
            .map( h -> this.getBoard().get(h.get(0)).get(h.get(1))).collect(Collectors.toList());
    List<TPRHex> newCorners = new ArrayList<>();
    for (Hex h : oldCorners) {
      newCorners.add(new HexAdapter(h));
    }
    return newCorners;
  }
}
