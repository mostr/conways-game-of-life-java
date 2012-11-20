package pl.michalostruszka.gameoflife.board;

import pl.michalostruszka.gameoflife.cell.Cell;
import pl.michalostruszka.gameoflife.cell.DeadCell;
import pl.michalostruszka.gameoflife.cell.LiveCell;
import pl.michalostruszka.gameoflife.cell.Position;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Board {

    private Set<LiveCell> liveCells = new HashSet<LiveCell>();

    public int countLiveNeighboursOf(Cell cell) { // TODO: it's ugly, think of how to do it better
        Set<Position> neighbours = cell.neighbours();
        int liveNeighbours = 0;
        for (Position neighbour : neighbours) {
            for (LiveCell currentCell : liveCells) {
                if(currentCell.isAtPosition(neighbour)) {
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
            board.bringCellToLife(cell);
        }
        return board;
    }

    public Board nextState() {
        Board nextBoard = new Board();
        for (LiveCell currentCell : liveCells) {
            currentCell.evolveIntoNewState(this).attachToBoard(nextBoard);
            evolveCellNeighbours(nextBoard, currentCell.neighbours());
        }
        return nextBoard;
    }

    private void evolveCellNeighbours(Board nextBoard, Set<Position> neighbours) {
        for (Position neighbourPosition: neighbours) {
            Cell nextNeighbourState = determineCellAt(neighbourPosition).evolveIntoNewState(this);
            nextNeighbourState.attachToBoard(nextBoard);
        }
    }

    private Cell determineCellAt(Position neighbourPosition) {
        return new CellFromPositionFactory().determineCellAt(neighbourPosition);
    }

    public void bringCellToLife(LiveCell cell) {
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

    private class CellFromPositionFactory {
        public Cell determineCellAt(Position position) {
            for (LiveCell currentCell : liveCells) {
                if(currentCell.isAtPosition(position)) {
                    return new LiveCell(position);
                }
            }
            return new DeadCell(position);
        }
    }

}
