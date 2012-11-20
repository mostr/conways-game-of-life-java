package pl.michalostruszka.gameoflife.cell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.michalostruszka.gameoflife.board.Board;
import pl.michalostruszka.gameoflife.cell.Cell;
import pl.michalostruszka.gameoflife.cell.LiveCell;
import pl.michalostruszka.gameoflife.cell.Position;

import java.util.HashSet;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LiveCellTest {

    @Mock
    private Board board;

    private static final LiveCell CURRENT_CELL = LiveCell.at(0, 0);
    private static final Set<Position> CURRENT_CELL_NEIGHBOURS = currentCellNeighbours();

    @Test
    public void shouldGenerateAllPossibleNeighboursPositions() throws Exception {
        Set<Position> neighbours = CURRENT_CELL.neighbours();
        assertThat(neighbours).hasSameSizeAs(CURRENT_CELL_NEIGHBOURS).containsAll(CURRENT_CELL_NEIGHBOURS);
    }

    @Test
    public void shouldEvolveToDeadWhenHasNoLiveNeighbours() throws Exception {
        when(board.countLiveNeighboursOf(CURRENT_CELL)).thenReturn(0);
        Cell nextState = CURRENT_CELL.evolveIntoNewState(board);
        assertThat(nextState).isInstanceOf(DeadCell.class);
    }

    @Test
    public void shouldEvolveToLiveWhenHasTwoLiveNeighbour() throws Exception {
        when(board.countLiveNeighboursOf(CURRENT_CELL)).thenReturn(2);
        Cell nextState = CURRENT_CELL.evolveIntoNewState(board);
        assertThat(nextState).isInstanceOf(LiveCell.class);
    }

    @Test
    public void shouldEvolveToLiveWhenHasThreeLiveNeighbour() throws Exception {
        when(board.countLiveNeighboursOf(CURRENT_CELL)).thenReturn(3);
        Cell nextState = CURRENT_CELL.evolveIntoNewState(board);
        assertThat(nextState).isInstanceOf(LiveCell.class);
    }

    @Test
    public void shouldEvolveToDeadWhenHasFourLiveNeighbour() throws Exception {
        when(board.countLiveNeighboursOf(CURRENT_CELL)).thenReturn(5);
        Cell nextState = CURRENT_CELL.evolveIntoNewState(board);
        assertThat(nextState).isInstanceOf(DeadCell.class);
    }

    private static Set<Position> currentCellNeighbours() {
        Set<Position> expectedNeighbours = new HashSet<Position>();
        int[][] neighboursRelativeCoords = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
        for (int[] coords : neighboursRelativeCoords) {
            expectedNeighbours.add(new Position(coords[0], coords[1]));
        }
        return expectedNeighbours;
    }

}
