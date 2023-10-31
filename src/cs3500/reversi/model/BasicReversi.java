package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Represents a basic implementation of the ReversiModel interface.
 */
public class BasicReversi implements ReversiModel {

  private final ArrayList<ArrayList<Hex>> board; // list of rows
  private final Map<Hex, CellState> cellStates; // map of cells to their states
  private int turn = 0; // which player's turn it is
  private int pass = 0; // number of consecutive passes
  private int whiteScore = 3; // number of white pieces on the board
  private int blackScore = 3; // number of black pieces on the board

  /**
   * Constructs a BasicReversi object.
   * @param numRows the number of rows in the board
   */
  public BasicReversi(int numRows) {
    if (numRows % 2 == 0 || numRows <= 3) {
      throw new IllegalArgumentException("numRows must be odd and more than 3");
    }
    // number of rows in the board
    board = new ArrayList<>();
    for (int i = 0; i < numRows; i++) {
      ArrayList<Hex> row = new ArrayList<>();
      board.add(row);
    }
    cellStates = new HashMap<>();
    initCells(numRows);
    initColors();
  }

  /**
   * Constructs a BasicReversi object with 7 rows.
   */
  public BasicReversi() {
    int numRows = 7;
    // number of rows in the board
    board = new ArrayList<>();
    for (int i = 0; i < numRows; i++) {
      ArrayList<Hex> row = new ArrayList<>();
      board.add(row);
    }
    cellStates = new HashMap<>();
    initCells(numRows);
    initColors();
  }

  /**
   * Initializes the cells in the board.
   * @param numRows the number of rows in the board
   */
  private void initCells(int numRows) {
    int n = (numRows - 1) / 2;
    for (int q = -n; q <= n; q++) {
      int r1 = max(-n, -q - n);
      int r2 = min( n, -q + n);
      for (int r = r1; r <= r2; r++) {
        int s = -q - r;
        ReversiCell h = new ReversiCell(r, q, s);
        board.get(q + n).add(h);
        cellStates.put(h, CellState.EMPTY);
      }
    }
  }

  @Override
  public void calculateScore() {
    int white = 0;
    int black = 0;
    for (Hex h : cellStates.keySet()) {
      if (cellStates.get(h) == CellState.WHITE) {
        white++;
      }
      else if (cellStates.get(h) == CellState.BLACK) {
        black++;
      }
    }
    whiteScore = white;
    blackScore = black;
  }

  @Override
  public int getWhiteScore() {
    return this.whiteScore;
  }

  @Override
  public int getBlackScore() {
    return this.blackScore;
  }

  @Override
  public void move(int x, int y) {
    List<Hex> cellsToFlip = getCellsToFlip(x, y);
    if (cellsToFlip.isEmpty()) {
      throw new IllegalStateException("Not a legal move");
    }
    placePiece(x, y, turn % 2 == 0 ? CellState.WHITE : CellState.BLACK);
    cellsToFlip.forEach(this::flipPiece);
    this.pass = 0;
    turn++;
    calculateScore();
  }

  /**
   * Gets the cells to flip for the given cell.
   * @param x the x coordinate of the cell
   * @param y the y coordinate of the cell
   * @return the cells to flip
   */
  List<Hex> getCellsToFlip(int x, int y) {
    if (getColor(board.get(x).get(y)) != CellState.EMPTY) {
      throw new IllegalStateException("Cell is not empty");
    }
    Hex c = board.get(x).get(y);
    CellState color = turn % 2 == 0 ? CellState.WHITE : CellState.BLACK;
    List<Hex> validNeighbors = c.neighbors().stream().filter(n -> isOpposite(color, n))
            .collect(Collectors.toList());
    if (validNeighbors.isEmpty()) {
      throw new IllegalStateException("No valid neighbors");
    }
    List<List<Hex>> lines = validNeighbors.stream().map(n -> c.cellsInDirection(c.getDirection(n)
            , cellStates)).collect(Collectors.toList());
    return checkCellsToFlip(lines, color);
  }

  /**
   * Checks the cells to flip in the given lines.
   * @param lines the lines to check
   * @param color the color of the player
   * @return the cells to flip
   */
  private List<Hex> checkCellsToFlip(List<List<Hex>> lines, CellState color) {
    List<Hex> reversiCells = new ArrayList<>();
    for (List<Hex> line : lines) {
      List<Hex> result = new ArrayList<>();
      for (Hex reversiCell : line) {
        if (getColor(reversiCell) == color) {
          reversiCells.addAll(result);
          break;
        } else if (getColor(reversiCell) == CellState.EMPTY) {
          result.removeAll(result);
        } else {
          result.add(reversiCell);
        }
      }
    }
    return reversiCells;
  }

  @Override
  public void pass() {
    turn++;
    pass++;
    calculateScore();
  }

  @Override
  public boolean isGameOver() {
    boolean legalMoveFound = false;
    for (int i = 0; i < board.size(); i++) {
      for (int j = 0; j < board.get(i).size(); j++) {
        try {
          if (!getCellsToFlip(i, j).isEmpty()) {
            legalMoveFound = true;
            break;
          }
        } catch (IllegalStateException e) {
          // do nothing
        }
      }
    }
    if (!legalMoveFound) {
      pass();
      calculateScore();
    }
    int numCells = 0;
    for (ArrayList<Hex> hexes : board) {
      numCells += hexes.size();
    }
    return pass == 2 || whiteScore == 0 || blackScore == 0
            || numCells == whiteScore + blackScore;
  }

  /**
   * Gets the board.
   * @return the board
   */
  public List<List<Hex>> getBoard() {
    return Collections.unmodifiableList(board);
  }

  /**
   * Gets the state of a given cell.
   * @param h the cell to get the state of
   * @return the state of the given cell
   */
  public CellState getColor(Hex h) {
    return cellStates.get(h);
  }

  /**
   * Places a piece on the board at x and y coordinates.
   * @param x the x coordinate of the piece
   * @param y the y coordinate of the piece
   * @param color the color of the piece
   */
  private void placePiece(int x, int y, CellState color) {
    cellStates.put(board.get(x).get(y), color);
  }

  /**
   * Initializes the colors of the cells.
   */
  private void initColors() {
    cellStates.replace(new ReversiCell(0,-1,1), CellState.BLACK);
    cellStates.replace(new ReversiCell(1,-1,0), CellState.WHITE);
    cellStates.replace(new ReversiCell(-1,0,1), CellState.WHITE);
    cellStates.replace(new ReversiCell(-1,1,0), CellState.BLACK);
    cellStates.replace(new ReversiCell(0,1,-1), CellState.WHITE);
    cellStates.replace(new ReversiCell(1,0,-1), CellState.BLACK);
  }

  /**
   * Checks if the given cell is the opposite color of the given color.
   * @param c the color to check
   * @param d the direction to check
   * @return whether the given cell is the opposite color of the given direction
   */
  private boolean isOpposite(CellState c, Hex d) {
    if (c == CellState.WHITE) {
      return cellStates.get(d) == CellState.BLACK;
    } else if (c == CellState.BLACK) {
      return cellStates.get(d) == CellState.WHITE;
    } else {
      return false;
    }
  }

  /**
   * Flips the piece at the given cell.
   * @param c the cell to flip
   */
  private void flipPiece(Hex c) {
    cellStates.replace(c, cellStates.get(c) == CellState.WHITE ? CellState.BLACK : CellState.WHITE);
  }

  @Override
  public String getTurn() {
    if (turn % 2 == 0) {
      return "White";
    } else {
      return "Black";
    }
  }
}