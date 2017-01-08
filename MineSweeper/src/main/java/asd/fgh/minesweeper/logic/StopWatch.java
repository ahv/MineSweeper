package asd.fgh.minesweeper.logic;

/**
 * StopWatch to score a Game
 *
 * @author ahv
 */
public class StopWatch {

    private long startTime;
    private long stopTime;
    private boolean running;

    public StopWatch() {
    }

    void start() {
        startTime = System.currentTimeMillis();
        running = true;
    }

    void stop() {
        stopTime = System.currentTimeMillis();
        running = false;
    }

    public int getElapsedTime() {
         // long defaults to 0; means that watch hasn't been started
         // unless system millis happen to be 0 when clock gets started, then whoops.
        if (startTime == 0) return 0;
        if (running) {
            return (int) (System.currentTimeMillis() - startTime) / 1000;
        }
        return (int) (stopTime - startTime) / 1000;
    }
}
