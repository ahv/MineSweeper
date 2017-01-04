package asd.fgh.minesweeper.logic.data;

import java.util.Random;

/**
 * Used internally by Board class when generating a new board.
 * Abstracts away creation logic and allows Board to be a simpler data class.
 * 
 * @author ahv
 */
public class MineMap {

    private final boolean[][] mineMap;
    private final int width;
    private final int height;

    MineMap(int mines, int width, int height) {
        // TODO: Failsafe, but is handling validation again -- which should've happened in GameSettings already.
        mines = mines <= width*height ? mines : width*height;
        this.mineMap = new boolean[width][height];
        this.width = width;
        this.height = height;
        Random r = new Random();
        int roll, x, y;
        // TODO: High amount of mines might hitch. When mines > size consider "trues" as empty grids instead?
        while (mines > 0) {
            roll = r.nextInt(width * height);
            y = roll / width;
            x = roll % width;
            if (mineMap[x][y]) {
                continue;
            }
            mineMap[x][y] = true;
            mines--;
        }
    }

    boolean isMined(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return false;
        }
        return mineMap[x][y];
    }

    int minedNeighbours(int x, int y) {
        int adjs = 0;
        for (int cx = -1; cx < 2; cx++) {
            for (int cy = -1; cy < 2; cy++) {
                if (isMined(x + cx, y + cy)) {
                    adjs++;
                }
            }
        }
        if (isMined(x, y) && adjs > 0) {
            adjs--; // Correct self count.
        }
        return adjs;
    }
}
