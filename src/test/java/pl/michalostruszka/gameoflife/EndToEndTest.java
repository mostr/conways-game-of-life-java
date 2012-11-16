package pl.michalostruszka.gameoflife;

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

  public static final GameState EMPTY_STATE = new GameState();

  @Test
  public void newGameShouldHaveEmptyState() throws Exception {
    GameOfLife game = new GameOfLife();
    GameState currentState = game.currentState();
    assertThat(currentState).isEqualTo(EMPTY_STATE);
  }

  @Test
  public void gameShouldBeSeededWithInitialState() throws Exception {
    GameOfLife game = new GameOfLife(initialState());
    GameState currentState = game.currentState();
    assertThat(currentState).isEqualTo(initialState());
  }

  @Test
  public void gameShouldChangeStateAfterTimeTick() throws Exception {
    GameState blinkerState = new GameState();
    blinkerState.setLiveCellAt(0, 0);
    blinkerState.setLiveCellAt(0, 1);
    blinkerState.setLiveCellAt(0, -1);

    GameOfLife game = new GameOfLife(blinkerState);

    GameState expectedState = new GameState();
    expectedState.setLiveCellAt(-1,0);
    expectedState.setLiveCellAt(0,0);
    expectedState.setLiveCellAt(1,0);

    game.tick();
    GameState currentState = game.currentState();

    assertThat(currentState).isEqualTo(expectedState);
  }

  private GameState initialState() {
    GameState initialState = new GameState();
    initialState.setLiveCellAt(0,0);
    initialState.setLiveCellAt(0,1);
    initialState.setLiveCellAt(0,2);
    initialState.setLiveCellAt(0,3);
    return initialState;
  }
}
