package pl.michalostruszka.gameoflife;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: mostruszka
 * Date: 16.11.12
 * Time: 11:05
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class CellTest {

  @Mock
  private GameBoard board;
  private static final Cell CURRENT_CELL = Cell.atPosition(0, 0);

  @Test
  public void shouldReturnNewStateOfCellOnEvaluation() throws Exception {
    Cell nextState = CURRENT_CELL.nextState(board);
    assertThat(nextState).isNotNull().isNotSameAs(CURRENT_CELL).isEqualTo(CURRENT_CELL);
  }

  @Test
  public void shouldGenerateAllPossibleNeighboursPositions() throws Exception {
    Set<Cell> expectedNeighbours = new HashSet<Cell>();
    expectedNeighbours.add(Cell.atPosition(0, 1));
    expectedNeighbours.add(Cell.atPosition(1, 1));
    expectedNeighbours.add(Cell.atPosition(1, 0));
    expectedNeighbours.add(Cell.atPosition(1, -1));
    expectedNeighbours.add(Cell.atPosition(0, -1));
    expectedNeighbours.add(Cell.atPosition(-1, -1));
    expectedNeighbours.add(Cell.atPosition(-1, 0));
    expectedNeighbours.add(Cell.atPosition(-1, 1));

    Set<Cell> neighbours = CURRENT_CELL.neighbours();

    assertThat(neighbours).hasSameSizeAs(expectedNeighbours).containsAll(expectedNeighbours);
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
}
