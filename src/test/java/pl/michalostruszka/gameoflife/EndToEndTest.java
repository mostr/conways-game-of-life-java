package pl.michalostruszka.gameoflife;

import org.junit.Ignore;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: mostruszka
 * Date: 16.11.12
 * Time: 09:50
 * To change this template use File | Settings | File Templates.
 */
public class EndToEndTest {

  public static final GameBoard EMPTY_BOARD = new GameBoard();

  @Test
  public void newGameShouldHaveEmptyState() throws Exception {
    GameOfLife game = new GameOfLife();
    GameBoard currentBoard = game.currentBoardState();
    assertThat(currentBoard).isEqualTo(EMPTY_BOARD);
  }

  @Test
  public void gameShouldBeSeededWithInitialState() throws Exception {
    GameOfLife game = new GameOfLife(initialState());
    GameBoard currentBoard = game.currentBoardState();
    assertThat(currentBoard).isEqualTo(initialState());
  }

  @Test
  public void gameShouldChangeStateAfterTimeTick() throws Exception {
    GameBoard blinkerState = new GameBoard();
    blinkerState.setLiveCellAt(0, 0);
    blinkerState.setLiveCellAt(0, 1);
    blinkerState.setLiveCellAt(0, -1);

    GameOfLife game = new GameOfLife(blinkerState);

    GameBoard expectedState = new GameBoard();
    expectedState.setLiveCellAt(-1,0);
    expectedState.setLiveCellAt(0,0);
    expectedState.setLiveCellAt(1,0);

    game.tick();
    GameBoard currentBoard = game.currentBoardState();

    assertThat(currentBoard).isEqualTo(expectedState);
  }

  private GameBoard initialState() {
    GameBoard initialState = new GameBoard();
    initialState.setLiveCellAt(0,0);
    initialState.setLiveCellAt(0,1);
    initialState.setLiveCellAt(0,2);
    initialState.setLiveCellAt(0,3);
    return initialState;
  }
}
