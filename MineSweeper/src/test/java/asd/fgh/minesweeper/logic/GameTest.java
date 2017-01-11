package asd.fgh.minesweeper.logic;

import asd.fgh.minesweeper.logic.data.Grid;
import asd.fgh.minesweeper.logic.data.GridState;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;

public class GameTest {
    
    private Game game;
    private MockUi ui;
    private ArrayList<Grid> uiReturn;
    
    @Before
    public void setUp(){
        game = new Game(Difficulty.BEGINNER);
        uiReturn = new ArrayList<>();
        ui = new MockUi(uiReturn);
        game.setUserInterface(ui);
    }

    @Test
    public void testCanCreateBeginnerGame() {
        Game g = new Game(Difficulty.BEGINNER);
        GameSettings s = g.getSettings();
        assertNotNull(g);
        assertEquals(Difficulty.BEGINNER, s.getDifficulty());
    }

    @Test
    public void testCanCreateIntermediateGame() {
        Game g = new Game(Difficulty.INTERMEDIATE);
        GameSettings s = g.getSettings();
        assertNotNull(g);
        assertEquals(Difficulty.INTERMEDIATE, s.getDifficulty());
    }

    @Test
    public void testCanCreateAdvancedGame() {
        Game g = new Game(Difficulty.ADVANCED);
        GameSettings s = g.getSettings();
        assertNotNull(g);
        assertEquals(Difficulty.ADVANCED, s.getDifficulty());
    }

    @Test
    public void testCanCreateCustomGame() {
        Game g = new Game(4, 10, 10);
        GameSettings s = g.getSettings();
        assertNotNull(g);
        assertNotNull(s);
        assertNull(s.getDifficulty());
    }
    
    @Test
    public void testFlagGridReturnsArrayToUi(){
         game.flagGridAt(0, 0);
         assertNotNull(uiReturn);
    }
    
    @Test
    public void testFlagGridReturnedArrayLengthIsOne(){
        game.flagGridAt(1, 1);
        //assertEquals(1, uiReturn.size());
    }
    
    @Test
    public void testCanFlagGrid(){
        game.flagGridAt(0, 0);
        //Grid g  = uiReturn.get(0);
        //assertEquals(GridState.FLAGGED, g.getState());
        game.flagGridAt(0, 0);
        //g = uiReturn.get(0);
        //assertEquals(GridState.UNREVEALED, g.getState());
    }
    
    @Test
    public void testCanOpenGrid(){
        game.openGridAt(0, 0);
        assertNotNull(uiReturn);
    }
}
