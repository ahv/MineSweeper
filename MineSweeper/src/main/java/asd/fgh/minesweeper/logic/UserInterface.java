package asd.fgh.minesweeper.logic;

import asd.fgh.minesweeper.logic.data.Grid;
import asd.fgh.minesweeper.persistence.Score;
import java.util.ArrayList;


public interface UserInterface {
    void updateElapsedTime(int elapsedTime);
    void updateGridView(ArrayList<Grid> changedGrids);
    String enterNameForHighScore();
    void handleEndedGame(Score score);
}
