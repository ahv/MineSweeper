package asd.fgh.minesweeper.ui;

import asd.fgh.minesweeper.logic.Difficulty;
import asd.fgh.minesweeper.logic.GameSettings;


public class Main {
    public static void main(String[] args) throws Exception {
        GameFrame ui = new GameFrame(GameSettings.generatePreset(Difficulty.BEGINNER));
    }
}
