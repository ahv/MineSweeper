package asd.fgh.minesweeper.logic.data;

import org.junit.Test;
import static org.junit.Assert.*;


public class BoardTest {
    
    @Test
    public void inBoundsCoordinatesReturnTrue(){
        Board b = new Board(10, 9, 9);
        assertTrue(b.isLegalGrid(0, 0));
        assertTrue(b.isLegalGrid(8, 0));
        assertTrue(b.isLegalGrid(8, 8));
        assertTrue(b.isLegalGrid(0, 8));
        assertTrue(b.isLegalGrid(5, 5));
    }
    
    @Test
    public void outOfBoundsCoordinatesReturnFalse(){
        Board b = new Board(10, 9, 9);
        assertFalse(b.isLegalGrid(-1, 0));
        assertFalse(b.isLegalGrid(9, 0));
        assertFalse(b.isLegalGrid(0, -1));
        assertFalse(b.isLegalGrid(0, 9));
        assertFalse(b.isLegalGrid(666, 666));
    }
    
}
