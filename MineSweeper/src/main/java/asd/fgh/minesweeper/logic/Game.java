package asd.fgh.minesweeper.logic;

import asd.fgh.minesweeper.logic.data.Board;

public class Game {

    // TODO: Implement clock
    private GameState state;
    private final Board board;
    private final GameSettings settings;
    private final Clock clock;
    
    public Game(int mines, int width, int height){
        this(new GameSettings(mines, width, height));
    }

    public Game(Difficulty difficulty) throws Exception {
        this(GameSettings.generatePreset(difficulty));
    }
    
    private Game(GameSettings s){
        this.settings = s;
        this.state = GameState.READY;
        this.board = new Board(s.getMines(), s.getWidth(), s.getHeight());
        this.clock = new Clock();
    }
    
    public GameSettings getSettings() {
        return settings;
    }

    public boolean hasGameEnded() {
        return (this.state == GameState.LOST || this.state == GameState.WON);
    }

    public void openGridAt(int x, int y) {
        if (hasGameEnded()) return;
        // Start clock on first open.
        if (state == GameState.READY) {
            this.clock.start();
            this.state = GameState.RUNNING;
        }
        board.openGridAt(x, y);
        if (board.isMined(x, y)) finishGame(GameState.LOST);
        else if (board.isCompletelyExplored()) finishGame(GameState.WON);
    }

    public void flagGridAt(int x, int y) {
        if (!hasGameEnded()) board.flipFlag(x, y);
    }

    // TODO: Scoreboard object?
    private void finishGame(GameState result) {
        this.state = result;
        this.clock.stop();
    }

    // TODO: Pretty obscure int based implementation, redo.
    // -2 flagged, -1 unrevealed, 0..8 touching mines, 9 mine
    public int[][] getBoardSnapshot() {
        int[][] snap = new int[board.getWidth()][board.getHeight()];
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if (board.isFlagged(x, y)){
                    snap[x][y] = -2;
                } else if (!board.isRevealed(x, y)){
                    snap[x][y] = -1;
                } else if (board.isMined(x, y)){
                    snap[x][y] = 9;
                } else {
                    snap[x][y] = board.touchedMines(x, y);
                }
                
            }
        }
        return snap;
    }
}
