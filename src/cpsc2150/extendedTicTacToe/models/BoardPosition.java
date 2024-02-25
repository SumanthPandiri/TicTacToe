package cpsc2150.extendedTicTacToe.models;

/**
 * This class is used to store the board position for tic-tac-toe board
 *
 * @author Sumanth Pandiri
 * @version 1.0
 *
 * @invariant 0  ≤ row ≤ MAX_SIZE
 *            AND 0  ≤ column ≤ MAX_SIZE
 */

public class BoardPosition {
    private int row;
    private int column;

    /**
     * Constructor creates a board position with the specific row and column taken as input
     *
     * @param r     The row of the position
     * @param c     The column of the position
     *
     * @pre 0  ≤ r ≤ MAX_SIZE
     *      AND 0  ≤ c ≤ MAX_SIZE
     *      AND r is the number of rows desired, c is the number of columns desired
     *
     * @post row = r AND column = c
     */
    public BoardPosition(int r, int c){
        row = r;
        column = c;
    }


    /**
     * Function override for toString method. The function returns a string of the board position
     *
     * @return String representation of the board position
     *
     * @pre NONE
     * @post toString = "[row],[column]"
     *      AND row = #row
     *      AND column = #column
     */
    @Override
    public String toString() {
        String s = row + "," + column;
        return s;
    }

    /**
     * This method returns the row of the board position
     *
     * @return row
     *
     * @pre NONE
     * @post getRow = row
     *      AND row = #row
     *      AND column = #column
     */
    public int getRow(){
        return row;
    }

    /**
     * This method returns the column of the board position
     *
     * @return column
     *
     * @pre NONE
     * @post getColumn = column
     *      AND row = #row
     *      AND column = #column
     */
    public int getColumn(){
        return column;
    }

    /**
     * Function override for equals method. This function compares 2 board positions to see if they are the same
     *
     * @param obj   The object being compared to
     *
     * @return True if the positions are equal and false if they are not
     *
     * @pre NONE
     * @post (equals = True iff (row = obj.getRow() AND column = obj.getColumn()))
     *      AND  (equals = False iff (row != obj.getRow() OR column != obj.getColumn()))
     *      AND row = #row
     *      AND column = #column
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BoardPosition) {
            BoardPosition c = (BoardPosition) obj;
            if (row == c.getRow() && column == c.getColumn()) {
                return true;
            }
        }
        return false;
    }
}
