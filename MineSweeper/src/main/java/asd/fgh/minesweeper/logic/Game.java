package asd.fgh.minesweeper.logic;

import asd.fgh.minesweeper.logic.data.Board;

/*
Beginner: 10 mines, 9x9 grid
Intermediate: 40, 16x16
Advanced: 99, 16x30
Allow Custom?
*/

public class Game {
    private boolean alive;
    private final Board board;

    public Game(int mines, int width, int height) {
        // Validify parameters
        width = clamp(width, 4, 30);
        height = clamp(height, 4, 16); // TODO: Hard coded values here.
        mines = clamp(mines, 1, width*height);
        
        this.board = new Board(mines, width, height);
        this.alive = true;
    }
    
    // TODO: Is this the proper place to handle validation?
    private int clamp(int value, int min, int max){
         return (value > max) ? max : (value < min ? min : value);
    }
    
    
}
