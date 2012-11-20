package pl.michalostruszka.gameoflife.cell;

import java.util.HashSet;
import java.util.Set;

public class Position {

    protected final int x;
    protected final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Set<Position> neighbours() {
        Set<Position> expectedNeighbours = new HashSet<Position>();
        int[][] neighboursRelativeCoords = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
        for (int[] relativeCoord : neighboursRelativeCoords) {
            Position position = new Position(this.x + relativeCoord[0], this.y + relativeCoord[1]);
            expectedNeighbours.add(position);
        }
        return expectedNeighbours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        if (y != position.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
