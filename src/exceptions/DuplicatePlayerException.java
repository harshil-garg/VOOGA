package exceptions;

/**
 * @author Filip Mazurek
 */
public class DuplicatePlayerException extends Exception implements Alert {
    private int myRow;
    private int myColumn;


    public DuplicatePlayerException(int row, int col) {
        super();
        myRow = row;
        myColumn = col;
    }

    @Override
    public String getMessage () {
        return String.format(DUPLICATE_PLAYER, Integer.toString(myRow), Integer.toString(myColumn));
    }
}
