package asd.fgh.minesweeper;

import asd.fgh.minesweeper.logic.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(10, 9, 9);
        System.out.println(game);
    }
}
