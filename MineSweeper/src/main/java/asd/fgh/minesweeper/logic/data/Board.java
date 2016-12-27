package asd.fgh.minesweeper.logic.data;


public class Board {
    private final Grid[][] grid;
    private final int width;
    private final int height;

    public Board(int mines, int width, int height) {
        MineMap m = new MineMap(mines, width, height);
        this.grid = new Grid[width][height];
        this.width = width;
        this.height = height;
        for (int i = 0; i < width*height; i++){
            int x = i%width;
            int y = i/width;
            grid[x][y] = new Grid(m.isMined(x, y), m.minedNeighbours(x, y));
        }
    }
    
    public boolean isLegalGrid(int x, int y){
        return (x >= 0 && x < width-1 && y >= 0 && y < height-1);
    }
}
