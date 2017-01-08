package asd.fgh.minesweeper.logic;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class StopWatchTest {

    @Test
    public void startStopElapsedGreaterOrEqualsZero() {
        StopWatch sw = new StopWatch();
        sw.start();
        sw.stop();
        assertTrue(sw.getElapsedTime() >= 0);
    }
}
