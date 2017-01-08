package asd.fgh.minesweeper.persistence;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Serializable class to record high scores.
 *
 * @author ahv
 */
public class HighScores implements Serializable {

    private ArrayList<Score> beginnerScores;
    private ArrayList<Score> intermediateScores;
    private ArrayList<Score> advancedScores;

    public HighScores() {
    }

    /**
     * Checks if a score is eligible for a position in high scores.
     *
     * @param s Score object from a finished Game.
     * @return True if score qualifies for a spot in high scores.
     */
    public static boolean isEligible(Score s) {
        // TODO: Return true if score is eligible for a position
        return true;
    }

    /**
     * Inserts a high score to the high score boards, checks if eligible
     * internally.
     *
     * @param s Score object from a finished game.
     */
    public static void enterScore(Score s) {
        if (!HighScores.isEligible(s)) {
            return;
        }
        // TODO: Blah
    }
}
