package asd.fgh.minesweeper.ui;

import asd.fgh.minesweeper.logic.Game;
import asd.fgh.minesweeper.logic.GameSettings;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Panel;
import javax.swing.BoxLayout;

public class GameFrame extends Frame {
    
    private Game game;
    private Panel mineGridPanel;
    private Button[][] grid;
    private Label timeLabel;
    private Label mineLabel;
    
    // Create the preliminary game frame.
    public GameFrame() throws HeadlessException, Exception {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        Panel topPanel = new Panel(new FlowLayout());
        this.timeLabel = new Label();
        this.mineLabel = new Label();
        topPanel.add(timeLabel);
        topPanel.add(mineLabel);
        topPanel.setSize(200, 20);
        add(topPanel);
        
        Panel mineGridEmptyPanel = new Panel(new FlowLayout());
        mineGridEmptyPanel.setSize(200, 200);
        this.mineGridPanel = mineGridEmptyPanel;
        this.add(mineGridPanel);
        
        setSize(200, 220);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void newGame(GameSettings s){
        this.game = new Game(s);
        
    }
    
    private Panel createGridPanel(GameSettings s) {
        int w = s.getWidth();
        int h = s.getHeight();
        Panel p = new Panel(new GridLayout(w, h));
        this.mineGridPanel = p;
        this.grid = new Button[w][h];

        int x, y;
        for (int i = 0; i < w*h; i++){
            x = i % w;
            y = i / w;
            Button b = new Button();
            this.grid[x][y] = b;
            p.add(b);
        }
        p.setSize(40*w, 40*w+20);
        return p;   
    }
}
