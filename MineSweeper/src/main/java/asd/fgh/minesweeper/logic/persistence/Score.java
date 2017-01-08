package asd.fgh.minesweeper.logic.persistence;

import asd.fgh.minesweeper.logic.Difficulty;

public class Score {

    private final Difficulty difficulty;
    private final int time;
    public String name;

    public Score(Difficulty difficulty, int time) {
        this.difficulty = difficulty;
        this.time = time;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getTime() {
        return time;
    }

    public String getName() {
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
