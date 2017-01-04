package asd.fgh.minesweeper.logic.data;

// The only public class in asd.fgh.minesweeper.logic.data -- used as an instance in Game class.

import asd.fgh.minesweeper.logic.GameSettings;

public class Board {

    private final Grid[][] grid;
    private final int width;
    private final int height;
    
    public Board (GameSettings settings){
        this(settings.getMines(), settings.getWidth(), settings.getHeight());
    }

    private Board(int mines, int width, int height) {
        MineMap m = new MineMap(mines, width, height);
        this.grid = new Grid[width][height];
        this.width = width;
        this.height = height;
        for (int i = 0; i < width * height; i++) {
            int x = i % width;
            int y = i / width;
            grid[x][y] = new Grid(m.isMined(x, y), m.minedNeighbours(x, y));
        }
    }

    public boolean isLegalGrid(int x, int y) {
        return (x >= 0 && x < width && y >= 0 && y < height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMineAmount() {
        int mines = 0;
        for (int i = 0; i < width * height; i++) {
            int x = i % width;
            int y = i / width;

            if (grid[x][y].isMined()) {
                mines++;
            }
        }
        return mines;
    }
}
