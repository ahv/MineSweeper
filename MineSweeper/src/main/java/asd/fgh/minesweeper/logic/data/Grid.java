package asd.fgh.minesweeper.logic.data;

public class Grid {

    private boolean revealed;
    private boolean flagged;
    private final boolean hasMine;
    private final int touchesMines;

    public Grid(boolean hasMine, int touchesMines) {
        this.hasMine = hasMine;
        this.touchesMines = touchesMines;
    }

    @Override
    public String toString() {
        return hasMine ? "*" : "" + touchesMines;
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
    
    boolean isRevealed(){
        return revealed;
    }

    boolean reveal() {
        revealed = true;
        return hasMine;
    }
}
