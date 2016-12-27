package asd.fgh.minesweeper.logic.data;

// Simple enough data class that it doesn't need tests?
class Grid {

    private boolean revealed;
    private boolean flagged;
    private final boolean hasMine;
    private final int touchesMines;

    Grid(boolean hasMine, int touchesMines) {
        this.hasMine = hasMine;
        this.touchesMines = touchesMines;
    }

    @Override
    public String toString() {
        return hasMine ? "*" : "" + touchesMines; // TODO: Return another symbols for flagged/unrevealed
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

    boolean reveal() {
        revealed = true;
        return hasMine;
    }
}
