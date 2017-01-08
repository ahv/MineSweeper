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
    
    @Test
    public void elapsedTimeIncreasesAfterStartAndWait() throws InterruptedException{
        StopWatch sw = new StopWatch();
        sw.start();
        Thread.sleep(2000);
        assertTrue(sw.getElapsedTime() >= 1);
    }
}
