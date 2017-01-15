package asd.fgh.minesweeper.ui;

import asd.fgh.minesweeper.logic.GameSettings;
import asd.fgh.minesweeper.persistence.HighScores;
import asd.fgh.minesweeper.persistence.Score;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NameEntryFrame extends Frame {

    private final Main main;
    private final Score score;
    private final GameSettings previousSettings;

    public NameEntryFrame(Main main, Score score, GameSettings previousSettings) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.main = main;
        this.score = score;
        this.previousSettings = previousSettings;
        setTitle("New highscore!");
        setLayout(new GridLayout(2, 1));
        setSize(250, 100);
        setLocationRelativeTo(null);
        setResizable(false);
        TextField nameField = new TextField();
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    submit(nameField.getText());
                }
            }
        });
        add(nameField);
        Button submitButton = new Button("submit");
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                submit(nameField.getText());
            }
        });
        add(submitButton);
        setVisible(true);
    }

    private void submit(String name) {
        score.setName(name);
        HighScores.enterScore(score);
        dispose();
        main.showEndScreen(score, previousSettings, true);
    }
}
