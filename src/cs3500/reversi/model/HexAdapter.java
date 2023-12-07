package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.provider.model.TPRHex;

/**
 * Adapts a Hex to a TPRHex.
 */
public class HexAdapter extends ReversiCell implements TPRHex {

  /**
   * Constructs a HexAdapter.
   *
   * @param q the q coordinate
   * @param r the r coordinate
   * @param s the s coordinate
   */
  public HexAdapter(int q, int r, int s) {
    super(q, r, s);
  }

  /**
   * Constructs a HexAdapter.
   *
   * @param hex the hex to adapt
   */
  public HexAdapter(Hex hex) {
    super(hex.getQ(), hex.getR(), hex.getS());
  }

  /**
   * Constructs a HexAdapter.
   *
   * @param hex the hex to adapt
   */
  public HexAdapter(TPRHex hex) {
    super(hex.getQ(), hex.getR(), hex.getS());
  }

  @Override
  public List<TPRHex> getNeighborDirections() {
    List<TPRHex> neighborDirections = new ArrayList<>();
    neighborDirections.add(new HexAdapter(1, 0, -1));
    neighborDirections.add(new HexAdapter(1, -1, 0));
    neighborDirections.add(new HexAdapter(0, -1, 1));
    neighborDirections.add(new HexAdapter(-1, 0, 1));
    neighborDirections.add(new HexAdapter(-1, 1, 0));
    neighborDirections.add(new HexAdapter(0, 1, -1));
    return neighborDirections;
  }

  @Override
  public List<TPRHex> getNeighbors() {
    List<Hex> neighbors = super.neighbors();
    List<TPRHex> newNeighbors = new ArrayList<>();
    for (Hex h : neighbors) {
      newNeighbors.add(new HexAdapter(h));
    }
    return newNeighbors;
  }

  @Override
  public TPRHex subtract(TPRHex hex) {
    return new HexAdapter(super.getDirection(new HexAdapter(hex)));
  }

  @Override
  public TPRHex add(TPRHex hex) {
    return new HexAdapter(this.getQ() + hex.getQ(), this.getR() + hex.getR(),
            this.getS() + hex.getS());
  }
}
