package cpsc2150.extendedTicTacToe.models;


/**
 * This class is used to implement IGameBoard
 *
 * @author Sumanth Pandiri
 * @version 1.0
 *
 * @invariant board is 2d array of [numRows][numColumns] and only stores characters specified by the user or ' '
 *            AND MINNUM ≤ numRows ≤ MAXNUMROWS
 *            AND MINNUM ≤ numColumns ≤ MAXNUMCOLUMNS
 *            AND MINNUM ≤ numTokens ≤ MAXNUMTOKENS
 *            AND numTokens ≤ numRows
 *            AND numTokens ≤ numColumns
 *
 * @correspondence NUMROWS = numRows AND
 *                 NUMCOLUMNS = numColumns AND
 *                 NUMTOKENS = numTokens AND
 *                 self = board[numRows][numColumns]
 */

public class GameBoard extends AbsGameBoard implements IGameBoard {
    private char[][] board;
    private int numRows;
    private int numColumns;
    private int numTokens;


    /**
     * Parameterized constructor creates an object of the GameBoard class and inputs
     *
     * @param   r represents the number of rows
     * @param   c represents the number of columns
     * @param   t represents the number of tokens
     *
     * @pre     MINNUM ≤ r ≤ MAXNUMROWS
     *          AND MINNUM ≤ c ≤ MAXNUMCOLUMNS
     *          AND MINNUM ≤ t ≤ MAXNUMTOKENS
     *          AND t ≤ r
     *          AND t ≤ c
     *
     * @post    board = new board[numRows][numColumns]
     *          AND object of GameBoard class is created
     *          AND [each position in board is initialized to '']
     *          AND numRows = r
     *          AND numColumns = c
     *          AND numTokens = t
     *
     */
    public GameBoard(int r, int c, int t){
        numRows = r;
        numColumns = c;
        numTokens = t;
        board = new char[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public int getNumRows() {
        return numRows;
    }


    public int getNumColumns(){
        return numColumns;
    }


    public int getNumToWin() {
        return numTokens;
    }


    public char whatsAtPos(BoardPosition pos) {
        char player = board[pos.getRow()][pos.getColumn()];
        return player;
    }


    public void placeMarker(BoardPosition marker, char player) {
        board[marker.getRow()][marker.getColumn()] = player;
    }
}
