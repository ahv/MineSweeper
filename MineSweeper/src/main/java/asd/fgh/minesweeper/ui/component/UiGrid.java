package asd.fgh.minesweeper.ui.component;

import asd.fgh.minesweeper.logic.Game;
import asd.fgh.minesweeper.logic.data.GridState;
import asd.fgh.minesweeper.ui.GameFrame;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * UI component, representing a grid.
 * @author ahv
 */
public class UiGrid extends Panel {

    private final Button b;
    private final Label l;

    public UiGrid(GameFrame frame, Game game, int x, int y) {
        setLayout(new GridLayout());
        setBackground(Color.LIGHT_GRAY);
        b = new Button();
        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                if (me.getButton() == MouseEvent.BUTTON3) {
                    game.flagGridAt(x, y);
                } else if (me.getButton() == MouseEvent.BUTTON1) {
                    game.openGridAt(x, y);
                }
            }
        });
        add(b);
        b.setVisible(true);
        l = new Label("", Label.CENTER);

        l.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                if (me.getButton() == MouseEvent.BUTTON3) {
                    game.openAdjacentsAt(x, y);
                }
            }
        });
    }

    public void setVisualState(GridState state) {
        switch (state) {
            case FLAGGED:
            case UNREVEALED:
                b.setLabel(GridState.stateToSymbol(state) + "");
                break;
            default:
                revealGridAs(state);
                break;
        }
    }

    private void revealGridAs(GridState state) {
        remove(b);
        add(l);
        l.setVisible(true);
        if (state == GridState.MINED) {
            l.setBackground(Color.RED);
        }
        l.setText(GridState.stateToSymbol(state) + "");
        l.setForeground(GridState.stateToColor(state));
    }
}
