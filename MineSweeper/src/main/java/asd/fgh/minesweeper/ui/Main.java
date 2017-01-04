package asd.fgh.minesweeper.ui;

import asd.fgh.minesweeper.logic.Difficulty;

public class Main {

    private GameFrame gameFrame;

    // TODO: Implement score and game set up
    public static void main(String[] args) throws Exception {
        Main m = new Main();
        // TODO: Check if previous gamesetting exists on disk and use that?
        m.startGame(Difficulty.BEGINNER);
    }

    public void startGame(Difficulty difficulty) throws Exception {
        if (gameFrame != null) {
            gameFrame.dispose();
        }
        this.gameFrame = new GameFrame(this, difficulty);
    }
}
