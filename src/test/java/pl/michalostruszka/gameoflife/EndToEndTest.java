package pl.michalostruszka.gameoflife;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class EndToEndTest {

    public static final Board EMPTY_BOARD = new Board();
    public static final Board SAMPLE_INITIAL_STATE = Board.seedWith(Cell.at(0, 0), Cell.at(1, 0));

    public static final Board FIRST_BLINKER_STATE = Board.seedWith(Cell.at(0, 1), Cell.at(0, 0), Cell.at(0, -1));
    public static final Board SECOND_BLINKER_STATE = Board.seedWith(Cell.at(-1, 0), Cell.at(0, 0), Cell.at(1, 0));


    @Test
    public void newGameShouldHaveEmptyState() throws Exception {
        GameOfLife game = new GameOfLife();
        Board currentBoard = game.currentBoardState();
        assertThat(currentBoard).isEqualTo(EMPTY_BOARD);
    }

    @Test
    public void gameShouldBeSeededWithInitialState() throws Exception {
        GameOfLife game = new GameOfLife(SAMPLE_INITIAL_STATE);
        assertThat(game.currentBoardState()).isEqualTo(SAMPLE_INITIAL_STATE);
    }

    @Test
    public void gameShouldChangeBlinkerStateAfterTimeTicks() throws Exception {
        GameOfLife game = new GameOfLife(FIRST_BLINKER_STATE);
        game.tick(1);
        assertThat(game.currentBoardState()).isEqualTo(SECOND_BLINKER_STATE);
    }

    @Test
    public void gameShouldChangeStateAfterTimeTicksTwice() throws Exception {
        GameOfLife game = new GameOfLife(FIRST_BLINKER_STATE);
        game.tick(2);
        assertThat(game.currentBoardState()).isEqualTo(FIRST_BLINKER_STATE);
    }

}
