package pl.michalostruszka.gameoflife.cell;

import pl.michalostruszka.gameoflife.board.Board;

import java.util.HashSet;
import java.util.Set;

public abstract class Cell {

    protected final Position position;

    public Cell(Position srcPosition) {
        this.position = srcPosition;
    }

    public boolean isAtPosition(Position expectedPosition) {
        return this.position.equals(expectedPosition);
    }

    public Set<Position> neighbours() {
        Set<Position> neighbours = new HashSet<Position>();
        for (Position neighbour : position.neighbours()) {
            neighbours.add(neighbour);
        }
        return neighbours;
    }

    public abstract Cell evolveIntoNewState(Board board);

    public abstract void attachToBoard(Board board);

    protected abstract Cell newStateDependingOnNeighboursCount(int count);
}
