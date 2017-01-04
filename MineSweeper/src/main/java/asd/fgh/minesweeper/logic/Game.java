package asd.fgh.minesweeper.logic;

import asd.fgh.minesweeper.logic.data.Board;

public class Game {

    // TODO: Implement clock
    // TODO: Implement game play logic
    private GameState state;
    private final Board board;
    private final GameSettings settings;
    private final Clock clock;

    public Game(GameSettings settings) {
        this.settings = settings;
        this.state = GameState.READY;
        this.board = new Board(settings);
        this.clock = new Clock();
    }

    // WARNING: Temporarily exposed Board for tests.
    public Board getBoard() {
        return board;
    }

    public GameState getState() {
        return state;
    }

    private boolean isGameOver() {
        return (this.state == GameState.LOST || this.state == GameState.WON);
    }

    // TODO: A fat method for basically all game logic. Chop it up?
    public void open(int x, int y) {
        if (isGameOver()) {
            return;
        }
        if (board.isRevealed(x, y)) {
            return;
        }
        // Start clock on first open.
        if (state == GameState.READY) {
            this.clock.start();
            this.state = GameState.RUNNING;
        }
        board.open(x, y);
        if (board.isMined(x, y)) {
            endGame(GameState.LOST);
        }
        if (winConditionMet()) {
            endGame(GameState.WON);
        }
    }

    public void flag(int x, int y) {
        if (!isGameOver()) {
            board.flipFlag(x, y);
        }
    }

    private void endGame(GameState result) {
        this.state = result;
        this.clock.stop();
    }

    // Win when all mineless grids are revealed.
    private boolean winConditionMet() {
        // TODO: Awkward
        return (board.getRevealedAmount() + board.getMineAmount()) == (board.getWidth() * board.getHeight());
    }

    // TODO: Pretty obscure int based implementation, redo.
    // -2 flagged, -1 unrevealed, 0..8 touching mines, 9 mine
    public int[][] getBoardSnapshot() {
        int[][] snap = new int[board.getWidth()][board.getHeight()];
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if (board.isFlagged(x, y)){
                    snap[x][y] = -2;
                } else if (!board.isRevealed(x, y)){
                    snap[x][y] = -1;
                } else if (board.isMined(x, y)){
                    snap[x][y] = 9;
                } else {
                    snap[x][y] = board.touchedMines(x, y);
                }
                
            }
        }
        return snap;
    }
}
