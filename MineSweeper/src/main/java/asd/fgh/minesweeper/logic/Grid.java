package asd.fgh.minesweeper.logic;

class Grid {
    private boolean revealed;
    private boolean flagged;
    private final boolean hasMine;
    private final int touchesMines;

    public Grid(boolean hasMine, int touchesMines) {
        this.hasMine = hasMine;
        this.touchesMines = touchesMines;
    }
}
