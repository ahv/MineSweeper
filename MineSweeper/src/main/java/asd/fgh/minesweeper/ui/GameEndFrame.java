package asd.fgh.minesweeper.ui;

import asd.fgh.minesweeper.logic.Difficulty;
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

public class GameEndFrame extends Frame {

    private final Main main;

    public GameEndFrame(Main main) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.main = main;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Panel titlePanel = new Panel(new FlowLayout());
        add(titlePanel);
        Label titleText = new Label("Hi");
        titlePanel.add(titleText);
        Button newBeginnerGameButton = new Button("New Beginner Game");
        newBeginnerGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                main.startGame(Difficulty.BEGINNER);

            }
        });
        Button newIntermediateGameButton = new Button("New Intermediate Game");
        newIntermediateGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                main.startGame(Difficulty.INTERMEDIATE);

            }
        });
        Button newAdvancedGameButton = new Button("New Advanced Game");
        newAdvancedGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                main.startGame(Difficulty.ADVANCED);

            }
        });
        titlePanel.add(newBeginnerGameButton);
        titlePanel.add(newIntermediateGameButton);
        titlePanel.add(newAdvancedGameButton);
        setSize(200, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
