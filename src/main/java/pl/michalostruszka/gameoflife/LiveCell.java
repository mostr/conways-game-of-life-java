package pl.michalostruszka.gameoflife;

import java.util.HashSet;
import java.util.Set;

public class LiveCell extends BaseCell {
    private boolean isLive;

    // when created from Position set it to dead status
    public LiveCell(Position srcPosition) {
        super(srcPosition);
        this.isLive = false;
    }

    // when created from coords set it to live status
    public static LiveCell at(int x, int y) {
        LiveCell cell = new LiveCell(new Position(x, y));
        cell.isLive = true;
        return cell;
    }

    public BaseCell nextState(Board board) {
        int count = board.countLiveNeighboursOf(this);
        if (count == 2 || count == 3) {
            return new LiveCell(this.position);
        }
        return new DeadCell(this.position);
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
