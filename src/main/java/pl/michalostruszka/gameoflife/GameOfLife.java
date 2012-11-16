package pl.michalostruszka.gameoflife;

/**
 * Created with IntelliJ IDEA.
 * User: mostruszka
 * Date: 16.11.12
 * Time: 09:54
 * To change this template use File | Settings | File Templates.
 */
public class GameOfLife {

  private GameState currentState;

  public GameOfLife(GameState initialState) {
    currentState = initialState;
  }

  public GameOfLife() {
    currentState = new GameState();
  }

  public GameState currentState() {
    return currentState;
  }

  public void tick() {
    // TODO tick
  }
}
