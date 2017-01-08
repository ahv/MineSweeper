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
    public void canCreateCustomGame() {
        Game g = new Game(16, 4, 4);
        GameSettings s = g.getSettings();
        assertEquals(4, s.getWidth());
        assertEquals(4, s.getHeight());
        assertEquals(16, s.getMines());
    }

    @Test
    public void canOpenGrid() {
        Game g = new Game(1, 30, 16);
        assertEquals(true, g.openGridAt(0, 0));
    }

    @Test
    public void gameEndsWhenMineHit() {
        Game g = new Game(16, 4, 4);
        assertEquals(false, g.hasEnded());
        g.openGridAt(0, 0);
        assertEquals(true, g.hasEnded());
    }

    @Test
    public void cantOpenFlagged() {
        Game g = new Game(16, 4, 4);
        g.flagGridAt(0, 0);
        g.openGridAt(0, 0);
        assertEquals(false, g.hasEnded());
    }

    @Test
    public void cantOpenAdjacentsOnUnrevealed() {
        Game g = new Game(1, 30, 16);
        g.openAdjacentsAt(0, 0);
    }

    // TODO: Chance to hit a mine...
    @Test
    public void isWonReturnsTrueWhenWon() {
        Game g = new Game(1, 30, 16);
        g.openGridAt(0, 0);
        assertTrue(g.isWon());
    }

    @Test
    public void snapshotComplete() {
        Game g = new Game(5, 9, 9);
        int[][] s = g.getBoardSnapshot();
        for (int x = 0; x < s.length; x++) {
            for (int y = 0; y < s[x].length; y++) {
                assertNotNull(s[x][y]);
            }
        }
    }

    @Test
    public void cantGetFinalScoreWhenGameNotEnded() {
        Game g = new Game(5, 9, 9);
        assertNull(g.getFinalScore());
    }
    
        @Test
    public void canGetFinalScoreWhenGameEnded() {
        Game g = new Game(16, 4, 4);
        g.openGridAt(0, 0);
        assertNotNull(g.getFinalScore());
    }
}
