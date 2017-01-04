package asd.fgh.minesweeper.logic;

public class GameSettings {

    public final static int MINIMUM_WIDTH = 4;
    public final static int MAXIMUM_WIDTH = 30;
    public final static int MINIMUM_HEIGHT = 4;
    public final static int MAXIMUM_HEIGHT = 16;

    private final int mines;
    private final int width;
    private final int height;
    private final Difficulty difficulty;

    public static GameSettings generatePreset(Difficulty difficulty) throws Exception {
        switch (difficulty) {
            case BEGINNER:
                return new GameSettings(10, 9, 9, difficulty);
            case INTERMEDIATE:
                return new GameSettings(40, 16, 16, difficulty);
            case ADVANCED:
                return new GameSettings(99, 30, 16, difficulty);
            case CUSTOM:
                throw new Exception("Use constructor with custom difficulty instead!");
            default:
                throw new Exception("Undefined preset difficulty!");
        }
    }

    public GameSettings(int mines, int width, int height) {
        this(mines, width, height, Difficulty.CUSTOM);
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
