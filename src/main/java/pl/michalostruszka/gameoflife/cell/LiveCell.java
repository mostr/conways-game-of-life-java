package pl.michalostruszka.gameoflife.cell;

import pl.michalostruszka.gameoflife.board.Board;

public class LiveCell extends Cell {

    public LiveCell(Position srcPosition) {
        super(srcPosition);
    }

    public static LiveCell at(int x, int y) {
        LiveCell cell = new LiveCell(new Position(x, y));
        return cell;
    }

    public Cell evolveIntoNewState(Board board) {
        int count = board.countLiveNeighboursOf(this);
        return newStateDependingOnNeighboursCount(count);
    }

    protected Cell newStateDependingOnNeighboursCount(int count) {
        if (count == 2 || count == 3) {
            return new LiveCell(this.position);
        }
        return new DeadCell(this.position);
    }

    @Override
    public void attachToBoard(Board board) {
        board.bringCellToLife(this);
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
