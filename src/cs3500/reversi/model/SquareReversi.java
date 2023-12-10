package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a SquareReversi game.
 */
public class SquareReversi extends BasicReversi {
  private List<List<Hex>> board;
  /**
   * Constructs a SquareReversi.
   */
  public SquareReversi() {
    super(8);
    this.initCells(8);
    this.initBoard();
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

  /**
   * Constructs a SquareReversi.
   *
   *
   */
  public SquareReversi(SquareReversi model) {
    super(model);
    this.cellStates = new HashMap<>(model.cellStates);
    this.board = new ArrayList<>(model.board);
  }


  @Override
  void constExceptions(int numRows) {
    if (numRows % 2 != 0 || numRows < 6) {
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

  @Override
  protected void placePiece(int x, int y, CellState color) {
    this.cellStates.replace(this.board.get(x).get(y), color);
  }

  private void initCells(int numRows) {
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numRows; j++) {
        this.cellStates.put(new ReversiCell(i, j, 0), CellState.EMPTY);
      }
    }
    this.cellStates.put(new ReversiCell(numRows / 2 - 1,
            numRows / 2 - 1, 0), CellState.BLACK);
    this.cellStates.put(new ReversiCell(numRows / 2,
            numRows / 2, 0), CellState.BLACK);
    this.cellStates.put(new ReversiCell((numRows / 2) - 1,
            numRows / 2, 0), CellState.WHITE);
    this.cellStates.put(new ReversiCell(numRows / 2,
            (numRows / 2) - 1, 0), CellState.WHITE);
  }

  @Override
  public List<Hex> getCellsToFlip(int row, int col) {
    List<Hex> flippedCells = new ArrayList<>();
    int r = row;
    int c = col;
    flippedCells.addAll(getCellsToFlipInDirection(r, c, 0, -1));
    flippedCells.addAll(getCellsToFlipInDirection(r, c, -1, 0));
    flippedCells.addAll(getCellsToFlipInDirection(r, c, 1, 0));
    flippedCells.addAll(getCellsToFlipInDirection(r, c, 0, 1));
    flippedCells.addAll(getCellsToFlipInDirection(r, c, -1, -1));
    flippedCells.addAll(getCellsToFlipInDirection(r, c, -1, 1));
    flippedCells.addAll(getCellsToFlipInDirection(r, c, 1, -1));
    flippedCells.addAll(getCellsToFlipInDirection(r, c, 1, 1));
    return flippedCells;
  }

  /**
   * Gets the cells to flip in a given direction.
   *
   * @param row the row of the cell
   * @param col the column of the cell
   * @param i   the row direction
   * @param j   the column direction
   * @return the cells to flip in a given direction
   */
  private List<Hex> getCellsToFlipInDirection(int row, int col, int i, int j) {
    int r = row + i;
    int c = col + j;
    List<Hex> temp = new ArrayList<>();
    List<Hex> flippedCells = new ArrayList<>();
    int turn = this.getTurn().equals("White") ? 0 : 1;
    CellState currentPlayer = turn == 0 ? CellState.WHITE : CellState.BLACK;
    while (r >= 0 && r < this.getNumRows() && c >= 0 && c < this.getNumRows()) {
      if (isSame(cellStates.get(new ReversiCell(r, c, 0)), CellState.EMPTY)) {
        break;
      } else if (!isSame(cellStates.get(new ReversiCell(r, c, 0)), currentPlayer)) {
        temp.add(new ReversiCell(r, c, 0));
      } else {
        flippedCells.addAll(temp);
        break;
      }
      r += i;
      c += j;
    }
    return flippedCells;
  }


  @Override
  public List<List<Hex>> getBoard() {
    return Collections.unmodifiableList(this.board);
  }

  @Override
  public boolean isLegalMove(int row, int col) {
    return !this.getCellsToFlip(row, col).isEmpty() && this.getColor(this.board.get(row).get(col))
            .equals("EMPTY");
  }

  /**
   * Checks if two cell states are the same.
   *
   * @param state     the first cell state
   * @param cellState the second cell state
   * @return whether the two cell states are the same
   */
  private boolean isSame(CellState state, CellState cellState) {
    return state == cellState;
  }

  @Override
  public int getWhiteScore() {
    int score = 0;
    for (int i = 0; i < this.getNumRows(); i++) {
      for (int j = 0; j < this.getNumRows(); j++) {
        if (this.getColor(this.board.get(i).get(j)).equals("WHITE")) {
          score++;
        }
      }
    }
    return score;
  }

  @Override
  public int getBlackScore() {
    int score = 0;
    for (int i = 0; i < this.getNumRows(); i++) {
      for (int j = 0; j < this.getNumRows(); j++) {
        if (this.getColor(this.board.get(i).get(j)).equals("BLACK")) {
          score++;
        }
      }
    }
    return score;
  }

  @Override
  public boolean isGameOver() {
    return this.getWhiteScore() + this.getBlackScore() == this.getNumRows() * this.getNumRows()
            || this.getWhiteScore() == 0 || this.getBlackScore() == 0 || this.getPasses() == 2;
  }

  @Override
  public ReversiModel copy() {
    return new SquareReversi(this);
  }
}
