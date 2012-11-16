package pl.michalostruszka.gameoflife;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CellTest {

    @Mock
    private Board board;

    private static final Cell CURRENT_CELL = Cell.at(0, 0);
    private static final Set<Cell> CURRENT_CELL_NEIGHBOURS = currentCellNeighbours();

    @Test
    public void shouldReturnNewStateOfCellOnEvaluation() throws Exception {
        Cell nextState = CURRENT_CELL.nextState(board);
        assertThat(nextState).isNotNull().isNotSameAs(CURRENT_CELL).isEqualTo(CURRENT_CELL);
    }

    @Test
    public void shouldGenerateAllPossibleNeighboursPositions() throws Exception {
        Set<Cell> neighbours = CURRENT_CELL.neighbours();
        assertThat(neighbours).hasSameSizeAs(CURRENT_CELL_NEIGHBOURS).containsAll(CURRENT_CELL_NEIGHBOURS);
    }

    @Test
    public void shouldDieWhenHasNoLiveNeighbours() throws Exception {
        isLiveForGivenLiveNeighbours(0);
    }

    @Test
    public void shouldDieWhenHasOneLiveNeighbour() throws Exception {
        assertThat(isLiveForGivenLiveNeighbours(1)).isEqualTo(false);
    }

    @Test
    public void shouldLiveOnWhenHasTwoLiveNeighbour() throws Exception {
        assertThat(isLiveForGivenLiveNeighbours(2)).isEqualTo(true);
    }

    @Test
    public void shouldLiveOnWhenHasThreeLiveNeighbour() throws Exception {
        assertThat(isLiveForGivenLiveNeighbours(3)).isEqualTo(true);
    }

    @Test
    public void shouldLiveOnWhenHasFourLiveNeighbour() throws Exception {
        assertThat(isLiveForGivenLiveNeighbours(4)).isEqualTo(false);
    }

    @Test
    public void shouldLiveOnWhenHasMoreThanThreeLiveNeighbour() throws Exception {
        assertThat(isLiveForGivenLiveNeighbours(6)).isEqualTo(false);
    }

    private boolean isLiveForGivenLiveNeighbours(int count) {
        when(board.countLiveNeighboursOf(CURRENT_CELL)).thenReturn(count);
        Cell nextState = CURRENT_CELL.nextState(board);
        return nextState.isLive();
    }

    private static Set<Cell> currentCellNeighbours() {
        Set<Cell> expectedNeighbours = new HashSet<Cell>();
        int[][] neighboursRelativeCoords = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
        for (int[] coords : neighboursRelativeCoords) {
            expectedNeighbours.add(Cell.at(coords[0], coords[1]));
        }
        return expectedNeighbours;
    }

}
