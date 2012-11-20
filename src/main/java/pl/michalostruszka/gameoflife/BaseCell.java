package pl.michalostruszka.gameoflife;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseCell {

    protected final Position position;

    public BaseCell(Position srcPosition) {
        this.position = srcPosition;
    }

    public boolean isOnPosition(Position expectedPosition) {
        return this.position.equals(expectedPosition);
    }

    public Set<Position> neighbours() {
        Set<Position> neighbours = new HashSet<Position>();
        for (Position neighbour : position.neighbours()) {
            neighbours.add(neighbour);
        }
        return neighbours;
    }

    public abstract BaseCell nextState(Board board);

}
