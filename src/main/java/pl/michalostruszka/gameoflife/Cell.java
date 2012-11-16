package pl.michalostruszka.gameoflife;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: mostruszka
 * Date: 16.11.12
 * Time: 10:13
 * To change this template use File | Settings | File Templates.
 */
public class Cell {
  private final int x;
  private final int y;
  private boolean isLive;

  private Cell(int x, int y) {
    this.x = x;
    this.y = y;
    this.isLive = true;
  }

  private Cell(Cell fromCell) {
    this.x = fromCell.x;
    this.y = fromCell.y;
    this.isLive = fromCell.isLive;
  }

  public static Cell at(int x, int y) {
    return new Cell(x, y);
  }

  public Cell nextState(GameBoard board) {
    Cell nextState = new Cell(this);
    int count = board.countLiveNeighboursOf(this);
    if((count == 2 && this.isLive())|| count == 3) {
      nextState.isLive = true;
    } else {
      nextState.isLive = false;
    }
    return nextState;
  }

  public Set<Cell> neighbours() {
    Set<Cell> expectedNeighbours = new HashSet<Cell>();
    int[][] neighboursRelativeCoords = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    for (int[] relativeCoord : neighboursRelativeCoords) {
      Cell cell = Cell.at(this.x + relativeCoord[0], this.y + relativeCoord[1]);
      cell.isLive = false;
      expectedNeighbours.add(cell);
    }
    return expectedNeighbours;
  }

  public boolean isLive() {
    return isLive;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Cell cell = (Cell) o;

    if (x != cell.x) return false;
    if (y != cell.y) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
  }

}
