package pl.michalostruszka.gameoflife.board;

import org.junit.Test;
import pl.michalostruszka.gameoflife.board.Board;
import pl.michalostruszka.gameoflife.cell.LiveCell;

import static org.fest.assertions.api.Assertions.assertThat;

public class BoardTest {

    private static final Board EMPTY_BOARD = new Board();
    private Board board;

    @Test
    public void emptyBoardShouldHaveNoLiveNeighboursForAnyCell() throws Exception {
        int result = EMPTY_BOARD.countLiveNeighboursOf(LiveCell.at(0, 0));
        assertThat(result).isZero();
    }

    @Test
    public void countLiveNeighboursForGivenCell() throws Exception {
        board = Board.seedWith(LiveCell.at(1, 0), LiveCell.at(1, 1));
        int result = board.countLiveNeighboursOf(LiveCell.at(0, 0));
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void shouldEvaluateNextBoardState() throws Exception {
        board = Board.seedWith(LiveCell.at(0, 0), LiveCell.at(1, 0), LiveCell.at(1, 1));
        Board next = board.nextState();
        assertThat(next.liveCells()).containsOnly(LiveCell.at(0, 0), LiveCell.at(0, 1), LiveCell.at(1, 1), LiveCell.at(1, 0));
    }
}
