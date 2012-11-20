package pl.michalostruszka.gameoflife;

public class DeadCell extends BaseCell {

    public DeadCell(Position neighbourPosition) {
        super(neighbourPosition);
    }

    public BaseCell evolveIntoNewState(Board board) {
        int count = board.countLiveNeighboursOf(this);
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
