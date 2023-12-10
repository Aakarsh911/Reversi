package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.provider.model.TPRHex;

/**
 * Adapts a Hex to a TPRHex.
 */
public class BoardPieceAdapter extends ReversiCell implements TPRHex {

  /**
   * Constructs a HexAdapter.
   *
   * @param q the q coordinate
   * @param r the r coordinate
   * @param s the s coordinate
   */
  public BoardPieceAdapter(int q, int r, int s) {
    super(q, r, s);
  }

  /**
   * Constructs a HexAdapter.
   *
   * @param boardPiece the hex to adapt
   */
  public BoardPieceAdapter(BoardPiece boardPiece) {
    super(boardPiece.getQ(), boardPiece.getR(), boardPiece.getS());
  }

  /**
   * Constructs a HexAdapter.
   *
   * @param hex the hex to adapt
   */
  public BoardPieceAdapter(TPRHex hex) {
    super(hex.getQ(), hex.getR(), hex.getS());
  }

  @Override
  public List<TPRHex> getNeighborDirections() {
    List<TPRHex> neighborDirections = new ArrayList<>();
    neighborDirections.add(new BoardPieceAdapter(1, 0, -1));
    neighborDirections.add(new BoardPieceAdapter(1, -1, 0));
    neighborDirections.add(new BoardPieceAdapter(0, -1, 1));
    neighborDirections.add(new BoardPieceAdapter(-1, 0, 1));
    neighborDirections.add(new BoardPieceAdapter(-1, 1, 0));
    neighborDirections.add(new BoardPieceAdapter(0, 1, -1));
    return neighborDirections;
  }

  @Override
  public List<TPRHex> getNeighbors() {
    List<BoardPiece> neighbors = super.neighbors();
    List<TPRHex> newNeighbors = new ArrayList<>();
    for (BoardPiece h : neighbors) {
      newNeighbors.add(new BoardPieceAdapter(h));
    }
    return newNeighbors;
  }

  @Override
  public TPRHex subtract(TPRHex hex) {
    return new BoardPieceAdapter(super.getDirection(new BoardPieceAdapter(hex)));
  }

  @Override
  public TPRHex add(TPRHex hex) {
    return new BoardPieceAdapter(this.getQ() + hex.getQ(), this.getR() + hex.getR(),
            this.getS() + hex.getS());
  }
}
