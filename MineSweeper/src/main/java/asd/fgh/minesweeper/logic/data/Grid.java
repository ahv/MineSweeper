package asd.fgh.minesweeper.logic.data;

// Simple enough data class that it doesn't need tests?
class Grid {

    // TODO: Add some error throwing; a mine should never be revealed and flagged for example.
    // TODO: Maybe implement something enum based instead?
    private boolean revealed;
    private boolean flagged;
    private final int y;
    private final boolean hasMine;
    private final int touchesMines;
    private final int x;

    Grid(int x, int y, boolean hasMine, int touchesMines) {
        this.flagged = false;
        this.y = y;
        this.hasMine = hasMine;
        this.touchesMines = touchesMines;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public String toString() {
        return hasMine ? "*" : "" + touchesMines; // TODO: Return other symbols for flagged/unrevealed
    }

    boolean isMined() {
        return hasMine;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void flipFlagged() {
        this.flagged = !flagged;
    }

    boolean isRevealed() {
        return revealed;
    }

    // TODO: Return value isn't used. Maybe still useful in a more optimized implementation.
    boolean reveal() {
        revealed = true;
        return hasMine;
    }

    public int touchedMines() {
        return touchesMines;
    }
}
