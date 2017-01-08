package asd.fgh.minesweeper.ui;

import asd.fgh.minesweeper.logic.Game;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Extended Button component for GameFrame.
 *
 * @author ahv
 */
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
        if (i == -2) {
            setLabel("!"); // flagged
        } else if (i == -1) {
            setLabel(" "); // unrevealed
        } else if (i == 9) {
            setLabel("*"); // mined
        } else if (i == 0) {
            //this.setBackground(Color.yellow);
            setLabel("_");
        } else {
            setLabel(i + ""); // touched mines amount
        }
    }

    // TODO: Clicks don't seem to always register!
    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON2) { // BUTTON2 is scrollwheel button.
            // TODO: Use both buttons click for this behaviour instead.
            game.openAdjacentsAt(x, y);
        } else if (me.getButton() == MouseEvent.BUTTON3) {
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
