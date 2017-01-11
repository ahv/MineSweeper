package asd.fgh.minesweeper.logic;

import asd.fgh.minesweeper.logic.data.Grid;
import asd.fgh.minesweeper.persistence.Score;
import java.util.ArrayList;

/**
 * Interface to implement by a class to be usable as an user interface in the
 * Game.
 *
 * @author ahv
 */
public interface UserInterface {

    /**
     * Update elapsed game time in an user interface.
     *
     * @param elapsedTime The value to show in ui.
     */
    void updateElapsedTime(int elapsedTime);

    /**
     * Update the visual representation of the game state.
     *
     * @param changedGrids Grids that should be changed visually.
     */
    void updateGridView(ArrayList<Grid> changedGrids);

    /**
     * Called when the game has ended.
     *
     * @param score Final result of the game.
     * @param settings The settings that the game was using.
     * @param wasVictory True if the game was won.
     */
    void handleEndedGame(Score score, GameSettings settings, boolean wasVictory);
}
