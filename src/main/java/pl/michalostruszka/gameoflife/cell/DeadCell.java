package pl.michalostruszka.gameoflife.cell;

import pl.michalostruszka.gameoflife.board.Board;

public class DeadCell extends Cell {

    public DeadCell(Position position) {
        super(position);
    }

    public Cell evolveIntoNewState(Board board) {
        int count = board.countLiveNeighboursOf(this);
        return newStateDependingOnNeighboursCount(count);
    }

    @Override
    public void attachToBoard(Board board) {
        // nothing to do, dead cells are not attached to board
    }

    @Override
    protected Cell newStateDependingOnNeighboursCount(int count) {
        if (count == 3) {
            return new LiveCell(this.position);
        }
        return new DeadCell(this.position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeadCell cell = (DeadCell) o;
        if (position != null ? !position.equals(cell.position) : cell.position != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return position != null ? position.hashCode() : 0;
    }

}
