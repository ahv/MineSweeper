package asd.fgh.minesweeper.logic;

import asd.fgh.minesweeper.logic.data.Board;
import asd.fgh.minesweeper.logic.persistence.Score;

/**
 * The UI facing class; generates the board internally, has the required methods
 * for playing the game and knows when the Game is over.
 *
 * @author ahv
 */
public class Game {

    private GameState state;
    private final Board board;
    private final GameSettings settings;
    private final StopWatch time;

    /**
     * Constructor for custom games, values are validated internally in a
     * GameSettings object.
     *
     * @param mines Amount of mines in the game, between 1 and total amount of
     * grids.
     * @param width Width of the game board.
     * @param height Height of the game board.
     *
     * @see asd.fgh.minesweeper.logic.GameSettings#GameSettings(int, int, int)
     */
    public Game(int mines, int width, int height) {
        this(new GameSettings(mines, width, height));
    }

    /**
     * Constructor for preset games, values defined in GameSettings class.
     *
     * @param difficulty Enum to identify a preset in GameSettings.
     *
     * @see
     * asd.fgh.minesweeper.logic.GameSettings#generatePreset(asd.fgh.minesweeper.logic.Difficulty)
     */
    public Game(Difficulty difficulty) {
        this(GameSettings.generatePreset(difficulty));
    }

    private Game(GameSettings s) {
        this.settings = s;
        this.state = GameState.READY;
        this.board = new Board(s.getMines(), s.getWidth(), s.getHeight());
        this.time = new StopWatch();
    }

    public GameSettings getSettings() {
        return settings;
    }

    /**
     * Check to see if the game is no longer in progress; a win or loss
     * condition has been reached.
     *
     * @return Returns true if no more game-play input will be handled.
     */
    public boolean hasEnded() {
        return (this.state == GameState.LOST || this.state == GameState.WON);
    }

    public boolean isWon() {
        return (state == GameState.WON);
    }

    /**
     * Player input to attempt to open a grid in the game.
     *
     * @param x X-coordinate.
     * @param y Y-coordinate.
     * @return True if opening a grid succeeded. Will return false when the grid
     * is flagged or game is over.
     */
    public boolean openGridAt(int x, int y) {
        if (!preCheck() || board.isFlagged(x, y)) {
            return false;
        }
        if (board.openGridAt(x, y)) { // if hit mine
            endGame(GameState.LOST);
            return false;
        } else if (board.isCompletelyExplored()) {
            endGame(GameState.WON);
            return false;
        }
        return true;
    }

    /**
     * Places or removes a flag on an unrevealed grid.
     *
     * @param x X-coordinate.
     * @param y Y-coordinate.
     * @return Returns true if game is playing and the grid hasn't been
     * revealed.
     */
    public boolean flagGridAt(int x, int y) {
        if (preCheck() && !board.isRevealed(x, y)) {
            board.flipFlag(x, y);
            return true;
        }
        return false;
    }

    /**
     * Special functionality for automatically opening adjacent grids. When used
     * on a revealed grid that has an equal amount of adjacent mined and flagged
     * grids will open all unflagged grids.
     *
     * @param x X-coordinate.
     * @param y Y-coordinate.
     */
    public void openAdjacentsAt(int x, int y) {
        if (!preCheck() || !board.isRevealed(x, y)) {
            return;
        }
        if (board.touchedFlagsAt(x, y) == board.touchedMinesAt(x, y)) {
            if (board.openAdjacentsAt(x, y)) { // if mine was hit
                endGame(GameState.LOST);
            } else if (board.isCompletelyExplored()) {
                endGame(GameState.WON);
            }
        }
    }

    private boolean preCheck() {
        // Starts clock on first action.
        if (state == GameState.READY) {
            this.time.start();
            this.state = GameState.RUNNING;
        }
        return !hasEnded();
    }

    private void endGame(GameState result) {
        state = result;
        time.stop();
    }

    public int getElapsedTime() {
        if (state == GameState.READY) {
            return 0;
        }
        return time.getElapsedTime();
    }

    // TODO: Shouldn't be called while the game is still going.
    // Maybe instead of returning null, return some sort of invalid score?
    public Score getFinalScore() {
        if (hasEnded()) {
            return new Score(settings.getDifficulty(), time.getElapsedTime());
        } else {
            return null;
        }
    }

    // TODO: Makeshift thing for UI, could use a rework
    /**
     * Generates an int code based snapshot of the current visible state of the
     * board. The integers represent states as follows: -2 flagged, -1
     * unrevealed, 0..8 touching x amount of mines, 9 mine
     *
     * @return A snapshot of the current visible game state.
     */
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
