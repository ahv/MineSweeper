package asd.fgh.minesweeper.logic;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void canCreateAllPresets() throws Exception {
        Game g = new Game(Difficulty.BEGINNER);
        assertNotNull(g);
        g = new Game(Difficulty.INTERMEDIATE);
        assertNotNull(g);
        g = new Game(Difficulty.ADVANCED);
        assertNotNull(g);
    }
    
    // TODO: Assumes that clamp range doesn't change..
    @Test
    public void canCreateCustomGame(){
        Game g = new Game(16, 4, 4);
        GameSettings s = g.getSettings();
        assertEquals(4, s.getWidth());
        assertEquals(4, s.getHeight());
        assertEquals(16, s.getMines());
    }
    
    @Test
    public void gameEndsWhenMineHit(){
        Game g = new Game(16, 4, 4);
        assertEquals(false, g.hasGameEnded());
        g.openGridAt(0, 0);
        assertEquals(true, g.hasGameEnded());
    }
    
    @Test
    public void cantOpenFlagged(){
        Game g = new Game(16, 4, 4);
        g.flagGridAt(0, 0);
        g.openGridAt(0, 0);
        assertEquals(false, g.hasGameEnded());
    }
}
