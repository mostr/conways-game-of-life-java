package pl.michalostruszka.gameoflife;

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

  public void tick(int ticksCount) {
		for(int i=0; i<ticksCount; i++) {
			this.currentState = currentState.nextState();
		}
  }

}
