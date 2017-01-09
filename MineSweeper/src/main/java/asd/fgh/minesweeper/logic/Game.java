package asd.fgh.minesweeper.logic;

import asd.fgh.minesweeper.logic.data.Board;
import asd.fgh.minesweeper.logic.data.Grid;
import asd.fgh.minesweeper.logic.data.GridState;
import asd.fgh.minesweeper.persistence.Score;
import java.util.ArrayList;

/**
 * The UI facing class; has the required methods for playing the game, generates
 * the board internally, knows if the game is over, produces a final score.
 *
 * @author ahv
 */
public class Game {

    private final Board board;
    private final GameSettings settings;
    private final StopWatch time;
    private UserInterface ui;

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
        this.board = new Board(s.getMines(), s.getWidth(), s.getHeight());
        this.time = new StopWatch();
    }

    // Used in building the UI
    public GameSettings getSettings() {
        return settings;
    }

    public void setUserInterface(UserInterface ui) {
        this.ui = ui;
    }

    /**
     * Player input to attempt to open a grid in the game.
     *
     * @param x X-coordinate.
     * @param y Y-coordinate.
     */
    public void openGridAt(int x, int y) {
        if (!time.isRunning()) {
            time.start();
        }
        handleMoveResult(board.openGridAt(x, y));
    }

    /**
     * Places or removes a flag on an unrevealed grid.
     *
     * @param x X-coordinate.
     * @param y Y-coordinate.
     */
    public void flagGridAt(int x, int y) {
        Grid g = board.flipFlag(x, y);
        // TODO: Overkill... a whole list for one element
        ArrayList<Grid> l = new ArrayList<>();
        l.add(g);
        ui.updateGridView(l);
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
        handleMoveResult(board.openAdjacentsAt(x, y));
    }

    private boolean listContainsMine(ArrayList<Grid> list) {
        for (Grid g : list) {
            if (g.getState() == GridState.MINED) {
                return true;
            }
        }
        return false;
    }

    private void handleMoveResult(ArrayList<Grid> touched) {
        if (!time.isRunning()) {
            time.start();
        }
        if (listContainsMine(touched)) {
            ui.handleEndedGame(new Score(settings.getDifficulty(), time.getElapsedTime()), settings, false);
        } else if (board.isCompletelyExplored()) {
            Score score = new Score(settings.getDifficulty(), time.getElapsedTime());
            ui.handleEndedGame(score, settings, true);
        }
        ui.updateGridView(touched);
    }
}
