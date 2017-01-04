package asd.fgh.minesweeper.logic;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class GameSettingsTest {
    // TODO: Test assumes that presets won't change! Refactor presets into constants and use those instead?
    @Test
    public void beginnerSettingsOk() throws Exception{
        GameSettings s = GameSettings.generatePreset(Difficulty.BEGINNER);
        assertEquals(9, s.getWidth());
        assertEquals(9, s.getHeight());
        assertEquals(Difficulty.BEGINNER, s.getDifficulty());
        assertEquals(10, s.getMines());
    }
}
