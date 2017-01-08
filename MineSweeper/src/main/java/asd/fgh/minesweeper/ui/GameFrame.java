package asd.fgh.minesweeper.ui;

import asd.fgh.minesweeper.ui.component.GameGrid;
import asd.fgh.minesweeper.logic.Difficulty;
import asd.fgh.minesweeper.logic.Game;
import asd.fgh.minesweeper.logic.GameSettings;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;

/**
 * Frame to visually represent an instance of a Game.
 *
 * @author ahv
 */
public class GameFrame extends Frame {

    private final Game game;
    private final GameGrid[][] grid;
    private final Label timeLabel;
    private final Label mineLabel;
    private final Main main;

    public GameFrame(Main main, Difficulty difficulty) throws HeadlessException {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setTitle("Minsweeper");
        setFont(new Font("Arial", Font.BOLD, 16));

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
        Panel minePanel = new Panel(new GridLayout(h, w, 1, 1));
        minePanel.setBackground(Color.GRAY);
        this.grid = new GameGrid[w][h];
        int x, y;
        for (int i = 0; i < w * h; i++) {
            x = i % w;
            y = i / w;
            GameGrid g = new GameGrid(this, game, x, y);
            this.grid[x][y] = g;
            minePanel.add(g);
        }
        //minePanel.setSize(40 * w, 40 * h);
        add(minePanel);

        setSize(32 * w, 40 * h + 20);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    // TODO: Sketchy method
    /**
     * Gets a snapshot from the game object and updates the look accordingly.
     *
     */
    public void updateView() {
        if (game.hasEnded()) {
            handleGameEnd(); // TODO: Show entire board on end
        }
        int[][] snap = game.getBoardSnapshot();
        for (int x = 0; x < snap.length; x++) {
            for (int y = 0; y < snap[0].length; y++) {
                this.grid[x][y].setVisualState(snap[x][y]);
            }
        }
        // TODO: This should update "live"
        this.timeLabel.setText("Time: " + game.getElapsedTime());
        validate();
        repaint();
    }

    // TODO: UI is handling score logic, tsk tsk
    private void handleGameEnd() {
        main.showEndScreen(game.getFinalScore());
    }
}
