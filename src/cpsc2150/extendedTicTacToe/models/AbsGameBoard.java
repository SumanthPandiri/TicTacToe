package cpsc2150.extendedTicTacToe.models;

/**
 * An abstract class that contains an overridden
 * implementation of {@code toString()}.
 *
 * @invariant board remains the same
 *
 */
abstract public class AbsGameBoard implements IGameBoard{
    /**
     * This method overrides the default implementation of {@code toString} to provide
     * a string representation of the object. The function returns a string of the gameBoard
     *
     * @return String representation of the game board in the format of a board
     *
     * @pre NONE
     * @post toString =     '   0| 1| 2| 3| 4| 5| 6| 7| 8| 9|10|11|12|13|14|
     *                       0|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
     *                       1|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
     *                       2|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
     *                       3|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
     *                       4|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
     *                       5|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
     *                       6|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
     *                       7|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
     *                       8|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
     *                       9|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
     *                      10|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
     *                      11|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |'
     *                      with blank spaces replaced by characters as per the board state
     *                      AND board = #board
     *
     */
    @Override
    public String toString() {
        Integer maxRow = getNumRows();
        Integer maxColumn = getNumColumns();
        String str = "   ";
        for (Integer num = 0; num < maxColumn; num++) {
            if (num < 10) {
                str += ' ' + num.toString() + '|';
            }
            else {
                str += num.toString() + '|';
            }
        }
        str += '\n';

        for (int row = 0; row < maxRow; row++) {
            Integer r = row;
            if (r < 10) {
                str += ' ' + r.toString()  + '|';
            }
            else {
                str += r.toString() + '|';
            }
            for (int column = 0; column < maxColumn; column++) {
                BoardPosition check = new BoardPosition(row, column);
                Character c = whatsAtPos(check);
                str += c.toString() + ' ' + '|';
            }
            str += '\n';
        }

        return str;
    }
}
