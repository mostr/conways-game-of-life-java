package pl.michalostruszka.gameoflife;

import java.math.BigDecimal;

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

  public static Cell atPosition(int x, int y) {
    return new Cell(x, y);
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

  public Cell evaluate(GameState state) {
    Cell nextState = new Cell(this);
    int count = state.countLiveNeighboursOf(this);
    if(count == 2 || count == 3) {
      nextState.isLive = true;
    } else {
      nextState.isLive = false;
    }
    return nextState;
  }

  public boolean isLive() {
    return isLive;
  }
}
