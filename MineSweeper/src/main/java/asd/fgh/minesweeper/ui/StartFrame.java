package asd.fgh.minesweeper.ui;

import asd.fgh.minesweeper.logic.Difficulty;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
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
        setTitle("Minesweeper");
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
        
        Panel customGamePanel = new Panel(new GridLayout(3, 2));
        customGamePanel.add(new Label("Width:"));
        TextField width = new TextField("10");
        customGamePanel.add(width);
        customGamePanel.add(new Label("Height:"));
        TextField height = new TextField("10");
        customGamePanel.add(height);
        customGamePanel.add(new Label("Mines:"));
        TextField mines = new TextField("10");
        customGamePanel.add(mines);
        Button custom = new Button("Custom");
        custom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int m = Integer.parseInt(mines.getText());
                int w = Integer.parseInt(width.getText());
                int h = Integer.parseInt(height.getText());
                main.startCustomGame(m,w,h);
            }
        });
        add(customGamePanel);
        add(custom);
        
        setSize(200, 250);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
