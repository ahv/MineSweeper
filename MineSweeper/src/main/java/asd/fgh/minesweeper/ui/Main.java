package asd.fgh.minesweeper.ui;

import asd.fgh.minesweeper.logic.Difficulty;
import asd.fgh.minesweeper.logic.GameSettings;

/**
 * Class to facilitate switching between "screens", i.e. different frames --
 * that get created and destroyed.
 *
 * @author ahv
 */
public class Main {

    private GameFrame gameFrame;
    private GameEndFrame gameEndDialog;
    private final StartFrame startFrame;

    // TODO: Implement score and game set up
    public static void main(String[] args) throws Exception {
        Main m = new Main();
        m.showStartFrame();
    }

    public Main() {
        startFrame = new StartFrame(this);
    }

    public void startGame(Difficulty difficulty) {
        startFrame.setVisible(false);
        if (gameFrame != null) {
            gameFrame.dispose();
        }
        if (gameEndDialog != null) {
            gameEndDialog.dispose();
        }
        this.gameFrame = new GameFrame(this, difficulty);
    }

    public void showEndScreen(GameSettings settings) {
        gameEndDialog = new GameEndFrame(this, settings);
    }

    public void showStartFrame() {
        if (gameEndDialog != null){
            gameEndDialog.dispose();
        }
        startFrame.setVisible(true);
    }

}
