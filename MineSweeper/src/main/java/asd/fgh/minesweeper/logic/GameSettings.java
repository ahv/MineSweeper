package asd.fgh.minesweeper.logic;

/**
 * Generates preset game settings, and validates custom game settings.
 * With package-private constructor and a factory method can only be
 * instantiated in asd.fgh.minesweeper.logic package, but its class declaration
 * being public, and it having public getters can also be used when setting up an UI.
 * 
 * A Game object carries its settings around and has a method to access them.
 * 
 * @see asd.fgh.minesweeper.logic.Game#getSettings() 
 * 
 * @author ahv
 */

public class GameSettings {

    public final static int MINIMUM_WIDTH = 4;
    public final static int MAXIMUM_WIDTH = 30;
    public final static int MINIMUM_HEIGHT = 4;
    public final static int MAXIMUM_HEIGHT = 16;

    private final int mines;
    private final int width;
    private final int height;
    private final Difficulty difficulty; // TODO: difficulty is null with custom games

    // TODO: Study material 
    static GameSettings generatePreset(Difficulty difficulty) {
        switch (difficulty) {
            case BEGINNER:
                return new GameSettings(10, 9, 9, difficulty);
            case INTERMEDIATE:
                return new GameSettings(40, 16, 16, difficulty);
            case ADVANCED:
                return new GameSettings(99, 30, 16, difficulty);
            default:
                // TODO: Silently returns a beginner instance when no preset is defined, is this ok?
                return generatePreset(Difficulty.BEGINNER);
        }
    }

    GameSettings(int mines, int width, int height) {
        this(mines, width, height, null);
    }

    private GameSettings(int mines, int width, int height, Difficulty difficulty) {
        // Validify parameters
        width = clamp(width, MINIMUM_WIDTH, MAXIMUM_WIDTH);
        height = clamp(height, MINIMUM_HEIGHT, MAXIMUM_HEIGHT);
        mines = clamp(mines, 1, width * height);
        this.mines = mines;
        this.width = width;
        this.height = height;
        this.difficulty = difficulty;
    }

    private int clamp(int value, int min, int max) {
        return (value > max) ? max : (value < min ? min : value);
    }

    public int getMines() {
        return mines;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
