package asd.fgh.minesweeper.logic;

import asd.fgh.minesweeper.logic.data.Board;
import asd.fgh.minesweeper.logic.data.Grid;
import asd.fgh.minesweeper.logic.data.GridState;
import java.util.ArrayList;

/**
 * The UI facing class; has the required methods for playing the game, generates
 * the board internally, knows if the game is over, produces a final score.
 *
 * @author ahv
 */
public class Game {

    private GameState state;
    private final Board board;
    private final GameSettings settings;
    private final StopWatch time;
    private final UserInterface ui;

    /**
     * Constructor for custom games, values are validated internally in a
     * GameSettings object.
     *
     * @param ui
     * @param mines Amount of mines in the game, between 1 and total amount of
     * grids.
     * @param width Width of the game board.
     * @param height Height of the game board.
     *
     * @see asd.fgh.minesweeper.logic.GameSettings#GameSettings(int, int, int)
     */
    public Game(UserInterface ui, int mines, int width, int height) {
        this(ui, new GameSettings(mines, width, height));
    }

    /**
     * Constructor for preset games, values defined in GameSettings class.
     *
     * @param ui
     * @param difficulty Enum to identify a preset in GameSettings.
     *
     * @see
     * asd.fgh.minesweeper.logic.GameSettings#generatePreset(asd.fgh.minesweeper.logic.Difficulty)
     */
    public Game(UserInterface ui, Difficulty difficulty) {
        this(ui, GameSettings.generatePreset(difficulty));
    }

    private Game(UserInterface ui, GameSettings s) {
        this.settings = s;
        this.state = GameState.READY;
        this.board = new Board(s.getMines(), s.getWidth(), s.getHeight());
        this.time = new StopWatch();
        this.ui = ui;
    }

    public void start() {
        while (!hasEnded()) {
            ui.updateElapsedTime(time.getElapsedTime()); // TODO: Unnecessarily spammy loop
        }
    }

    // Used in building the UI
    public GameSettings getSettings() {
        return settings;
    }

    boolean hasEnded() {
        return (this.state == GameState.LOST || this.state == GameState.WON);
    }

    /**
     * Player input to attempt to open a grid in the game.
     *
     * @param x X-coordinate.
     * @param y Y-coordinate.
     */
    public void openGridAt(int x, int y) {
        if (!preCheck()) {
            return;
        }
        ArrayList<Grid> touched = board.openGridAt(x, y);
        containsMine(touched);
        ui.updateGridView(touched);
    }

    /**
     * Places or removes a flag on an unrevealed grid.
     *
     * @param x X-coordinate.
     * @param y Y-coordinate.
     */
    public void flagGridAt(int x, int y) {
        if (preCheck()) {
            Grid g = board.flipFlag(x, y);
            // TODO: Overkill... a whole
            ArrayList<Grid> l = new ArrayList<>();
            l.add(g);
            ui.updateGridView(l);
        }
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
        ArrayList<Grid> touched = board.openAdjacentsAt(x, y);
        containsMine(touched);
        ui.updateGridView(touched);
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

    private boolean containsMine(ArrayList<Grid> list) {
        for (Grid g : list) {
            if (g.getState() == GridState.MINED) {
                return true;
            }
        }
        return false;
    }
}
