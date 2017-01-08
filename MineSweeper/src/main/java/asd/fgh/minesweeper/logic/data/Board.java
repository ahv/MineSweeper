package asd.fgh.minesweeper.logic.data;

import java.util.ArrayList;

/**
 * Holds a reference to each Grid on the board and has methods to manipulate and
 * inspect them. This class is the only public class in
 * asd.fgh.minesweeper.logic.data and is used internally in an instance of a
 * Game. This class is aspiring to be the data, while the Game class handles the
 * logic (although currently the dichotomy is pretty hazy).
 *
 * To separate Board creation logic and the generated data itself this class
 * uses a MineMap instance internally during its construction to abstract away
 * the creation logic.
 *
 * @author ahv
 */
public class Board {

    private final Grid[][] grid;
    private final int width;
    private final int height;
    private final int mines;

    /**
     * Creates a Board. The passed values aren't validated at this stage, but
     * should be coming from a GameSettings object.
     *
     * @param mines Amount of mines to randomize on the board.
     * @param width Width of the board.
     * @param height Height of the board.
     *
     * @see asd.fgh.minesweeper.logic.GameSettings
     */
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Check if given coordinates are within the boundaries of the Board.
     *
     * @param x X-coordinate.
     * @param y Y-coordinate.
     * @return True if given coordinates are within the boundaries.
     */
    public boolean coordinatesAreInBoundary(int x, int y) {
        return (x >= 0 && x < width && y >= 0 && y < height);
    }

    // All non-mined grids are revealed -- the win condition.
    /**
     * Checks if every non-mined grid has been revealed -- which happens to be
     * the win condition of the game.
     *
     * @return True if essentially win condition has been met.
     */
    public boolean isCompletelyExplored() {
        int revealed = 0;
        for (int i = 0; i < width * height; i++) {
            int x = i % width;
            int y = i / width;

            if (grid[x][y].isRevealed()) {
                revealed++;
            }
        }
        System.out.println("State: " + (revealed + mines) + " of " + width * height);
        return revealed + mines == width * height;
    }

    /**
     * Opens a grid at coordinates. If the opened grid touches no mines, will
     * also recursively open adjacent grids until the edges of the unmined
     * "island" have been found.
     *
     * @param x X-coordinate.
     * @param y Y-coordinate.
     * @return Returns true if mine was hit.
     */
    public boolean openGridAt(int x, int y) {
        if (!coordinatesAreInBoundary(x, y)) {
            return false;
        }
        // Skip recursion when hitting a mine or grid is touching a mine.
        if (grid[x][y].isMined()) {
            grid[x][y].reveal();
            return true;

        } else if (grid[x][y].touchedMines() != 0) {
            grid[x][y].reveal();
            return false;
        }
        // Recursive opening
        ArrayList<Grid> openable = new ArrayList<>();
        openable.add(grid[x][y]);
        populateOpenableRecursively(x, y, openable);
        for (Grid g : openable) {
            g.reveal();
        }
        return false;
    }

    // TODO: What these methods in this class provide could actually be better implemented in Grid class.
    // -- especially if a grid knows about its neighbours!
    // This helper method is only used above, it populates an array of grids to open.
    private ArrayList<Grid> populateOpenableRecursively(int x, int y, ArrayList<Grid> added) {
        for (Grid g : neighbourGrids(x, y)) {
            if (g == null || added.contains(g) || g.isRevealed() || g.isFlagged()) {
                continue;
            }
            added.add(g);
            if (g.touchedMines() == 0) {
                populateOpenableRecursively(g.getX(), g.getY(), added);
            }
        }
        return added;
    }

    // TODO: Drops nulls in the array.
    private Grid[] neighbourGrids(int x, int y) {
        //if (!coordinatesAreInBoundary(x, y)) return empty;
        Grid[] neighbours = new Grid[]{gridAt(x - 1, y - 1), gridAt(x, y - 1), gridAt(x + 1, y - 1), gridAt(x - 1, y),
            gridAt(x + 1, y), gridAt(x - 1, y + 1), gridAt(x, y + 1), gridAt(x + 1, y + 1)};
        return neighbours;
    }

    /**
     * Opens all of the adjacent, unflagged grids from the coordinates position.
     *
     * @param x X-coordinate.
     * @param y Y-coordinate.
     * @return True if a mine was hit.
     */
    public boolean openAdjacentsAt(int x, int y) {
        boolean hitMine = false;
        if (!coordinatesAreInBoundary(x, y)) {
            return false;
        }
        for (Grid g : neighbourGrids(x, y)) {
            if (g != null && !g.isFlagged()) {
                if (openGridAt(g.getX(), g.getY())) {
                    hitMine = true;
                }
            }
        }
        return hitMine;
    }

    private Grid gridAt(int x, int y) {
        if (coordinatesAreInBoundary(x, y)) {
            return grid[x][y];
        }
        return null;
    }

    // TODO: These methods that just pass through seem wrong, maybe they just smell funny?
    public void flipFlag(int x, int y) {
        if (coordinatesAreInBoundary(x, y)) {
            grid[x][y].flipFlag();
        }
    }

    public boolean isMined(int x, int y) {
        if (coordinatesAreInBoundary(x, y)) {
            return grid[x][y].isMined();
        }
        return false;
    }

    public boolean isRevealed(int x, int y) {
        if (coordinatesAreInBoundary(x, y)) {
            return grid[x][y].isRevealed();
        }
        return false;
    }

    public boolean isFlagged(int x, int y) {
        if (coordinatesAreInBoundary(x, y)) {
            return grid[x][y].isFlagged();
        }
        return false;
    }

    public int touchedMinesAt(int x, int y) {
        if (coordinatesAreInBoundary(x, y)) {
            return grid[x][y].touchedMines();
        }
        return 0;
    }

    public int touchedFlagsAt(int x, int y) {
        int flags = 0;
        if (coordinatesAreInBoundary(x, y)) {
            for (Grid g : neighbourGrids(x, y)) {
                if (g != null && g.isFlagged()) {
                    flags++;
                }
            }
        }
        return flags;
    }
}
