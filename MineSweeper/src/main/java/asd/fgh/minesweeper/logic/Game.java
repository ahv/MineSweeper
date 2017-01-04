package asd.fgh.minesweeper.logic;

import asd.fgh.minesweeper.logic.data.Board;

public class Game {

    private GameState state;
    private final Board board;
    private final GameSettings settings;
    // TODO: Implement clock
    private final Clock clock;

    public Game(int mines, int width, int height) {
        this(new GameSettings(mines, width, height));
    }

    public Game(Difficulty difficulty) throws Exception {
        this(GameSettings.generatePreset(difficulty));
    }

    private Game(GameSettings s) {
        this.settings = s;
        this.state = GameState.READY;
        this.board = new Board(s.getMines(), s.getWidth(), s.getHeight());
        this.clock = new Clock();
    }

    public GameSettings getSettings() {
        return settings;
    }

    public boolean hasEnded() {
        return (this.state == GameState.LOST || this.state == GameState.WON);
    }

    // True: managed to open grid
    public boolean openGridAt(int x, int y) {
        if (!preCheck() || board.isFlagged(x, y)) {
            return false;
        }
        afterCheck(x, y);
        return board.openGridAt(x, y);
    }

    private boolean preCheck() {
        // Starts clock on first action.
        if (state == GameState.READY) {
            this.clock.start();
            this.state = GameState.RUNNING;
        }
        return !hasEnded();
    }

    // True: game is over
    private boolean afterCheck(int x, int y) {
        if (board.isMined(x, y)) {
            state = GameState.LOST;
        } else if (board.isCompletelyExplored()) {
            state = GameState.WON;
        } else {
            return false;
        }
        this.clock.stop();
        return true;
    }

    // This is the both mouse buttons click functionality:
    // when adjacent flags count match the adjacent mine count in the grid
    // assume unflagged adjacents safe and open them.
    // True: Was able to open adjacents
    public boolean openAdjacentsAt(int x, int y) {
        if (!preCheck()) {
            return false;
        }
        if (board.isRevealed(x, y) && board.touchedFlagsAt(x, y) == board.touchedMinesAt(x, y)) {
            board.openAdjacentsAt(x, y);
            return true;
        }
        return false;
    }

    public void flagGridAt(int x, int y) {
        if (preCheck()) {
            board.flipFlag(x, y);
        }
    }

    // Convenience for UI
    // TODO: Pretty obscure int based implementation, redo?
    // -2 flagged, -1 unrevealed, 0..8 touching mines, 9 mine
    public int[][] getBoardSnapshot() {
        int[][] snap = new int[board.getWidth()][board.getHeight()];
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if (board.isFlagged(x, y)) {
                    snap[x][y] = -2;
                } else if (!board.isRevealed(x, y)) {
                    snap[x][y] = -1;
                } else if (board.isMined(x, y)) {
                    snap[x][y] = 9;
                } else {
                    snap[x][y] = board.touchedMinesAt(x, y);
                }

            }
        }
        return snap;
    }
}
