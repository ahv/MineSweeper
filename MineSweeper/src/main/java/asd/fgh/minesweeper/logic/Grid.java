package asd.fgh.minesweeper.logic;

class Grid {
    private boolean revealed;
    private boolean flagged;
    private final boolean hasMine;

    public Grid(boolean hasMine) {
        this.hasMine = hasMine;
    }
}
