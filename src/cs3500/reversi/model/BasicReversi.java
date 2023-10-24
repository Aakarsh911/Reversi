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

  private final ArrayList<ArrayList<ReversiCell>> board;
  private final Map<ReversiCell, CellState> cellStates;

  private int turn;

  private final int numRows;

  public BasicReversi(int numRows) {
    if (numRows % 2 == 0 || numRows < 3) {
      throw new IllegalArgumentException("numRows must be odd and less than 3");
    }
    this.numRows = numRows;
    board = new ArrayList<>();
    for (int i = 0; i < numRows; i++) {
      ArrayList<ReversiCell> row = new ArrayList<>();
      board.add(row);
    }
    cellStates = new HashMap<>();
    turn = 0;
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
    initCells(numRows);
    initColors();
  }

  public void move(int x, int y) {
    List<ReversiCell> cellsToFlip = isLegalMove(x, y);
    if(cellsToFlip.isEmpty()){
      throw new IllegalStateException("Not a legal move");
    }
    placePiece(x, y, turn % 2 == 0 ? CellState.WHITE : CellState.BLACK);
    cellsToFlip.forEach(this::flipPiece);
    turn++;
  }

  public List<ReversiCell> isLegalMove(int x, int y) {
    List<ReversiCell> reversiCells = new ArrayList<>();
    if (getColor(board.get(x).get(y)) != CellState.EMPTY) {
      throw new IllegalStateException("Cell is not empty");
    }
    ReversiCell c = board.get(x).get(y);
    CellState color = turn % 2 == 0 ? CellState.WHITE : CellState.BLACK;
    List<ReversiCell> validNeighbors = c.neighbors().stream().filter(n -> isOpposite(color, n))
            .collect(Collectors.toList());
    if (validNeighbors.isEmpty()) {
      throw new IllegalStateException("No valid neighbors");
    }else{
      System.out.println(validNeighbors);
      List<List<ReversiCell>> lines = validNeighbors.stream().map(n -> c.cellsInDirection(c.getDirection(n),c.calcDistance(n,numRows)-1)).collect(Collectors.toList());
      System.out.println(lines);
      for (int i = 0; i < lines.size(); i++){
        List<ReversiCell> line = lines.get(i);
        List<ReversiCell> result = new ArrayList<>();
        for (ReversiCell reversiCell : line) {
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
    }
    return reversiCells;
  }

  public void pass() {
    turn++;
  }

  public boolean isGameOver() {
    return false;
  }

  public List<List<ReversiCell>> getBoard() {
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

  private boolean isOpposite(ReversiCell c, ReversiCell d) {
    if (cellStates.get(c) == CellState.WHITE) {
      return cellStates.get(d) == CellState.BLACK;
    } else if (cellStates.get(c) == CellState.BLACK) {
      return cellStates.get(d) == CellState.WHITE;
    } else {
      return false;
    }
  }
  private boolean isOpposite(CellState c, ReversiCell d) {
    if (c == CellState.WHITE) {
      return cellStates.get(d) == CellState.BLACK;
    } else if (c == CellState.BLACK) {
      return cellStates.get(d) == CellState.WHITE;
    } else {
      return false;
    }
  }

  private void flipPiece(ReversiCell c){
    cellStates.replace(c, cellStates.get(c) == CellState.WHITE ? CellState.BLACK : CellState.WHITE);
  }

  public Map<ReversiCell, CellState> getCellStates() {
    return cellStates;
  }
}
