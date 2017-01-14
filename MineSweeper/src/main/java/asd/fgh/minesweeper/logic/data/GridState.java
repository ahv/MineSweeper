package asd.fgh.minesweeper.logic.data;

import java.awt.Color;

/**
 * Represents a "visual" state of a grid.
 *
 * @author ahv
 */
public enum GridState { // TODO: Iffy
    NONE, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, MINED, FLAGGED, UNREVEALED;

    static GridState nthEnum(int n) { // a little unsafe perhaps.
        return GridState.values()[n];
    }

    /**
     * Turns a GridState to a char, convenience for user interfaces.
     *
     * @param state The state to get a symbol for.
     * @return An unique char matching each state.
     */
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

    static final Color blue = new Color(0, 0, 220);
    static final Color darkblue = new Color(0, 0, 120);
    static final Color green = new Color(0, 120, 0);
    static final Color red = new Color(220, 0, 0);
    static final Color darkred = new Color(120, 0, 0);
    static final Color teal = new Color(0, 220, 220);
    static final Color purple = new Color(120, 0, 120);

    /**
     * Convenience for user interfaces, matches states with colors.
     *
     * @param state The state to get a color for.
     * @return A color matching a state.
     */
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
