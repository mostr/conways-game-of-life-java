package pl.michalostruszka.gameoflife.cell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.michalostruszka.gameoflife.board.Board;

import java.util.HashSet;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeadCellTest {

    @Mock
    private Board board;

    private static final DeadCell CURRENT_CELL = new DeadCell(new Position(0, 0));

    @Test
    public void shouldEvolveToDeadWhenHasNoLiveNeighbours() throws Exception {
        when(board.countLiveNeighboursOf(CURRENT_CELL)).thenReturn(0);
        Cell nextState = CURRENT_CELL.evolveIntoNewState(board);
        assertThat(nextState).isInstanceOf(DeadCell.class);
    }

    @Test
    public void shouldEvolveToLiveWhenHasThreeLiveNeighbour() throws Exception {
        when(board.countLiveNeighboursOf(CURRENT_CELL)).thenReturn(3);
        Cell nextState = CURRENT_CELL.evolveIntoNewState(board);
        assertThat(nextState).isInstanceOf(LiveCell.class);
    }

    @Test
    public void shouldEvolveToDeadWhenHasMoreThanThreeLiveNeighbours() throws Exception {
        when(board.countLiveNeighboursOf(CURRENT_CELL)).thenReturn(5);
        Cell nextState = CURRENT_CELL.evolveIntoNewState(board);
        assertThat(nextState).isInstanceOf(DeadCell.class);
    }

}
