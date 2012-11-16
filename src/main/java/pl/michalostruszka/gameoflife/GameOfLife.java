package pl.michalostruszka.gameoflife;

/**
 * Created with IntelliJ IDEA.
 * User: mostruszka
 * Date: 16.11.12
 * Time: 09:54
 * To change this template use File | Settings | File Templates.
 */
public class GameOfLife {

  private GameBoard currentState;

  public GameOfLife(GameBoard initialState) {
    currentState = initialState;
  }

  public GameOfLife() {
    currentState = new GameBoard();
  }

  public GameBoard currentBoardState() {
    return currentState;
  }

  public void tick() {
    this.currentState = currentState.nextState();
  }

}
