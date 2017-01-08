package asd.fgh.minesweeper.ui.component;

import asd.fgh.minesweeper.logic.Game;
import asd.fgh.minesweeper.ui.GameFrame;
import java.awt.Button;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Extended Button component for GameFrame.
 *
 * @author ahv
 */
class GameButton extends Button implements MouseListener {

    private final Game game;
    private final GameFrame frame;
    private final int x;
    private final int y;

    GameButton(GameFrame frame, Game game, int x, int y) throws HeadlessException {
        this.game = game;
        this.x = x;
        this.y = y;
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if (game.hasEnded()) {
            return;
        }
        if (me.getButton() == MouseEvent.BUTTON3) {
            //System.out.println("Right click.");
            game.flagGridAt(x, y);
        } else if (me.getButton() == MouseEvent.BUTTON1) {
            //System.out.println("Left click.");
            game.openGridAt(x, y);
        }
        try {
            frame.updateView();
        } catch (Exception ex) {
            Logger.getLogger(GameButton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
