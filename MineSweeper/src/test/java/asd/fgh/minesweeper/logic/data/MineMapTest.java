package asd.fgh.minesweeper.logic.data;

import org.junit.Test;
import static org.junit.Assert.*;

public class MineMapTest {

    @Test
    public void isMinedReturnsTrueOnMinedGrid() {
        MineMap m = new MineMap(1, 1, 1);
        assertTrue(m.isMined(0, 0));
    }

    @Test
    public void correctAmountOfMinesLaid() {
        MineMap m = new MineMap(10, 9, 9);
        int mines = 0;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (m.isMined(x, y)) {
                    mines++;
                }
            }
        }
        assertEquals(10, mines);
    }

    @Test
    public void minedNeighboursWorksInCorner() {
        MineMap m = new MineMap(9, 3, 3);
        assertEquals(3, m.minedNeighbours(0, 0));
    }

    @Test
    public void minedNeighboursWorksInCenter() {
        MineMap m = new MineMap(9, 3, 3);
        assertEquals(8, m.minedNeighbours(1, 1));
    }

}
