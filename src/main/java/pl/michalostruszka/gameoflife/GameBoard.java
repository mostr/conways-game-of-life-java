package pl.michalostruszka.gameoflife;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: mostruszka
 * Date: 16.11.12
 * Time: 09:55
 * To change this template use File | Settings | File Templates.
 */
public class GameBoard {

  private Set<Cell> cells = new HashSet<Cell>();

  public void setLiveCellAt(int x, int y) {
    cells.add(Cell.atPosition(x, y));
  }

  public int countLiveNeighboursOf(Cell cell) {
    Set<Cell> neighbours = cell.neighbours();
    int liveNeighbours = 0;
    for (Cell neighbour : neighbours) {
      if(cells.contains(neighbour)) {
        liveNeighbours++;
      }
    }
    return liveNeighbours;
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
    GameBoard gameBoard = (GameBoard) o;
    if(cells.size() == gameBoard.cells.size() && cells.containsAll(gameBoard.cells)) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return cells != null ? cells.hashCode() : 0;
  }

/*
  public GameBoard nextState() {
    GameBoard nextBoard = new GameBoard();
    for (Cell currentCell : cells) {
      Cell nextCellState = currentCell.nextState(this);
      if(nextCellState.isLive()) {
        nextBoard.addLiveCell(currentCell);
      }
      for(Cell neighbour : currentCell.neighbours()) {
        Cell nextNeighbourState = neighbour.nextState(this);
        if(nextNeighbourState.isLive()) {
          nextBoard.addLiveCell(nextNeighbourState);
        }
      }
    }
    return nextBoard;
  }
*/

  public GameBoard nextState() {
    GameBoard nextBoard = new GameBoard();
    for (Cell currentCell : cells) {
      Cell nextCellState = currentCell.nextState(this);
      if(nextCellState.isLive()) {
        nextBoard.addLiveCell(currentCell);
      }
      for(Cell ncell: currentCell.neighbours()) {
        Cell nextNCell = ncell.nextState(this);
        if(nextNCell.isLive()) {
          nextBoard.addLiveCell(nextNCell);
        }
      }
    }
    return nextBoard;
  }

  private void addLiveCell(Cell cell) {
    cells.add(cell);
  }

  public Set<Cell> liveCells() {
    return Collections.unmodifiableSet(cells);
  }
}
