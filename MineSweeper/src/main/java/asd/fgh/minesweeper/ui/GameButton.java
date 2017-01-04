package asd.fgh.minesweeper.ui;

import asd.fgh.minesweeper.logic.Game;
import java.awt.Button;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameButton extends Button implements MouseListener {

    private final Game game;
    private final GameFrame frame;
    private final int x;
    private final int y;
    
    public GameButton(GameFrame frame, Game game, int x, int y) throws HeadlessException {
        this.game = game;
        this.x = x;
        this.y = y;
        this.frame = frame;
    }
    
    void setVisualState(int i) {
        setLabel(i + "");
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        /*
        - check which button
        - what's the state of the grid
        
        */
        game.open(x, y);
        frame.updateView();
        System.out.println("Clicked: " + x + ", " + y);
        System.out.println("Game State: " + game.getState());
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) { 
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
