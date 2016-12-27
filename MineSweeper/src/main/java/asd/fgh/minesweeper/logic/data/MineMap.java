package asd.fgh.minesweeper.logic.data;

import java.util.Random;

// Used internally by Board class when generating a new game.
public class MineMap {

    private final boolean[] mineMap;
    private final int width;

    MineMap(int mines, int width, int height) {
        this.mineMap = new boolean[width * height];
        this.width = width;
        Random r = new Random();
        int roll;
        // TODO: High amount of mines might hitch. When mines > size consider "trues" as empty grids instead?
        while (mines > 0) {
            roll = r.nextInt(width * height);
            if (mineMap[roll]) {
                continue;
            }
            mineMap[roll] = true;
            mines--;
        }
    }
    
    boolean isMined(int x, int y) {
        if (x < 0 || y < 0) return false;
        int loc = y * width + x;
        return (loc >= 0 && loc < mineMap.length && mineMap[loc]);
    }

    int minedNeighbours(int x, int y) {
        int adjs = 0;
        for (int cx = -1; cx < 2; cx++){
            for (int cy = -1; cy < 2; cy++){
                if (isMined(x+cx, y+cy)) adjs++;
            }
        }
        if (isMined(x, y) && adjs > 0) adjs--; // Correct self count.
        return adjs;
    }
}
