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
        if (i == -2) setLabel("!"); // flagged
        else if (i == -1) setLabel(" "); // unrevealed
        else if (i == 9) setLabel("*"); // mined
        else setLabel(i + ""); // touched mines amount

    }

    // TODO: Right click doesn't seem to always register... maybe it's just my mouse dying.
    @Override
    public void mouseClicked(MouseEvent me) {
        // BUTTON2 appears to be scrollwheel.
        if (me.getButton() == MouseEvent.BUTTON3) game.flag(x, y);
        else if (me.getButton() == MouseEvent.BUTTON1) game.open(x, y);
        frame.updateView();
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
