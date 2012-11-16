package pl.michalostruszka.gameoflife;

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

  private Cell(int x, int y) {
    this.x = x;
    this.y = y;
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
}
