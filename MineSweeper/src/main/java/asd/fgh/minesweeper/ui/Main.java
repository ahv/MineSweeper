package asd.fgh.minesweeper.ui;

import asd.fgh.minesweeper.logic.Difficulty;
import asd.fgh.minesweeper.persistence.Score;

/**
 * Class to facilitate switching between "screens", i.e. different frames --
 * that get created and destroyed.
 *
 * @author ahv
 */
public class Main {

    private GameFrame gameFrame;
    private EndFrame endFrame;
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
        if (endFrame != null) {
            endFrame.dispose();
        }
        this.gameFrame = new GameFrame(this, difficulty);
    }

    public void showEndScreen(Score result, boolean won) {
        gameFrame.setEnabled(false);
        endFrame = new EndFrame(this, result, won);
    }

    public void showStartFrame() {
        if (endFrame != null) {
            endFrame.dispose();
        }
        startFrame.setVisible(true);
    }

}
