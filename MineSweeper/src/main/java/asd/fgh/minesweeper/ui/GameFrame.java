package asd.fgh.minesweeper.ui;

import asd.fgh.minesweeper.ui.component.UiGrid;
import asd.fgh.minesweeper.logic.Game;
import asd.fgh.minesweeper.logic.GameSettings;
import asd.fgh.minesweeper.logic.UserInterface;
import asd.fgh.minesweeper.logic.data.Grid;
import asd.fgh.minesweeper.persistence.HighScores;
import asd.fgh.minesweeper.persistence.Score;
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
import java.util.ArrayList;
import javax.swing.BoxLayout;

/**
 * Frame to visually represent an instance of a Game.
 *
 * @author ahv
 */
public class GameFrame extends Frame implements UserInterface {

    private final Game game;
    private final UiGrid[][] grid;
    private final Label timeLabel;
    private final Label mineLabel;
    private final Main main;

    public GameFrame(Main main, Game game) throws HeadlessException {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setTitle("Minsweeper");
        setFont(new Font("Arial", Font.BOLD, 16));

        this.main = main;
        this.game = game;
        game.setUserInterface(this);
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
        this.grid = new UiGrid[w][h];
        int x, y;
        for (int i = 0; i < w * h; i++) {
            x = i % w;
            y = i / w;
            UiGrid g = new UiGrid(this, game, x, y);
            this.grid[x][y] = g;
            minePanel.add(g);
        }
        add(minePanel);

        setSize(32 * w, 40 * h + 20);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }

    @Override
    public void updateGridView(ArrayList<Grid> changedGrids) {
        for (Grid g : changedGrids) {
            grid[g.getX()][g.getY()].setVisualState(g.getState());
        }
        validate();
        repaint();
    }

    @Override
    public void updateElapsedTime(int elapsedTime) {
        timeLabel.setText("Time: " + elapsedTime);
    }

    @Override
    public void handleEndedGame(Score score, GameSettings previousSettings, boolean won) {
        if (won && HighScores.isEligible(score)) {
            main.showNewHighScoreScreen(score, previousSettings);
        } else {
            main.showEndScreen(score, previousSettings, won);
        }
    }
}
