package asd.fgh.minesweeper.logic.data;

// Simple enough data class that it doesn't need tests?
class Grid {

    private GridState state;
    private final int x;
    private final int y;
    private final boolean hasMine;
    private final int touchesMines;

    Grid(int x, int y, boolean hasMine, int touchesMines) {
        this.x = x;
        this.y = y;
        this.hasMine = hasMine;
        this.touchesMines = touchesMines;
        this.state = GridState.UNREVEALED;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int touchedMines() {
        return touchesMines;
    }

    boolean isMined() {
        return hasMine;
    }

    boolean isRevealed() {
        return state == GridState.REVEALED;
    }

    public boolean isFlagged() {
        return state == GridState.FLAGGED;
    }

    public void flipFlag() {
        if (!isRevealed()) {
            // TODO: This assumes that GridState enum doesn't get expanded.
        state = state == GridState.UNREVEALED ? GridState.FLAGGED : GridState.UNREVEALED;
        }
    }

    public void reveal() {
        if (!isFlagged() || !isRevealed()) {
            state = GridState.REVEALED;
        }
    }
}
