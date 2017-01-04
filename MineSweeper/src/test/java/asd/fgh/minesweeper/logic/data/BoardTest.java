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

}
