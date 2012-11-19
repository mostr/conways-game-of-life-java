package pl.michalostruszka.gameoflife;

import java.util.HashSet;
import java.util.Set;

public class Cell {
    private final Position position;
    private boolean isLive;

    private Cell(int x, int y) {
        this.position = new Position(x, y);
        this.isLive = true;
    }

    private Cell(Position srcPosition) {
        this.position = srcPosition;
    }

    public static Cell at(int x, int y) {
        return new Cell(x, y);
    }

    public Cell nextState(Board board) {
        Cell nextState = new Cell(this.position);
        int count = board.countLiveNeighboursOf(this);
        if ((count == 2 && this.isLive()) || count == 3) {
            nextState.isLive = true;
        } else {
            nextState.isLive = false;
        }
        return nextState;
    }

    public Set<Cell> neighbours() {
        Set<Cell> cells = new HashSet<Cell>();
        for (Position neighbour : position.neighbours()) {
            cells.add(new Cell(neighbour));
        }
        return cells;
    }

    public boolean isLive() {
        return isLive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        if (position != null ? !position.equals(cell.position) : cell.position != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return position != null ? position.hashCode() : 0;
    }
}
