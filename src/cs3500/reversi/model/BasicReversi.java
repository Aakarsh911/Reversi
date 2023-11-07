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

  /**
   * INVARIANT: board.size() is always odd and more than 3
   */
  private final ArrayList<ArrayList<Hex>> board; // list of rows
  /**
   * INVARIANT: cellStates.size() is always equal to the total number of cells in board
   */
  private final Map<Hex, CellState> cellStates; // map of cells to their states
  private int turn = 0; // which player's turn it is
  private int pass = 0; // number of consecutive passes
  private int whiteScore = 3; // number of white pieces on the board
  private int blackScore = 3; // number of black pieces on the board
  private int numRows = 7; // number of rows in the board

  private final Player[] players = new Player[2];

  /**
   * Constructs a BasicReversi object.
   * Ensures numRows is odd and more than 3.
   * Adds cells to the board and adds them to cellStates simultaneously to ensure invariant.
   *
   * @param numRows the number of rows in the board
   * @throws IllegalArgumentException if numRows is even or less than or equal to 3
   */
  public BasicReversi(int numRows, int numHumans) {
    if (numRows % 2 == 0 || numRows <= 3) {
      throw new IllegalArgumentException("numRows must be odd and more than 3");
    }
    switch (numHumans) {
      case 0: {
        players[0] = new AIPlayer();
        players[1] = new AIPlayer();
        break;
      }
      case 1:
        players[0] = new Person();
        players[1] = new AIPlayer();
        break;
      case 2:
        players[0] = new Person();
        players[1] = new Person();
        break;
      default:
        throw new IllegalArgumentException("numHumans must be 0, 1, or 2");
    }
    this.numRows = numRows;
    board = new ArrayList<>();
    // rowNum here represents the row number
    for (int rowNum = 0; rowNum < numRows; rowNum++) {
      ArrayList<Hex> row = new ArrayList<>();
      board.add(row);
    }
    cellStates = new HashMap<>();
    initCells(numRows);
    initColors();
  }

  public BasicReversi(int numRows) {
    if (numRows % 2 == 0 || numRows <= 3) {
      throw new IllegalArgumentException("numRows must be odd and more than 3");
    }
    this.numRows = numRows;
    board = new ArrayList<>();
    // rowNum here represents the row number
    for (int rowNum = 0; rowNum < numRows; rowNum++) {
      ArrayList<Hex> row = new ArrayList<>();
      board.add(row);
    }
    cellStates = new HashMap<>();
    initCells(numRows);
    initColors();
  }

  /**
   * Constructs a BasicReversi object with 7 rows.
   * Ensures numRows is odd and more than 3.
   * Adds cells to the board and adds them to cellStates simultaneously to ensure invariant.
   *
   * @throws IllegalArgumentException if numRows is even or less than or equal to 3
   */
  public BasicReversi() {
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
   *
   * @param numRows the number of rows in the board
   */
  private void initCells(int numRows) {
    int n = (numRows - 1) / 2; // n here represents the radius of the board
    for (int r = -n; r <= n; r++) { // r here represents the r value in the cube coordinates
      int r1 = max(-n, -r - n); // r1 represents the max between -n and -r - n
      int r2 = min(n, -r + n); // r2 represents the min between n and -r + n
      for (int q = r1; q <= r2; q++) { // q here represents the q value in the cube coordinates
        int s = -r - q; // s here represents the s value in the cube coordinates
        ReversiCell h = new ReversiCell(q, r, s); // h here represents the hex
        board.get(r + n).add(h);
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
      } else if (cellStates.get(h) == CellState.BLACK) {
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
    this.turn++;
    this.calculateScore();
    boolean legalMoveFound = false;
    for (int rowNum = 0; rowNum < board.size(); rowNum++) {
      for (int colNum = 0; colNum < board.get(rowNum).size(); colNum++) {
        try {
          if (!getCellsToFlip(rowNum, colNum).isEmpty()) {
            legalMoveFound = true;
            break;
          }
        } catch (IllegalStateException e) {
          continue;
        }
      }
    }
    if (!legalMoveFound) {
      this.pass();
      this.calculateScore();
    }
  }

  /**
   * Gets the cells to flip for the given cell.
   *
   * @param x the row of the cell
   * @param y the column of the cell
   * @return the cells to flip
   */
  List<Hex> getCellsToFlip(int x, int y) {
    if (!getColor(board.get(x).get(y)).equals("EMPTY")) {
      throw new IllegalStateException("Cell is not empty");
    }
    Hex h = board.get(x).get(y);
    CellState color = turn % 2 == 0 ? CellState.WHITE : CellState.BLACK;
    List<Hex> validNeighbors = h.neighbors().stream().filter(n -> isOpposite(color, n))
            .collect(Collectors.toList());
    if (validNeighbors.isEmpty()) {
      throw new IllegalStateException("No valid neighbors");
    }
    List<List<Hex>> lines = validNeighbors.stream().map(n -> h.cellsInDirection(h.getDirection(n)
            , cellStates)).collect(Collectors.toList());
    return checkCellsToFlip(lines, color);
  }

  /**
   * Checks the cells to flip in the given lines.
   *
   * @param lines the lines to check
   * @param color the color of the player
   * @return the cells to flip
   */
  private List<Hex> checkCellsToFlip(List<List<Hex>> lines, CellState color) {
    List<Hex> reversiCells = new ArrayList<>();
    for (List<Hex> line : lines) {
      List<Hex> result = new ArrayList<>();
      for (Hex reversiCell : line) {
        if (getColor(reversiCell) == color.toString()) {
          reversiCells.addAll(result);
          break;
        } else if (getColor(reversiCell) == "EMPTY") {
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
    this.turn++;
    this.pass++;
    calculateScore();
  }

  public ArrayList<Integer> bestMoveWithMostFlips() {
    ArrayList<Integer> bestMove = new ArrayList<>();
    int bestScore = Integer.MIN_VALUE;
    for (int rowNum = 0; rowNum < board.size(); rowNum++) {
      for (int colNum = 0; colNum < board.get(rowNum).size(); colNum++) {
        try {
          if (getCellsToFlip(rowNum, colNum).size() > bestScore
                  && !getCellsToFlip(rowNum, colNum).isEmpty()) {
            bestMove.removeAll(bestMove);
            bestMove.add(rowNum);
            bestMove.add(colNum);
            bestScore = getCellsToFlip(rowNum, colNum).size();
          }
        } catch (IllegalStateException e) {
          continue;
        }
      }
    }
    return bestMove;
  }

  public ArrayList<Integer> maxMinSmartAi() {
    // generate list of all possible moves
    ArrayList<ArrayList<Integer>> possibleMoves = new ArrayList<>();
    for (int rowNum = 0; rowNum < board.size(); rowNum++) {
      for (int colNum = 0; colNum < board.get(rowNum).size(); colNum++) {
        try {
          if (!getCellsToFlip(rowNum, colNum).isEmpty()) {
            ArrayList<Integer> move = new ArrayList<>();
            move.add(rowNum);
            move.add(colNum);
            possibleMoves.add(move);
          }
        } catch (IllegalStateException e) {
          continue;
        }
      }
    }
    BasicReversi copy = new BasicReversi(this.numRows,1);
    copy.board.clear();
    for (int rowNum = 0; rowNum < board.size(); rowNum++) {
      ArrayList<Hex> row = new ArrayList<>();
      copy.board.add(row);
    }
    copy.cellStates.clear();
    copy.initCells(this.numRows);
    copy.initColors();

    // make the move on the copy and calculate all the next possible moves and make a mao for each move
    Map<ArrayList<Integer>, ArrayList<ArrayList<Integer>>> possibleMovesMap = new HashMap<>();
    for (ArrayList<Integer> move : possibleMoves) {
      try {
        copy.move(move.get(0), move.get(1));
      } catch (Exception e) {
        continue;
      }
      ArrayList<ArrayList<Integer>> nextPossibleMoves = new ArrayList<>();
      for (int rowNum = 0; rowNum < copy.board.size(); rowNum++) {
        for (int colNum = 0; colNum < copy.board.get(rowNum).size(); colNum++) {
          try {
            if (!copy.getCellsToFlip(rowNum, colNum).isEmpty()) {
              ArrayList<Integer> nextMove = new ArrayList<>();
              nextMove.add(rowNum);
              nextMove.add(colNum);
              nextPossibleMoves.add(nextMove);
            }
          } catch (IllegalStateException e) {
            continue;
          }
        }
      }
      possibleMovesMap.put(move, nextPossibleMoves);
      copy.board.clear();
      for (int rowNum = 0; rowNum < board.size(); rowNum++) {
        ArrayList<Hex> row = new ArrayList<>();
        copy.board.add(row);
      }
      copy.cellStates.clear();
      copy.initCells(this.numRows);
      copy.initColors();
    }
    // find the move that whose maximum number of flips of the vlaue in the mao is the minimum out of all values
    int min = Integer.MAX_VALUE;
    for (ArrayList<Integer> move : possibleMovesMap.keySet()) {
      int max = Integer.MIN_VALUE;
      for (ArrayList<Integer> nextMove : possibleMovesMap.get(move)) {
        try {
          if (getCellsToFlip(nextMove.get(0), nextMove.get(1)).size() > max) {
            max = getCellsToFlip(nextMove.get(0), nextMove.get(1)).size();
          }
        } catch (IllegalStateException e) {
          continue;
        }
      }
      if (max < min) {
        min = max;
      }
    }

    // find the move that has the minimum number of flips
    ArrayList<Integer> bestMove = new ArrayList<>();
    for (ArrayList<Integer> move : possibleMovesMap.keySet()) {
      int max = Integer.MIN_VALUE;
      for (ArrayList<Integer> nextMove : possibleMovesMap.get(move)) {
        try {
          if (getCellsToFlip(nextMove.get(0), nextMove.get(1)).size() > max) {
            max = getCellsToFlip(nextMove.get(0), nextMove.get(1)).size();
          }
        } catch (IllegalStateException e) {
          continue;
        }
      }
      if (max == min) {
        bestMove = move;
        break;
      }
    }
    if (bestMove.isEmpty()) {
      bestMove = this.bestMoveWithMostFlips();
    }
    return bestMove;
  }

  @Override
  public boolean isGameOver() {
    int numCells = 0;
    boolean legalMoveFound = false;
    for (int rowNum = 0; rowNum < board.size(); rowNum++) {
      for (int colNum = 0; colNum < board.get(rowNum).size(); colNum++) {
        try {
          if (!getCellsToFlip(rowNum, colNum).isEmpty()) {
            legalMoveFound = true;
            break;
          }
        } catch (IllegalStateException e) {
          continue;
        }
      }
    }
    if (!legalMoveFound && pass == 1) {
      this.pass();
      this.calculateScore();
      return true;
    }
    for (ArrayList<Hex> hexes : board) {
      numCells += hexes.size();
    }
    return pass == 2 || whiteScore == 0 || blackScore == 0
            || numCells == whiteScore + blackScore;
  }

  /**
   * Gets the board.
   *
   * @return the board
   */
  public List<List<Hex>> getBoard() {
    return Collections.unmodifiableList(board);
  }

  /**
   * Gets the state of a given cell.
   *
   * @param h the cell to get the state of
   * @return the state of the given cell
   */
  public String getColor(Hex h) {
    return this.cellStates.get(h).toString();
  }

  /**
   * Places a piece on the board at x and y coordinates.
   * Uses and maintains the invariant that all the elements in cellStates are in board.
   * This is done by using the .replace() method in the Map interface.
   *
   * @param x     the x coordinate of the piece
   * @param y     the y coordinate of the piece
   * @param color the color of the piece
   */
  private void placePiece(int x, int y, CellState color) {
    this.cellStates.replace(this.board.get(x).get(y), color);
  }

  /**
   * Initializes the colors of the cells.
   * Uses and maintains the invariant that all the elements in cellStates are in board.
   * This is done by using the .replace() method in the Map interface.
   */
  private void initColors() {
    this.cellStates.replace(new ReversiCell(0, -1, 1), CellState.BLACK);
    this.cellStates.replace(new ReversiCell(1, -1, 0), CellState.WHITE);
    this.cellStates.replace(new ReversiCell(-1, 0, 1), CellState.WHITE);
    this.cellStates.replace(new ReversiCell(-1, 1, 0), CellState.BLACK);
    this.cellStates.replace(new ReversiCell(0, 1, -1), CellState.WHITE);
    this.cellStates.replace(new ReversiCell(1, 0, -1), CellState.BLACK);
  }

  /**
   * Checks if the given cell is the opposite color of the given color.
   *
   * @param state the color to check
   * @param hex   the direction to check
   * @return whether the given cell is the opposite color of the given direction
   */
  private boolean isOpposite(CellState state, Hex hex) {
    if (state == CellState.WHITE) {
      return cellStates.get(hex) == CellState.BLACK;
    } else if (state == CellState.BLACK) {
      return cellStates.get(hex) == CellState.WHITE;
    } else {
      return false;
    }
  }

  /**
   * Flips the piece at the given cell.
   *
   * @param h the hex to flip
   */
  private void flipPiece(Hex h) {
    this.cellStates.replace(h, cellStates.get(h)
            == CellState.WHITE ? CellState.BLACK : CellState.WHITE);
  }

  @Override
  public String getTurn() {
    if (this.turn % 2 == 0) {
      return "White";
    } else {
      return "Black";
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof BasicReversi) {
      BasicReversi r = (BasicReversi) o;
      return this.board.equals(r.board) && this.cellStates.equals(r.cellStates)
              && this.turn == r.turn && this.pass == r.pass && this.whiteScore == r.whiteScore
              && this.blackScore == r.blackScore;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.board.hashCode() + this.cellStates.hashCode() + this.turn + this.pass
            + this.whiteScore + this.blackScore;
  }

  @Override
  public String toString() {
    return "\nTurn: " + this.turn
            + "\nWhite Score: " + this.whiteScore + "\nBlack Score: "
            + this.blackScore;
  }
}