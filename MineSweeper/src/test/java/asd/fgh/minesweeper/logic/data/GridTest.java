package asd.fgh.minesweeper.logic.data;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

public class GridTest {
    
    private Grid g;
    
    @Before
    public void setUp(){
        g = new Grid(null, 0, 0, true, 0);
    }
    
    @Test
    public void gridBeginsAsUnrevealed(){
        assertEquals(false, g.isRevealed());
    }
    
    @Test
    public void flipFlagFlipsBetweenFlaggedAndUnrevealedEnum(){
        g.flipFlag();
        assertEquals(true, g.isFlagged());
        g.flipFlag();
        assertEquals(false, g.isFlagged());
    }
}
