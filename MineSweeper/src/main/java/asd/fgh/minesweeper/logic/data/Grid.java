package asd.fgh.minesweeper.logic.data;

// Simple enough data class that it doesn't need tests?
import java.util.ArrayList;

/**
 * Simple data class to know everything a Grid needs to know about itself. Used
 * internally in Board class.
 *
 * @author ahv
 */
public class Grid {

    private final Board board;

    private final int x;
    private final int y;
    private boolean revealed;
    private boolean flagged;
    private final boolean mined;
    private final int touchesMines;

    Grid(Board board, int x, int y, boolean hasMine, int touchesMines) {
        // TODO: If touches more than 8 mines then something has gone wrong!
        this.board = board;
        this.x = x;
        this.y = y;
        this.mined = hasMine;
        this.touchesMines = touchesMines;
        this.revealed = false;
        this.flagged = false;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public GridState getState() {
        if (revealed && mined) {
            return GridState.MINED;
        } else if (revealed && !mined) {
            return GridState.nthEnum(touchesMines);
        } else if (flagged) {
            return GridState.FLAGGED;
        }
        return GridState.UNREVEALED;
    }

    int touchedMines() {
        return touchesMines;
    }

    boolean isMined() {
        return mined;
    }

    boolean isRevealed() {
        return revealed;
    }

    boolean isFlagged() {
        return flagged;
    }

    void flipFlag() {
        if (!isRevealed()) {
            flagged = !flagged;
        }
    }

    ArrayList<Grid> open(ArrayList<Grid> changedGrids) {
        if (flagged || revealed) {
            return changedGrids;
        }
        revealed = true;
        changedGrids.add(this);
        if (mined || touchesMines > 0) {
            return changedGrids;
        }
        for (Grid g : board.neighbourGrids(x, y)) {
            g.open(changedGrids);
        }
        return changedGrids;
    }

    boolean flaggedNeighboursMatchMinedNeighbours() {
        int flags = 0;
        for (Grid g : board.neighbourGrids(x, y)) {
            if (g.flagged) {
                flags++;
            }
        }
        return flags == touchesMines;
    }
}
