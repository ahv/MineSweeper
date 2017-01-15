package asd.fgh.minesweeper.persistence;

import asd.fgh.minesweeper.logic.Difficulty;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton class to record high scores. Has persistence.
 *
 * @author ahv
 */
public class HighScores implements Serializable {

    private static final HighScores SCORES = load();

    private static HighScores load() {
        try {
            File scoreFile = new File("scores");
            if (scoreFile.exists()) {
                FileInputStream fis = new FileInputStream(scoreFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                HighScores scores = (HighScores) ois.readObject();
                return scores;
            } else {
                return new HighScores();
            }
        } catch (IOException | ClassNotFoundException ex) {
            return new HighScores();
        }
    }

    // TODO: Let the UI know that a file can't be saved
    private static void save() {
        try {
            File scoreFile = new File("scores");
            scoreFile.createNewFile(); // Creates a new file only if one doesn't exist already.
            FileOutputStream fos = new FileOutputStream(scoreFile);
            ObjectOutputStream ous = new ObjectOutputStream(fos);
            ous.writeObject(SCORES);
        } catch (IOException ex) {
            Logger.getLogger(HighScores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private final ArrayList<Score> beginner;
    private final ArrayList<Score> intermediate;
    private final ArrayList<Score> advanced;

    // private because we're a singleton!
    private HighScores() {
        beginner = new ArrayList<>();
        intermediate = new ArrayList<>();
        advanced = new ArrayList<>();
    }

    /**
     * Checks if a score is eligible for a position in high scores.
     *
     * @param score Score object from a finished Game.
     * @return True if score qualifies for a spot in high scores.
     */
    public static boolean isEligible(Score score) {
        if (score.getDifficulty() == null) {
            return false;
        }
        ArrayList<Score> listToCheck = null;
        // TODO: Crude as hell, but good enough for now
        switch (score.getDifficulty()) {
            case BEGINNER:
                listToCheck = SCORES.beginner;
                break;
            case ADVANCED:
                listToCheck = SCORES.advanced;
                break;
            case INTERMEDIATE:
                listToCheck = SCORES.intermediate;
                break;
            default:
                break;
        }
        if (listToCheck.size() < 4) { // TODO: Hardcoded score list length here...
            return true;
        }
        for (Score s : listToCheck) {
            if (score.getTime() < s.getTime()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Inserts a high score to the high score boards after checking if eligible.
     * Saves the score to disk after each new entry.
     *
     *
     * @param score Score object from a finished game.
     */
    public static void enterScore(Score score) {
        if (!HighScores.isEligible(score)) {
            return;
        }
        // TODO: copy-pasted shit here
        ArrayList<Score> list = null;
        switch (score.getDifficulty()) {
            case BEGINNER:
                list = SCORES.beginner;
                break;
            case ADVANCED:
                list = SCORES.advanced;
                break;
            case INTERMEDIATE:
                list = SCORES.intermediate;
                break;
            default:
                break;
        }
        if (list != null) {
            list.add(score);
            Collections.sort(list);
            // TODO: Terrible hardcoded highscore list size stuff here
            while (list.size() > 3) {
                list.remove(3);
            }
        }
        save();
    }

    /**
     * Gets a score list matching a difficulty, useful in the UI when showing
     * scoreboards.
     *
     * @param difficulty Difficulty to get scores for.
     * @return List of scores matching the parameter difficulty.
     */
    public static ArrayList<Score> getScoresFor(Difficulty difficulty) {
        switch (difficulty) {
            case BEGINNER:
                return SCORES.beginner;
            case INTERMEDIATE:
                return SCORES.intermediate;
            case ADVANCED:
                return SCORES.advanced;
            default:
                return new ArrayList<>();
        }
    }
}
