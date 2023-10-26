package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class BasicReversi implements ReversiModel {

  private final ArrayList<ArrayList<Hex>> board;
  private final Map<Hex, CellState> cellStates;
  private int turn = 0;
  private final int numRows;
  private int pass = 0;
  private int whiteScore = 3;
  private int blackScore = 3;

  private boolean started = false;

  public BasicReversi(int numRows) {
    if (numRows % 2 == 0 || numRows <= 3) {
      throw new IllegalArgumentException("numRows must be odd and more than 3");
    }
    this.numRows = numRows;
    board = new ArrayList<>();
    for (int i = 0; i < numRows; i++) {
      ArrayList<Hex> row = new ArrayList<>();
      board.add(row);
    }
    cellStates = new HashMap<>();
  }



  private void initCells(int numRows) {

    int N = (numRows - 1) / 2;
    for (int q = -N; q <= N; q++) {
      int r1 = max(-N, -q - N);
      int r2 = min( N, -q + N);
      for (int r = r1; r <= r2; r++) {
        int s = -q - r;
        ReversiCell h = new ReversiCell(r, q, s);
        board.get(q + N).add(h);
        cellStates.put(h, CellState.EMPTY);
      }
    }
  }

  public void startGame() {
    if(started){
      throw new IllegalStateException("Game already started");
    }
    started = true;
    initCells(numRows);
    initColors();
  }

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

  private void checkStarted() {
    if(!started){
      throw new IllegalStateException("Game not started");
    }
  }

  public void move(int x, int y) {
    checkStarted();
    List<Hex> cellsToFlip = getCellsToFlip(x, y);
    if(cellsToFlip.isEmpty()){
      throw new IllegalStateException("Not a legal move");
    }
    placePiece(x, y, turn % 2 == 0 ? CellState.WHITE : CellState.BLACK);
    cellsToFlip.forEach(this::flipPiece);
    this.pass = 0;
    turn++;
    calculateScore();
  }

  public List<Hex> getCellsToFlip(int x, int y) {
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
      List<List<Hex>> lines = validNeighbors.stream().map(n -> c.cellsInDirection(c.getDirection(n),
              c.calcDistance(n,numRows)-1)).collect(Collectors.toList());

    return checkCellsToFlip(lines, color);
  }

  private List<Hex> checkCellsToFlip(List<List<Hex>> lines, CellState color) {
    List<Hex> reversiCells = new ArrayList<>();
    for (int i = 0; i < lines.size(); i++){
      List<Hex> line = lines.get(i);
      List<Hex> result = new ArrayList<>();
      for (Hex reversiCell : line) {
        if (getColor(reversiCell) == color) {
          reversiCells.addAll(result);
          break;
        }else if (getColor(reversiCell) == CellState.EMPTY){
          result.removeAll(result);
        }else{
          result.add(reversiCell);
        }
      }
    }
    return reversiCells;
  }

  public void pass() {
    checkStarted();
    turn++;
    pass++;
    calculateScore();
  }

  public boolean isGameOver() {
    boolean legalMoveFound = false;
    for (int i = 0; i < board.size(); i++) {
      for (int j = 0; j < board.get(i).size(); j++) {
        try {
          if (getCellsToFlip(i, j).size() > 0) {
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

  public List<List<Hex>> getBoard() {
    return Collections.unmodifiableList(board);
  }

  public CellState getColor(Hex h) {
    return cellStates.get(h);
  }

  private void placePiece(int x, int y, CellState color) {
    cellStates.put(board.get(x).get(y), color);
  }

  private void initColors() {
    cellStates.replace(new ReversiCell(0,-1,1), CellState.BLACK);
    cellStates.replace(new ReversiCell(1,-1,0), CellState.WHITE);
    cellStates.replace(new ReversiCell(-1,0,1), CellState.WHITE);
    cellStates.replace(new ReversiCell(-1,1,0), CellState.BLACK);
    cellStates.replace(new ReversiCell(0,1,-1), CellState.WHITE);
    cellStates.replace(new ReversiCell(1,0,-1), CellState.BLACK);
  }

  private boolean isOpposite(CellState c, Hex d) {
    if (c == CellState.WHITE) {
      return cellStates.get(d) == CellState.BLACK;
    } else if (c == CellState.BLACK) {
      return cellStates.get(d) == CellState.WHITE;
    } else {
      return false;
    }
  }

  private void flipPiece(Hex c){
    cellStates.replace(c, cellStates.get(c) == CellState.WHITE ? CellState.BLACK : CellState.WHITE);
  }

  public String getTurn() {
    if (turn % 2 == 0) {
      return "White";
    } else {
      return "Black";
    }
  }
}