package asd.fgh.minesweeper.logic;

import java.util.Random;

/*
Beginner: 10 mines, 9x9 grid
Intermediate: 40, 16x16
Advanced: 99, 16x30
Allow Custom?
*/

public class Game {
    private final Grid[][] grid;

    // TODO: Large constructor, could be broken into private methods for readability (and testability).
    public Game(int mines, int width, int height) {
        // Validify parameters
        width = clamp(width, 4, 30);
        height = clamp(height, 4, 16); // TODO: Hard coded values here.
        final int size = width*height;
        mines = clamp(mines, 1, size);
        
        // Generate temporary mine map
        boolean[] mineMap = new boolean[size];
        int laidMines = 0;
        Random r = new Random();
        int roll;
         // TODO: High amount of mines might hitch. When mines > size consider "trues" as empty grids instead?
        while (laidMines < mines) {
            roll = r.nextInt(size);
            if (mineMap[roll]) continue;
            mineMap[roll] = true;
            laidMines++;
        }
        
        // Initialize grid
        grid = new Grid[width][height];
        int adjacentMines;
        for (int i = 0; i < size; i++){
            adjacentMines = adjacentMines(i, mineMap, width);
            grid[i%width][i/width] = new Grid(mineMap[i]);
        }
    }
    
    private int clamp(int value, int min, int max){
         return (value > max) ? max : (value < min ? min : value);
    }

    private int adjacentMines(int i, boolean[] m, int w) {
        int a = 0;
        int l = m.length;
        if (i%w>0 && i/w>0 && m[i-w-1]) a++;
        if (i/w>0 && m[i-w]) a++;
        if (i%w<w-1 && i/w>0 && m[i-w]) a++;
        return a;
    }
}