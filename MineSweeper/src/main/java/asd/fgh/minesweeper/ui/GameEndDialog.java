package asd.fgh.minesweeper.ui;

import asd.fgh.minesweeper.logic.Difficulty;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;

public class GameEndDialog extends Frame {

    private final Main main;

    public GameEndDialog(Main main) {
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
        titlePanel.add(newBeginnerGameButton);
        setSize(200, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
  
}
