package pl.michalostruszka.gameoflife;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class BoardTest {

    private static final Board EMPTY_BOARD = new Board();
    private Board board;

    @Test
    public void emptyBoardShouldHaveNoLiveNeighboursForAnyCell() throws Exception {
        int result = EMPTY_BOARD.countLiveNeighboursOf(Cell.at(0, 0));
        assertThat(result).isZero();
    }

    @Test
    public void countLiveNeighboursForGivenCell() throws Exception {
        board = Board.seedWith(Cell.at(1, 0), Cell.at(1, 1));
        int result = board.countLiveNeighboursOf(Cell.at(0, 0));
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void shouldEvaluateNextBoardState() throws Exception {
        board = Board.seedWith(Cell.at(0, 0), Cell.at(1, 0), Cell.at(1, 1));
        Board next = board.nextState();
        assertThat(next.liveCells()).containsOnly(Cell.at(0, 0), Cell.at(0, 1), Cell.at(1, 1), Cell.at(1, 0));
    }
}
