package pl.michalostruszka.gameoflife;

public class GameOfLife {

    private Board currentState;

    public GameOfLife(Board initialState) {
        currentState = initialState;
    }

    public GameOfLife() {
        currentState = new Board();
    }

    public Board currentBoardState() {
        return currentState;
    }

    public void tick(int ticksCount) {
        for (int i = 0; i < ticksCount; i++) {
            this.currentState = currentState.nextState();
        }
    }

}
