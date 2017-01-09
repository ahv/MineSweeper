package asd.fgh.minesweeper.logic.data;

public enum GridState { // TODO: Iffy
    NONE, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, MINED, FLAGGED, UNREVEALED;
    static GridState nthEnum(int n){ // a little unsafe perhaps.
        return GridState.values()[n];
    }
    public static char stateToSymbol(GridState state){
        switch(state){
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
                return'?';
        }
    }
    
}
