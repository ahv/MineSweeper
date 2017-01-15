package asd.fgh.minesweeper.ui;

import asd.fgh.minesweeper.logic.Difficulty;
import static asd.fgh.minesweeper.logic.Difficulty.BEGINNER;
import asd.fgh.minesweeper.logic.GameSettings;
import asd.fgh.minesweeper.persistence.HighScores;
import asd.fgh.minesweeper.persistence.Score;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;

public class EndFrame extends Frame {

    private final Main main;

    public EndFrame(Main main, GameSettings previousSettings, Score score, boolean won) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Difficulty difc = score.getDifficulty();
        setTitle((won ? "WON - " : "LOST - ") + (difc != null ? difc : "CUSTOM") + " - " + score.getTime() + " seconds");
        this.main = main;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new Label("Highscores", Label.CENTER));
        // TODO: Terrible copypaste shit here
        add(new Label("Beginner:"));
        int i = 1;
        for (Score s : HighScores.getScoresFor(Difficulty.BEGINNER)) {
            add(new Label(i + ". " + s.getName() + " -- " + s.getTime() + " seconds"));
            i++;
        }
        add(new Label("Intermediate:"));
        i = 1;
        for (Score s : HighScores.getScoresFor(Difficulty.INTERMEDIATE)) {
            add(new Label(s.getName() + " " + s.getTime() + " seconds"));
            i++;
        }
        add(new Label("Advanced:"));
        i = 1;
        for (Score s : HighScores.getScoresFor(Difficulty.ADVANCED)) {
            add(new Label(s.getName() + " " + s.getTime() + " seconds"));
            i++;
        }
        //TODO: scores

        Panel buttonPanel = new Panel(new FlowLayout());
        Button startFrameButton = new Button("Change difficulty");
        startFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                main.showStartFrame();
            }
        });
        Button newGameButton = new Button("Play again");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                main.startGame(previousSettings);
            }
        });
        buttonPanel.add(startFrameButton);
        buttonPanel.add(newGameButton);
        add(buttonPanel);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

}
