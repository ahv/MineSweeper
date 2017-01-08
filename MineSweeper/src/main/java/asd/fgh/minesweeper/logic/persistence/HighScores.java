package asd.fgh.minesweeper.logic.persistence;

import java.io.Serializable;

public class HighScores implements Serializable {

    public static boolean isEligible(Score s) {
        // TODO: Return true if score is eligible for a position
        return true;
    }

    public static void enterScore(Score s) {
        if (!HighScores.isEligible(s)) {
            return;
        }
        // TODO: Blah
    }
}
