package pl.michalostruszka.gameoflife;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: mostruszka
 * Date: 16.11.12
 * Time: 09:55
 * To change this template use File | Settings | File Templates.
 */
public class GameState {

  private Set<Cell> cells = new HashSet<Cell>();

  public void setLiveCellAt(int x, int y) {
    cells.add(Cell.atPosition(x, y));
  }


  @Override
  public String toString() {
    StringBuffer buffer = new StringBuffer("[");
    for (Cell cell : cells) {
      buffer.append(cell + ", ");
    }
    return buffer + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GameState gameState = (GameState) o;

    if(cells.size() == gameState.cells.size() && cells.containsAll(gameState.cells)) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return cells != null ? cells.hashCode() : 0;
  }

  public int countLiveNeighboursOf(Cell cell) {
    return 0;
  }
}
