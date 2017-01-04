package asd.fgh.minesweeper.ui;

import asd.fgh.minesweeper.logic.Game;
import asd.fgh.minesweeper.logic.GameSettings;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Panel;
import javax.swing.BoxLayout;

public class GameFrame extends Frame {
    
    private final Game game;
    private final GameButton[][] grid;
    private final Label timeLabel;
    private final Label mineLabel;
    
    public GameFrame(GameSettings s) throws HeadlessException, Exception {
        this.game = new Game(s);
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
        Panel minePanel = new Panel(new GridLayout(w, h));
        this.grid = new GameButton[w][h];
        int x, y;
        for (int i = 0; i < w*h; i++){
            x = i % w;
            y = i / w;
            // TODO: Seems pretty badly coupled but maybe we'll live.
            GameButton b = new GameButton(this, game, x, y);
            b.addMouseListener(b);
            this.grid[x][y] = b;
            minePanel.add(b);
        }
        minePanel.setSize(40*h, 40*w);
        add(minePanel);
        
        setSize(40*h+20, 40*w);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    // TODO: Sketchy
    public void updateView(){
        int[][] snap = game.getBoardSnapshot();
        for (int x = 0; x < snap.length; x++) {
            for (int y = 0; y < snap[0].length; y++) {
                this.grid[x][y].setVisualState(snap[x][y]);
            }
        }
    }
}
