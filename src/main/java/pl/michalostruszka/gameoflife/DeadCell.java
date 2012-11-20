package pl.michalostruszka.gameoflife;

public class DeadCell extends BaseCell {

    public DeadCell(Position neighbourPosition) {
        super(neighbourPosition);
    }

    public BaseCell nextState(Board board) {
        int count = board.countLiveNeighboursOf(this);
        if (count == 3) {
            return new LiveCell(this.position);
        }
        return new DeadCell(this.position);
    }
}
