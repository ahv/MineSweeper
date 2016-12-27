package asd.fgh.minesweeper.logic;

import asd.fgh.minesweeper.logic.data.Board;

public class Game {

    private boolean alive;
    private final Board board;
    private final Difficulty difficulty;

    // TODO: Using CUSTOM difficulty causes an Exception! Use null to signify CUSTOM difficulty instead?
    // TODO: Game difficulty presets are in a somewhat obscure place hidden in this class. Refactor? (hard coded values in tests because of this!)
    public static Game generatePresetGame(Difficulty difficulty) throws Exception {
        switch (difficulty) {
            case BEGINNER:
                return new Game(10, 9, 9, difficulty);
            case INTERMEDIATE:
                return new Game(40, 16, 16, difficulty);
            case ADVANCED:
                return new Game(99, 30, 16, difficulty);
            default:
                throw new Exception("Invalid difficulty!");
        }
    }

    private Game(int mines, int width, int height, Difficulty difficulty) {
        this.board = new Board(mines, width, height);
        this.alive = true;
        this.difficulty = Difficulty.CUSTOM;
    }

    public Game(int mines, int width, int height) {
        this(mines, width, height, Difficulty.CUSTOM);
    }

    // WARNING: Temporarily exposed Board for tests.
    public Board getBoard() {
        return board;
    }
}
