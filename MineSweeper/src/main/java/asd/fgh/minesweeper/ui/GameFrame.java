package asd.fgh.minesweeper.ui;

import asd.fgh.minesweeper.logic.Difficulty;
import asd.fgh.minesweeper.logic.Game;
import asd.fgh.minesweeper.logic.GameSettings;
import asd.fgh.minesweeper.logic.persistence.HighScores;
import asd.fgh.minesweeper.logic.persistence.Score;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Panel;
import javax.swing.BoxLayout;

/**
 * Frame to visually represent an instance of a Game.
 * @author ahv
 */
public class GameFrame extends Frame {

    private final Game game;
    //private final GameButton[][] grid;
    private final GameGrid[][] grid;
    private final Label timeLabel;
    private final Label mineLabel;
    private final Main main;

    public GameFrame(Main main, Difficulty difficulty) throws HeadlessException {
        this.main = main;
        this.game = new Game(difficulty);
        GameSettings s = game.getSettings();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Panel topPanel = new Panel(new FlowLayout());
        this.timeLabel = new Label("Time: 0");
        this.mineLabel = new Label("Mines: " + s.getMines());
        topPanel.add(timeLabel);
        topPanel.add(mineLabel);
        topPanel.setSize(20, 200);
        add(topPanel);

        int w = s.getWidth();
        int h = s.getHeight();
        Panel minePanel = new Panel(new GridLayout(w, h, 1, 1));
        this.grid = new GameGrid[w][h];
        int x, y;
        for (int i = 0; i < w * h; i++) {
            x = i % w;
            y = i / w;
            // TODO: Seems pretty badly coupled but maybe we'll live.
            GameGrid g = new GameGrid(this, game, x, y);
            this.grid[x][y] = g;
            minePanel.add(g);
        }
        minePanel.setSize(40 * h, 40 * w);
        add(minePanel);

        setSize(40 * h + 20, 40 * w);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // TODO: Sketchy method
    /**
     * Gets a snapshot from the game object and updates the look accordingly.
     * @throws Exception 
     */
    public void updateView() {
        if (game.hasEnded()) {
            //main.startGame(Difficulty.BEGINNER);
            handleGameEnd(); // TODO: Show entire board
        }
        int[][] snap = game.getBoardSnapshot();
        for (int x = 0; x < snap.length; x++) {
            for (int y = 0; y < snap[0].length; y++) {
                this.grid[x][y].setVisualState(snap[x][y]);
            }
        }
        validate();
        repaint();
    }

    // TODO: UI is handling score logic, tsk tsk
    private void handleGameEnd() {
        if (game.isWon()){
            Score s = game.getFinalScore();
            HighScores.isEligible(s);
            // spawn name entry score window
        } else {
        }
        main.showEndScreen();
    }
}
