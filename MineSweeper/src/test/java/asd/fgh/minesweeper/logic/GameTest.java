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
    
    @Test
    public void canCreateCustomGame(){
        Game g = new Game(20, 20, 20);
        assertNotNull(g);
    }
}
