package asd.fgh.minesweeper.logic.data;

import java.util.ArrayList;

/**
 * Holds a reference to each Grid on the board and has methods to manipulate and
 * inspect them. This class is the only public class in
 * asd.fgh.minesweeper.logic.data and is used internally in an instance of a
 * Game. The board state manipulating methods return array lists that hold
 * references to each changed Grid so their visual representation can be changed
 * in an user interface.
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
            grid[x][y] = new Grid(this, x, y, m.isMined(x, y), m.minedNeighbours(x, y));
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    boolean isInBoardBoundary(int x, int y) {
        return (x >= 0 && x < width && y >= 0 && y < height);
    }

    /**
     * Checks if every non-mined grid has been revealed -- which happens to be
     * the win condition of the game.
     *
     * @return True if essentially win condition has been met.
     */
    public boolean isCompletelyExplored() { // TODO: Simplify (track values internally?)
        int revealed = 0;
        for (int i = 0; i < width * height; i++) {
            int x = i % width;
            int y = i / width;

            if (grid[x][y].isRevealed()) {
                revealed++;
            }
        }
        return revealed + mines == width * height;
    }

    /**
     * Opens a grid at coordinates. If the opened grid touches no mines, Grid
     * class also recursively opens adjacent grids until the edges of the
     * unmined "island" have been found.
     *
     * @param x X-coordinate.
     * @param y Y-coordinate.
     * @return Returns a list of references to each changed grid.
     */
    public ArrayList<Grid> openGridAt(int x, int y) {
        ArrayList<Grid> touched = new ArrayList<>();
        if (!isInBoardBoundary(x, y)) {
            return touched;
        }
        Grid g = gridAt(x, y);
        g.open(touched);
        return touched;
    }

    /**
     * Flips a flag in grid.
     *
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return Reference to affected grid.
     */
    public Grid flipFlag(int x, int y) {
        if (isInBoardBoundary(x, y)) {
            grid[x][y].flipFlag();
        }
        return grid[x][y];
    }

    /**
     * If flagged neighbours matches mined neighbours amount, opens all adjacent
     * unflagged grids from the coordinates position.
     *
     * @param x X-coordinate.
     * @param y Y-coordinate.
     * @return List of all changed grids.
     */
    public ArrayList<Grid> openAdjacentsAt(int x, int y) {
        ArrayList<Grid> touched = new ArrayList<>();
        if (!isInBoardBoundary(x, y)) {
            return touched;
        }
        if (gridAt(x, y).flaggedNeighboursMatchMinedNeighbours()) {
            for (Grid g : neighbourGrids(x, y)) {
                g.open(touched);
            }
        }
        return touched;
    }

    // TODO: A little dirty looking.
    ArrayList<Grid> neighbourGrids(int x, int y) {
        ArrayList<Grid> neighbours = new ArrayList<>();
        if (!isInBoardBoundary(x, y)) {
            return neighbours;
        }
        Grid[] ns = new Grid[]{gridAt(x - 1, y - 1), gridAt(x, y - 1), gridAt(x + 1, y - 1), gridAt(x - 1, y),
            gridAt(x + 1, y), gridAt(x - 1, y + 1), gridAt(x, y + 1), gridAt(x + 1, y + 1)};
        for (Grid g : ns) {
            if (g != null) {
                neighbours.add(g);
            }
        }
        return neighbours;
    }

    private Grid gridAt(int x, int y) {
        if (isInBoardBoundary(x, y)) {
            return grid[x][y];
        }
        return null;
    }
}
