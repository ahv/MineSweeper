package asd.fgh.minesweeper.logic.data;

// The only public class in asd.fgh.minesweeper.logic.data -- used as an instance in Game class.
// Game class has the actual game logic, this class aspires to be data only.
// Handles the recursive opening of grids though.

import java.util.ArrayList;

public class Board {

    private final Grid[][] grid;
    private final int width;
    private final int height;
    private final int mines;
    
    public Board(int mines, int width, int height) {
        MineMap m = new MineMap(mines, width, height);
        this.grid = new Grid[width][height];
        this.mines = mines;
        this.width = width;
        this.height = height;
        for (int i = 0; i < width * height; i++) {
            int x = i % width;
            int y = i / width;
            grid[x][y] = new Grid(x, y, m.isMined(x, y), m.minedNeighbours(x, y));
        }
    }

    public boolean coordinatesAreInBoundary(int x, int y) {
        return (x >= 0 && x < width && y >= 0 && y < height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    // All non-mined grids are revealed -- the win condition.
    public boolean isCompletelyExplored() {
        int revealed = 0;
        for (int i = 0; i < width * height; i++) {
            int x = i % width;
            int y = i / width;

            if (grid[x][y].isRevealed()) {
                revealed++;
            }
        }
        return revealed+mines == width*height;
    }

    public void openGridAt(int x, int y) {
        if (!coordinatesAreInBoundary(x, y)) return;
        if (grid[x][y].isMined() || grid[x][y].touchedMines() != 0) {
            grid[x][y].reveal();
            return; // Skip recursion when hitting a mine or grid is touching a mine.
        }
        
        // Recursive opening
        ArrayList<Grid> openable = new ArrayList<>();
        openable.add(grid[x][y]);
        populateOpenableRecursively(x, y, openable);
        for (Grid g : openable){
            g.reveal();
        }
    }
    
    // This helper method is only used above.
    private ArrayList<Grid> populateOpenableRecursively(int x, int y, ArrayList<Grid> added) {
        Grid[] neighbours = new Grid[]{ gridAt(x-1, y-1), gridAt(x, y-1), gridAt(x+1, y-1), gridAt(x-1, y), 
            gridAt(x+1, y), gridAt(x-1, y+1), gridAt(x, y+1), gridAt(x+1, y+1)};
        for (Grid g : neighbours){
            if (g == null || added.contains(g) || g.isRevealed() || g.isFlagged()) continue;
            added.add(g);
            if (g.touchedMines() == 0) populateOpenableRecursively(g.getX(), g.getY(), added);
        }
        return added;
    }

    private Grid gridAt(int x, int y) {
        if (coordinatesAreInBoundary(x, y)) return grid[x][y];
        return null;
    }

    // TODO: These methods that just pass through seem wrong, maybe they just smell funny.
    public void flipFlag(int x, int y) {
        if (coordinatesAreInBoundary(x, y)) grid[x][y].flipFlag();
    }

    public boolean isMined(int x, int y) {
        if (coordinatesAreInBoundary(x, y)) return grid[x][y].isMined();
        return false;
    }
    
    public boolean isRevealed(int x, int y) {
        if (coordinatesAreInBoundary(x, y)) return grid[x][y].isRevealed();
        return false;
    }
    
    public boolean isFlagged(int x, int y) {
        if (coordinatesAreInBoundary(x, y)) return grid[x][y].isFlagged();
        return false;
    }

    public int touchedMines(int x, int y) {
        if (coordinatesAreInBoundary(x, y)) return grid[x][y].touchedMines();
        return 0;
    }
}
