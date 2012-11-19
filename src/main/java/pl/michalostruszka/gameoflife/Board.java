package pl.michalostruszka.gameoflife;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Board {

    private Set<LiveCell> liveCells = new HashSet<LiveCell>();

    public int countLiveNeighboursOf(LiveCell cell) {
        Set<Position> neighbours = cell.neighbours();
        int liveNeighbours = 0;
        for (Position neighbour : neighbours) {
            for (LiveCell currentCell : liveCells) {
                if(currentCell.isOnPosition(neighbour)) {
                    liveNeighbours++;
                }
            }
        }
        return liveNeighbours;
    }



    public Set<LiveCell> liveCells() {
        return Collections.unmodifiableSet(liveCells);
    }

    public static Board seedWith(LiveCell... initialCells) {
        Board board = new Board();
        for (LiveCell cell : initialCells) {
            board.addLiveCell(cell);
        }
        return board;
    }

    public Board nextState() {
        Board nextBoard = new Board();
        for (LiveCell currentCell : liveCells) {
            LiveCell nextCellState = currentCell.nextState(this);
            if (nextCellState.isLive()) {
                nextBoard.addLiveCell(currentCell);
            }
            for (LiveCell ncell : currentCell.neighbours()) {
                LiveCell nextNCell = ncell.nextState(this);
                if (nextNCell.isLive()) {
                    nextBoard.addLiveCell(nextNCell);
                }
            }
        }
        return nextBoard;
    }

    private void addLiveCell(LiveCell cell) {
        liveCells.add(cell);
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("[");
        for (LiveCell cell : liveCells) {
            buffer.append(cell + ", ");
        }
        return buffer + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        if (liveCells.size() == board.liveCells.size() && liveCells.containsAll(board.liveCells)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return liveCells != null ? liveCells.hashCode() : 0;
    }

}
