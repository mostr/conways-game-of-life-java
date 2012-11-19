package pl.michalostruszka.gameoflife;

import java.util.HashSet;
import java.util.Set;

public class BaseCell {

    protected final Position position;

    public BaseCell(Position srcPosition) {
        this.position = srcPosition;
    }

    public boolean isOnPosition(Position expectedPosition) {
        return this.position.equals(expectedPosition);
    }
}
