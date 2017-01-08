package asd.fgh.minesweeper.persistence;

import asd.fgh.minesweeper.logic.Difficulty;

/**
 * Produced from a game instance, describes the result of a game.
 *
 * @author ahv
 */
public class Score {

    private final Difficulty difficulty;
    private final int time;
    public String name;
    private final boolean won;

    public Score(Difficulty difficulty, int time, boolean won) {
        this.difficulty = difficulty;
        this.time = time;
        this.won = won;
    }

    public boolean isWon() {
        return won;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getTime() {
        return time;
    }

    /**
     * Name associated with the score.
     *
     * @return Name or "Unknown" if no name has been entered.
     */
    public String getName() {
        if (name == null) {
            return "Unknown";
        }
        return name;
    }

    // Can only set once.
    public void setName(String name) {
        if (name == null) {
            // TODO: Validate
            this.name = name;
        }
    }
}
