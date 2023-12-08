package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SquareReversi extends BasicReversi {

  private List<List<Hex>> board;
  /**
   * Constructs a SquareReversi.
   */
  public SquareReversi() {
    super();
  }

  /**
   * Constructs a SquareReversi.
   *
   * @param numRows the number of rows in the board
   */
  public SquareReversi(int numRows) {
    super(numRows);
    this.initCells(numRows);
    this.initBoard();
  }

  @Override
  void constExceptions(int numRows) {
    if (numRows % 2 != 0) {
      throw new IllegalArgumentException("Number of rows must be even.");
    }
  }

  private void initBoard() {
    this.board = new ArrayList<>();
    for (int i = 0; i < this.getNumRows(); i++) {
      List<Hex> row = new ArrayList<>();
      for (int j = 0; j < this.getNumRows(); j++) {
        row.add(new ReversiCell(i, j, 0));
      }
      this.board.add(row);
    }
  }

  private void initCells(int numRows) {
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numRows; j++) {
        this.cellStates.put(new ReversiCell(i, j, 0), CellState.EMPTY);
      }
    }
    this.cellStates.put(new ReversiCell(numRows / 2 + 1, numRows / 2 + 1, 0), CellState.BLACK);
    this.cellStates.put(new ReversiCell(numRows / 2, numRows / 2, 0), CellState.BLACK);
    this.cellStates.put(new ReversiCell(numRows / 2 + 1, numRows / 2, 0), CellState.WHITE);
    this.cellStates.put(new ReversiCell(numRows / 2, numRows / 2 + 1, 0), CellState.WHITE);
  }

  @Override
  public List<Hex> getCellsToFlip(int row, int col) {
    List<Hex> flippedCells = new ArrayList<>();
    CellState currentPlayer = cellStates.get(new ReversiCell(row, col, 0));

    // Check in all eight directions for potential flips
    int[] directions = {-1, 0, 1};
    for (int dRow : directions) {
      for (int dCol : directions) {
        if (dRow == 0 && dCol == 0) {
          continue; // Skip the current cell
        }

        List<Hex> cellsInDirection = new ArrayList<>();
        int r = row + dRow;
        int c = col + dCol;

        // Move in the current direction until an edge is reached or an opponent's piece is found
        while (isValidCell(r, c)
                && cellStates.containsKey(new ReversiCell(r, c, 0))
                && cellStates.get(new ReversiCell(r, c, 0)) != CellState.EMPTY
                && cellStates.get(new ReversiCell(r, c, 0)) != currentPlayer) {
          cellsInDirection.add(new ReversiCell(r, c, 0));
          r += dRow;
          c += dCol;
        }

        // If the last piece in the direction is the player's own piece, flip the pieces in between
        if (this.isValidCell(r, c)
                && cellStates.containsKey(new ReversiCell(r, c, 0))
                && cellStates.get(new ReversiCell(r, c, 0)) == currentPlayer) {
          flippedCells.addAll(cellsInDirection);
        }
      }
    }

    return flippedCells;
  }

  private boolean isValidCell(int row, int col) {
    return row >= 0 && row < this.getNumRows() && col >= 0 && col < this.getNumRows();
  }

  @Override
  public List<List<Hex>> getBoard() {
    return this.board;
  }

  @Override
  public boolean isLegalMove(int row, int col) {
    return this.getCellsToFlip(row, col).size() > 0;
  }

  private boolean isOpposite(CellState state, CellState cellState) {
    return state != cellState && state != CellState.EMPTY;
  }
}
