package asd.fgh.minesweeper.logic;

/**
 * Time keeping object to score a game.
 *
 * @author ahv
 */
public class StopWatch {

    private long startTime;
    private long stopTime;
    private boolean running;

    /**
     * Start the stopwatch.
     */
    public void start() {
        startTime = System.currentTimeMillis();
        running = true;
    }

    /**
     * Stop the stopwatch.
     */
    public void stop() {
        stopTime = System.currentTimeMillis();
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    /**
     * Gives the elapsed time since first move in seconds.
     *
     * @return Elapsed time from first move, 0 if hasn't been started yet.
     */
    public int getElapsedTime() {
        // long defaults to 0; means that watch hasn't been started
        // unless system millis happen to be 0 when clock gets started, then whoops.
        if (startTime == 0) {
            return 0;
        }
        if (running) {
            return (int) (System.currentTimeMillis() - startTime) / 1000;
        }
        return (int) (stopTime - startTime) / 1000;
    }
}
