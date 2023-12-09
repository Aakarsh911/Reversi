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
    this.cellStates.put(new ReversiCell(numRows / 2 - 1, numRows / 2 - 1, 0), CellState.BLACK);
    this.cellStates.put(new ReversiCell(numRows / 2, numRows / 2, 0), CellState.BLACK);
    this.cellStates.put(new ReversiCell((numRows / 2) - 1, numRows / 2, 0), CellState.WHITE);
    this.cellStates.put(new ReversiCell(numRows / 2, (numRows / 2) - 1, 0), CellState.WHITE);
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
    System.out.println("done");
    return flippedCells;
  }

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
    if (currentPlayer == CellState.WHITE) {
      currentPlayer = CellState.BLACK;
    } else {
      currentPlayer = CellState.WHITE;
    }
    if (flippedCells.size() > 0) {
      flippedCells.add(new ReversiCell(row, col, 0));
      this.cellStates.put(new ReversiCell(row, col, 0), currentPlayer);
    }
    return flippedCells;
  }


  @Override
  public List<List<Hex>> getBoard() {
    return this.board;
  }

  @Override
  public boolean isLegalMove(int row, int col) {
    return this.getCellsToFlip(row, col).size() > 0;
  }

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
            || this.getWhiteScore() == 0 || this.getBlackScore() == 0;
  }
}
