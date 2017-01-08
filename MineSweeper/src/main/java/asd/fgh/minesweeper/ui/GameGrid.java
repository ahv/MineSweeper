package asd.fgh.minesweeper.ui;

import asd.fgh.minesweeper.logic.Game;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameGrid extends Panel {

    private final GameButton b;
    private final Label l;
    private final Game game;
    private final int x;
    private final int y;

    GameGrid(GameFrame frame, Game game, int x, int y) {
        this.x = x;
        this.y = y;
        this.game = game;
        setLayout(new GridLayout());
        setBackground(Color.LIGHT_GRAY);
        b = new GameButton(frame, game, x, y);
        b.addMouseListener(b);
        add(b);
        b.setVisible(true);
        l = new Label("", Label.CENTER);
        
        // TODO: Refactor this mess
        l.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (game.hasEnded()) return;
                if (me.getButton() == MouseEvent.BUTTON2) {
                    game.openAdjacentsAt(x, y);
                    try {
                        frame.updateView();
                    } catch (Exception ex) {
                        Logger.getLogger(GameButton.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
        });
    }

    void setVisualState(int i) {
        if (i == -2) {
            b.setLabel("!"); // flagged
        } else if (i == -1) {
            b.setLabel(" "); // unrevealed
        } else if (i == 9) {
            remove(b);
            add(l);
            l.setVisible(true);
            l.setText("*");
        } else if (i == 0) { // revealed
            remove(b);
            add(l);
            l.setVisible(true);
            l.setText(" ");
        } else {
            remove(b);
            add(l);
            l.setVisible(true);
            l.setText(i + ""); // touched mines amount
        }
    }
}
