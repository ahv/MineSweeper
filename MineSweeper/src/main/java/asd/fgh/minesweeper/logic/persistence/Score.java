package asd.fgh.minesweeper.logic.persistence;

import asd.fgh.minesweeper.logic.Difficulty;

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

    public String getName() {
        if (name == null) return "Unknown";
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
