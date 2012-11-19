package pl.michalostruszka.gameoflife;

import java.util.HashSet;
import java.util.Set;

public class LiveCell {
    private final Position position;
    private boolean isLive;

    private LiveCell(int x, int y) {
        this.position = new Position(x, y);
        this.isLive = true;
    }

    private LiveCell(Position srcPosition) {
        this.position = srcPosition;
    }

    public static LiveCell at(int x, int y) {
        return new LiveCell(x, y);
    }

    public LiveCell nextState(Board board) {
        LiveCell nextState = new LiveCell(this.position);
        int count = board.countLiveNeighboursOf(this);
        if ((count == 2 && this.isLive()) || count == 3) {
            nextState.isLive = true;
        } else {
            nextState.isLive = false;
        }
        return nextState;
    }

    public Set<LiveCell> neighbours() {
        Set<LiveCell> cells = new HashSet<LiveCell>();
        for (Position neighbour : position.neighbours()) {
            cells.add(new LiveCell(neighbour));
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
        LiveCell cell = (LiveCell) o;
        if (position != null ? !position.equals(cell.position) : cell.position != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return position != null ? position.hashCode() : 0;
    }
}
