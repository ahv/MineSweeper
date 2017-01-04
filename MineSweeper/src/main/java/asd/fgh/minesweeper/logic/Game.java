package asd.fgh.minesweeper.logic;

import asd.fgh.minesweeper.logic.data.Board;

public class Game {

    private GameState state;
    private final Board board;
    private final GameSettings settings;

    public Game(GameSettings settings) {
        this.settings = settings;
        this.state = GameState.READY;
        this.board = new Board(settings);
    }

    // WARNING: Temporarily exposed Board for tests.
    public Board getBoard() {
        return board;
    }
}
