package asd.fgh.minesweeper.ui;

import asd.fgh.minesweeper.logic.Difficulty;

/**
 * Class to facilitate switching between "screens",
 * i.e. different frames -- that get created and destroyed.
 * 
 * @author ahv
 */
public class Main {

    private GameFrame gameFrame;
    private GameEndDialog gameEndDialog;

    // TODO: Implement score and game set up
    public static void main(String[] args) throws Exception {
        Main m = new Main();
        // TODO: Check if previous gamesetting exists on disk and use that?
        m.startGame(Difficulty.BEGINNER);
    }

    public void startGame(Difficulty difficulty) {
        if (gameFrame != null) {
            gameFrame.dispose();
        }
        if (gameEndDialog != null){
            gameEndDialog.dispose();
        }
        this.gameFrame = new GameFrame(this, difficulty);
    }
    
    public void showEndScreen(){
        gameEndDialog = new GameEndDialog(this);
    }
}
