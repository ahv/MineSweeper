package asd.fgh.minesweeper.logic;

import asd.fgh.minesweeper.logic.data.Grid;
import asd.fgh.minesweeper.persistence.Score;
import java.util.ArrayList;


class MockUi implements UserInterface {

    private ArrayList<Grid> uiReturn;

    MockUi(ArrayList<Grid> uiReturn) {
        this.uiReturn = uiReturn;
        
    }

    @Override
    public void updateElapsedTime(int elapsedTime) {
        
    }

    @Override
    public void updateGridView(ArrayList<Grid> changedGrids) {
        uiReturn = changedGrids;
    }

    @Override
    public void handleEndedGame(Score score, GameSettings settings, boolean wasVictory) {
        
    }
    
}
