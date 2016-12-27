package asd.fgh.minesweeper.logic.data;

// The only public class in asd.fgh.minesweeper.logic.data -- used as an instance in Game class.
public class Board {

    private final Grid[][] grid;
    private final int width;
    private final int height;

    public Board(int mines, int width, int height) {
        // Validify parameters
        width = clamp(width, 4, 30);
        height = clamp(height, 4, 16); // TODO: Hard coded values here.
        mines = clamp(mines, 1, width * height);
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

    // TODO: Is this the proper place to handle validation?
    private int clamp(int value, int min, int max) {
        return (value > max) ? max : (value < min ? min : value);
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
