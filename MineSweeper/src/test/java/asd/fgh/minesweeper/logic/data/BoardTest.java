package asd.fgh.minesweeper.logic.data;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void inBoundsCoordinatesReturnTrue() {
        Board b = new Board(10, 9, 9);
        assertTrue(b.coordinatesAreInBoundary(0, 0));
        assertTrue(b.coordinatesAreInBoundary(8, 0));
        assertTrue(b.coordinatesAreInBoundary(8, 8));
        assertTrue(b.coordinatesAreInBoundary(0, 8));
        assertTrue(b.coordinatesAreInBoundary(5, 5));
    }

    @Test
    public void outOfBoundsCoordinatesReturnFalse() {
        Board b = new Board(10, 9, 9);
        assertFalse(b.coordinatesAreInBoundary(-1, 0));
        assertFalse(b.coordinatesAreInBoundary(9, 0));
        assertFalse(b.coordinatesAreInBoundary(0, -1));
        assertFalse(b.coordinatesAreInBoundary(0, 9));
        assertFalse(b.coordinatesAreInBoundary(666, 666));
    }
    
    @Test
    public void canCompletelyExplore(){
        Board b = new Board(1, 4, 4);
        assertEquals(false, b.isCompletelyExplored());
        for (int x = 0; x < 4; x++){
            for (int y = 0; y < 4; y++){
                if (!b.isMined(x, y)) b.openGridAt(x, y);
            }
        }
        assertEquals(true, b.isCompletelyExplored());
    }
    
    @Test
    public void canOpenAdjacents(){
        Board b = new Board(1, 20, 20);
        b.openAdjacentsAt(0, 0);
    }

}
