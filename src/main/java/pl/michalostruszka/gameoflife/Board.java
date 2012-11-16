package pl.michalostruszka.gameoflife;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Board {

    private Set<Cell> cells = new HashSet<Cell>();

    public int countLiveNeighboursOf(Cell cell) {
        Set<Cell> neighbours = cell.neighbours();
        int liveNeighbours = 0;
        for (Cell neighbour : neighbours) {
            if (cells.contains(neighbour)) {
                liveNeighbours++;
            }
        }
        return liveNeighbours;
    }

    public Set<Cell> liveCells() {
        return Collections.unmodifiableSet(cells);
    }

    public static Board seedWith(Cell... initialCells) {
        Board board = new Board();
        for (Cell cell : initialCells) {
            board.addLiveCell(cell);
        }
        return board;
    }

    public Board nextState() {
        Board nextBoard = new Board();
        for (Cell currentCell : cells) {
            Cell nextCellState = currentCell.nextState(this);
            if (nextCellState.isLive()) {
                nextBoard.addLiveCell(currentCell);
            }
            for (Cell ncell : currentCell.neighbours()) {
                Cell nextNCell = ncell.nextState(this);
                if (nextNCell.isLive()) {
                    nextBoard.addLiveCell(nextNCell);
                }
            }
        }
        return nextBoard;
    }

    private void addLiveCell(Cell cell) {
        cells.add(cell);
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("[");
        for (Cell cell : cells) {
            buffer.append(cell + ", ");
        }
        return buffer + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        if (cells.size() == board.cells.size() && cells.containsAll(board.cells)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return cells != null ? cells.hashCode() : 0;
    }

}
