package asd.fgh.minesweeper.logic.data;

import java.awt.Color;

public enum GridState { // TODO: Iffy
    NONE, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, MINED, FLAGGED, UNREVEALED;

    static GridState nthEnum(int n) { // a little unsafe perhaps.
        return GridState.values()[n];
    }

    public static char stateToSymbol(GridState state) {
        switch (state) {
            case NONE:
            case UNREVEALED:
                return ' ';
            case ONE:
                return '1';
            case TWO:
                return '2';
            case THREE:
                return '3';
            case FOUR:
                return '4';
            case FIVE:
                return '5';
            case SIX:
                return '6';
            case SEVEN:
                return '7';
            case EIGHT:
                return '8';
            case FLAGGED:
                return '!';
            case MINED:
                return 'X';
            default:
                return '?';
        }
    }

    static Color blue = new Color(0, 0, 220);
    static Color darkblue = new Color(0, 0, 120);
    static Color green = new Color(0, 120, 0);
    static Color red = new Color(220, 0, 0);
    static Color darkred = new Color(120, 0, 0);
    static Color teal = new Color(0, 220, 220);
    static Color purple = new Color(120, 0, 120);

    public static Color stateToColor(GridState state) {
        switch (state) {
            case ONE:
                return blue;
            case TWO:
                return green;
            case THREE:
                return red;
            case FOUR:
                return darkblue;
            case FIVE:
                return darkred;
            case SIX:
                return teal;
            case SEVEN:
                return purple;
            case NONE:
            case UNREVEALED:
            case EIGHT:
            case FLAGGED:
            case MINED:
            default:
                return Color.black;
        }
    }

}
