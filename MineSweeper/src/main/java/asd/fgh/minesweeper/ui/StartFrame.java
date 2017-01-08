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

public class StartFrame extends Frame {

    public StartFrame(Main main) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setTitle("Mine Sweeper");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Panel titlePanel = new Panel(new FlowLayout());
        add(titlePanel);
        Label titleText = new Label("Select Difficulty:");
        titlePanel.add(titleText);
        Button newBeginnerGameButton = new Button("Beginner");
        newBeginnerGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                main.startGame(Difficulty.BEGINNER);

            }
        });
        Button newIntermediateGameButton = new Button("Intermediate");
        newIntermediateGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                main.startGame(Difficulty.INTERMEDIATE);

            }
        });
        Button newAdvancedGameButton = new Button("Advanced");
        newAdvancedGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                main.startGame(Difficulty.ADVANCED);

            }
        });
        // TODO: Custom settings box
        add(newBeginnerGameButton);
        add(newIntermediateGameButton);
        add(newAdvancedGameButton);
        
        setSize(200, 200);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
