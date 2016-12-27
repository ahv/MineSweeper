package asd.fgh.minesweeper.logic;

import asd.fgh.minesweeper.logic.data.Board;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void beginnerDifficulty() throws Exception {
        Game g = Game.generatePresetGame(Difficulty.BEGINNER);
        Board b = g.getBoard();
        assertEquals(10, b.getMineAmount());
        assertEquals(9, b.getWidth());
        assertEquals(9, b.getHeight());
    }

    @Test
    public void intermediateDifficulty() throws Exception {
        Game g = Game.generatePresetGame(Difficulty.INTERMEDIATE);
        Board b = g.getBoard();
        assertEquals(40, b.getMineAmount());
        assertEquals(16, b.getWidth());
        assertEquals(16, b.getHeight());
    }

    @Test
    public void advancedDifficulty() throws Exception {
        Game g = Game.generatePresetGame(Difficulty.ADVANCED);
        Board b = g.getBoard();
        assertEquals(99, b.getMineAmount());
        assertEquals(30, b.getWidth());
        assertEquals(16, b.getHeight());
    }

    @Test
    public void customDifficulty() {
        Game g = new Game(24, 20, 10);
        Board b = g.getBoard();
        assertEquals(24, b.getMineAmount());
        assertEquals(20, b.getWidth());
        assertEquals(10, b.getHeight());
    }

}
