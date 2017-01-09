package asd.fgh.minesweeper.ui;

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

    public EndFrame(Main main, Score score, boolean won) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setTitle((won ? "WON - ": "LOST - ") + score.getDifficulty() + " - " + score.getTime() + " seconds");
        this.main = main;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new Label("Highscores", Label.CENTER));
        add(new Label("Beginner:"));
        add(new Label("Intermediate:"));
        add(new Label("Advanced:"));
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
                main.startGame(score.getDifficulty());
            }
        });
        buttonPanel.add(startFrameButton);
        buttonPanel.add(newGameButton);
        add(buttonPanel);
        setSize(200, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
