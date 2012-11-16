package pl.michalostruszka.gameoflife;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: mostruszka
 * Date: 16.11.12
 * Time: 11:52
 * To change this template use File | Settings | File Templates.
 */
public class GameBoardTest {

  private static final GameBoard EMPTY_BOARD = new GameBoard();
  private GameBoard board = new GameBoard();

  @Test
  public void emptyBoardShouldHaveNoLiveNeighboursForAnyCell() throws Exception {
    int result = EMPTY_BOARD.countLiveNeighboursOf(Cell.at(0, 0));
    assertThat(result).isZero();
  }

  @Test
  public void countLiveNeighboursForGivenCell() throws Exception {
    board.setLiveCellAt(1,0);
    board.setLiveCellAt(1,1);
    int result = board.countLiveNeighboursOf(Cell.at(0, 0));
    assertThat(result).isEqualTo(2);
  }

  @Test
  public void shouldEvaluateCurrentCellsOnNextBoard() throws Exception {
    board.setLiveCellAt(0, 0);
    board.setLiveCellAt(1, 0);
    board.setLiveCellAt(1, 1);

    GameBoard next = board.nextState();

    assertThat(next.liveCells()).containsOnly(Cell.at(0, 0), Cell.at(0, 1), Cell.at(1, 1), Cell.at(1, 0));
  }
}
