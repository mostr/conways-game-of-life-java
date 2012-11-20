package pl.michalostruszka.gameoflife;

import org.junit.Test;
import pl.michalostruszka.gameoflife.board.Board;
import pl.michalostruszka.gameoflife.cell.LiveCell;

import static org.fest.assertions.api.Assertions.assertThat;

public class EndToEndTest {

    public static final Board EMPTY_BOARD = new Board();
    public static final Board SAMPLE_INITIAL_STATE = Board.seedWith(LiveCell.at(0, 0), LiveCell.at(1, 0));

    public static final Board FIRST_BLINKER_STATE = Board.seedWith(LiveCell.at(0, 1), LiveCell.at(0, 0), LiveCell.at(0, -1));
    public static final Board SECOND_BLINKER_STATE = Board.seedWith(LiveCell.at(-1, 0), LiveCell.at(0, 0), LiveCell.at(1, 0));
    private static final Board GLIDER_INITIAL_STATE = Board.seedWith(LiveCell.at(0, 0), LiveCell.at(1, 0), LiveCell.at(2, 0), LiveCell.at(2, 1), LiveCell.at(1, 2));
    private static final Board GLIDER_STATE_AFTER_5_TICKS = Board.seedWith(LiveCell.at(1, 0), LiveCell.at(2, -1), LiveCell.at(2, -2), LiveCell.at(3, 0), LiveCell.at(3, -1));


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
    public void runningGameWithEmptyBoardCausesNoStateChange() throws Exception {
        GameOfLife gameWithEmptyBoard = new GameOfLife(EMPTY_BOARD);
        gameWithEmptyBoard.tick(10);
        assertThat(gameWithEmptyBoard.currentBoardState()).isEqualTo(EMPTY_BOARD);

    }

    @Test
    public void gameShouldChangeBlinkerStateAfterTimeTicks() throws Exception {
        GameOfLife game = new GameOfLife(FIRST_BLINKER_STATE);
        game.tick(1);
        assertThat(game.currentBoardState()).isEqualTo(SECOND_BLINKER_STATE);
    }

    @Test
    public void gameShouldChangeBlinkerStateBackAfterTimeTicksTwice() throws Exception {
        GameOfLife game = new GameOfLife(FIRST_BLINKER_STATE);
        game.tick(2);
        assertThat(game.currentBoardState()).isEqualTo(FIRST_BLINKER_STATE);
    }

    @Test
    public void gliderChangesStateAfterFiveTicks() throws Exception {
        GameOfLife game = new GameOfLife(GLIDER_INITIAL_STATE);
        game.tick(5);
        assertThat(game.currentBoardState()).isEqualTo(GLIDER_STATE_AFTER_5_TICKS);
    }

}
