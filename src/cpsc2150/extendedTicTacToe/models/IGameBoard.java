package cpsc2150.extendedTicTacToe.models;

/**
 *  This is the interface for the game board of the tic-tac-toe game
 *  It provides methods for manipulating the game board and determining the state of the game board
 *
 * @ensures the game board is a 2D grid of size [NUMROWS][NUMCOLUMNS] and is empty when initialized
 *
 * @defines NUMROWS = Z
 *          NUMCOLUMNS = Z
 *          NUMTOKENS = Z
 *          MINNUM = 3
 *          MAXNUMROWS = 100
 *          MAXNUMCOLUMNS = 100
 *          MAXNUMTOKENS = 25
 *
 * @constraints self will have NUMROWS number of rows and NUMCOLUMNS number of columns
 *              AND MINNUM ≤ NUMROWS ≤ MAXNUMROWS
 *              AND MINNUM ≤ NUMCOLUMNS ≤ MAXNUMCOLUMNS
 *              AND MINNUM ≤ NUMTOKENS ≤ MAXNUMTOKENS
 *              AND NUMTOKENS ≤ NUMROWS
 *  *           AND NUMTOKENS ≤ NUMCOLUMNS
 */
public interface IGameBoard {

    /**
     * Outputs the number of rows in the game board
     *
     * @return  returns the number of rows in the game board
     * @post    getNumRows = NUMROWS AND self = #self
     */
    public int getNumRows();

    /**
     * Outputs the number of columns in the game board
     *
     * @return  returns the number of columns in the game board
     * @post    getNumColumns = NUMCOLUMNS AND self = #self
     */
    public int getNumColumns();

    /**
     * Outputs the number of markers needed to win
     *
     * @return  returns the number of tokens needed to win
     * @post    getNumToWin = NUMTOKENS AND self = #self
     */
    public int getNumToWin();

    /**
     * This function returns what is at position pos
     *
     * @param   pos represents the BoardPosition object for the position being checked
     * @return  a character representing what is at the position, could be a character or ''
     *
     * @pre     0 ≤ pos.getRow() < NUMROWS AND 0 ≤ pos.getColumn() < NUMCOLUMNS
     *
     * @post    whatsAtPos = character existing in self at position [marker.getRow()][marker.getColumn()]
     *          self = #self
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * This function places a marker at the position specified
     *
     * @param   marker the board position object representing the position that the marker will be placed
     * @param   player represents which the player's character
     *
     * @pre     0 ≤ marker.getRow() < NUMROWS AND 0 ≤ marker.getColumn() < NUMCOLUMNS AND
     *          whatsAtPos(marker) == ' '
     *
     * @post    self at position [marker.getRow()][marker.getColumn()] = player AND
     *          [rest of self remains constant]
     */
    public void placeMarker(BoardPosition marker, char player);

    /**
     * Function checks to see if the space is available to place a marker
     *
     * @param   pos the board position object that represents the position being checked
     * @return  True if the position is available and false if not available
     *
     * @pre     position is a space in the board
     *
     * @post    (checkSpace = True iff whatsAtPos(pos) = '' AND
     *          0 ≤ pos.getRow() < getNumRows() AND
     *          0 ≤ pos.getColumn < getNumColumns()) AND
     *          (checkSpace = False iff pos.getRow() ≥ getNumRows() OR pos.getRow() < 0 OR
     *          pos.getColumn() ≥ getNumColumns() OR pos.getColumn() < 0 OR
     *          whatsAtPos(pos) ≠ '') AND
     *          self = #self
     */
    default boolean checkSpace(BoardPosition pos) {
        if ((0 <= pos.getColumn()) && (pos.getColumn() < getNumColumns()) &&
                (0 <= pos.getRow()) && (pos.getRow() < getNumRows()) && whatsAtPos(pos) == ' ') {
            return true;
        }
        return false;
    }

    /**
     * This function checks if a player is at a specific position on the board
     *
     * @param   pos represents the BoardPosition object for the position being checked
     * @param   player represents the player that the function is checking for
     * @return  True if the player exists at this position and false if the player doesn't exist at the position
     *
     * @pre     0 ≤ lastPos.getRow() < getNumRows() AND 0 ≤ lastPos.getColumn < getNumColumns
     *
     * @post    (isPlayerAtPos = True iff whatsAtPos(pos) = player) AND
     *          (isPlayerAtPos = False iff whatsAtPos(pos) ≠ player) AND
     *          self = #self
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player) {
        char x = whatsAtPos(pos);
        if (x == player) {
            return true;
        }
        return false;
    }

    /**
     * This function checks to see if there is a winner after the last move
     *
     * @param   lastPos represents the BoardPosition object for the position of the last move on the board
     * @return  returns true if there is a winner and false if there is not a winner
     *
     * @pre     0 ≤ lastPos.getRow() < getNumRows() AND 0 ≤ lastPos.getColumn < getNumColumns() AND
     *          [lastPos was the last marker placed]
     *
     * @post    (checkForWinner = True iff checkHorizontalWin = True OR
     *          checkVerticalWin = True OR
     *          checkDiagonalWin = True) AND
     *          (checkForWinner = False iff checkHorizontalWin = False AND
     *          checkVerticalWin = False AND
     *          checkDiagonalWin = False) AND
     *          self = #self
     */
    default boolean checkForWinner(BoardPosition lastPos) {
        char player = whatsAtPos(lastPos);
        if (checkDiagonalWin(lastPos, player) || checkHorizontalWin(lastPos, player) ||
                checkVerticalWin(lastPos, player)) {
            return true;
        }
        return false;
    }

    /**
     * This function checks for a horizontal win
     *
     * @param   lastPos represents the BoardPosition object for the position of the last move on the board
     * @param   player  represents the player that placed the last marker
     * @return  True if there is a horizontal NUMTOKENS number of tokens in a row and False if there isn't
     *
     * @pre     0 ≤ lastPos.getRow() < getNumRows() AND 0 ≤ lastPos.getColumn < getNumColumns() AND
     *          lastPos was the last marker placed
     *
     * @post    (checkHoriontalWin = True if the row lastPos.getRow() has getNumToWin()-1 number
     *          of consecutive markers in #self of type player) AND
     *          (checkHoriontalWin = False if the row lastPos.getRow() doesn't
     *          have getNumToWin()-1 number of consecutive markers in #self of type player)
     *          self = #self
     */
    default boolean checkHorizontalWin(BoardPosition lastPos, char player) {
        int c = lastPos.getColumn();
        int r = lastPos.getRow();
        int maxColumn = getNumColumns() - 1;
        int total = 1;
        Boolean checkPlace = true;

        //goes through the left side of row checking for a win
        while (checkPlace && c > 0) {
            c--;
            BoardPosition check = new BoardPosition(r, c);
            if (isPlayerAtPos(check, player)) {
                total++;
            }
            else {
                checkPlace = false;
            }
        }

        //goes through the right side of row checking for a win
        checkPlace = true;
        c = lastPos.getColumn();
        while (checkPlace && c < maxColumn) {
            c++;
            BoardPosition check = new BoardPosition(r, c);
            if (isPlayerAtPos(check, player)) {
                total++;
            }
            else {
                checkPlace = false;
            }
        }

        if (total >= getNumToWin()) {
            return true;
        }
        return false;
    }

    /**
     * This function checks for a vertical win
     *
     * @param   lastPos represents the BoardPosition object for the postion of the last move on the board
     * @param   player  represents the player that placed the last marker
     * @return  True if there is a vertical NUMTOKENS number of tokens in a row and False if there isn't
     *
     * @pre     0 ≤ lastPos.getRow() < getNumRows() AND 0 ≤ lastPos.getColumn < getNumColumns() AND
     *          lastPos was the last marker placed
     *
     * @post    (checkVerticalWin = True if the column lastPos.getColumn() has getNumToWin()-1 consecutive markers
     *          in #self of type player) AND
     *          (checkVerticalWin = False if the column lastPos.getColumn() doesn't
     *          have getNumToWin()-1 number of consecutive markers in #self of type player) AND
     *          self = #self
     */
    default boolean checkVerticalWin(BoardPosition lastPos, char player) {
        int c = lastPos.getColumn();
        int r = lastPos.getRow();
        int maxRow = getNumRows() -1;
        int total = 1;
        Boolean checkPlace = true;

        //sifts through the top of column to check for a win
        while (checkPlace && r > 0) {
            r--;
            BoardPosition check = new BoardPosition(r, c);
            if (isPlayerAtPos(check, player)) {
                total++;
            }
            else {
                checkPlace = false;
            }
        }

        //sifts through the bottom of column to check for a win
        checkPlace = true;
        r = lastPos.getRow();
        while (checkPlace && r < maxRow) {
            r++;
            BoardPosition check = new BoardPosition(r, c);
            if (isPlayerAtPos(check, player)) {
                total++;
            }
            else {
                checkPlace = false;
            }
        }

        if (total >= getNumToWin()) {
            return true;
        }
        return false;
    }

    /**
     * This function checks for a diagonal win
     *
     * @param   lastPos represents the BoardPosition object for the postion of the last move on the board
     * @param   player  represents the player that placed the last marker
     * @return  True if there is a diagonal NUMTOKENS number of tokens in a row and False if there isn't
     *
     * @pre     0 ≤ lastPos.getRow() < getNumRows() AND 0 ≤ lastPos.getColumn < getNumColumns AND
     *          lastPos was the last marker placed
     *
     * @post    (checkDiagonalWin = True if the diagonals of lastPos have getNumToWin()-1 number
     *          of consecutive markers in #self of type player) AND
     *          (checkDiagonalWin = False if the diagonals for lastPos doesn't have getNumToWin()-1 number of
     *          consecutive markers in #self of type player) AND
     *          self = #self
     */
    default boolean checkDiagonalWin(BoardPosition lastPos, char player) {
        int c = lastPos.getColumn();
        int r = lastPos.getRow();
        int total = 0;
        int rCheck = r;
        int cCheck = c;
        int win = getNumToWin();
        int minPos = 0;
        int maxColumn = getNumColumns() - 1;
        int maxRow = getNumRows() - 1;
        Boolean checkSpot = true;


        //checks top left diagonal
        while (checkSpot && (minPos <= rCheck) && (rCheck <= maxRow) && (minPos <= cCheck) && (cCheck <= maxColumn)) {

            BoardPosition checkP = new BoardPosition(rCheck, cCheck);
            if (isPlayerAtPos(checkP, player)) {
                total++;
            }
            else {
                checkSpot = false;
            }
            rCheck--;
            cCheck--;
        }

        total--;
        checkSpot = true;
        rCheck = r;
        cCheck = c;
        //checks bottom right diagonal
        while (checkSpot && (minPos <= rCheck) && (rCheck <= maxRow) && (minPos <= cCheck) && (cCheck <= maxColumn)) {
            BoardPosition checkP = new BoardPosition(rCheck, cCheck);
            if (isPlayerAtPos(checkP, player)) {
                total++;
            }
            else {
                checkSpot = false;
            }
            rCheck++;
            cCheck++;
        }

        if (total >= win) {
            return true;
        }
        total = 0;

        checkSpot = true;
        rCheck = r;
        cCheck = c;
        //checks top right diagonal
        while (checkSpot && (minPos <= rCheck) && (rCheck <= maxRow) && (minPos <= cCheck) && (cCheck <= maxColumn)) {
            BoardPosition checkP = new BoardPosition(rCheck, cCheck);
            if (isPlayerAtPos(checkP, player)) {
                total++;
            }
            else {
                checkSpot = false;
            }
            rCheck--;
            cCheck++;
        }

        total--;
        checkSpot = true;
        rCheck = r;
        cCheck = c;
        //checks bottom left diagonal
        while (checkSpot && (minPos <= rCheck) && (rCheck <= maxRow) && (minPos <= cCheck) && (cCheck <= maxColumn)) {
            BoardPosition checkP = new BoardPosition(rCheck, cCheck);
            if (isPlayerAtPos(checkP, player)) {
                total++;
            }
            else {
                checkSpot = false;
            }
            rCheck++;
            cCheck--;
        }

        if (total >= win) {
            return true;
        }
        return false;
    }

    /**
     * This function checks for a draw
     *
     * @return  True if the game is tied and False if the game is not tied
     *
     * @pre     The game hasn't ended yet
     *
     * @post    (checkForDraw = True iff checkSpace() = False for each position in #self) AND
     *          (checkForDraw = False iff checkSpace() = True for any position in #self) AND
     *          self = #self
     */
    default boolean checkForDraw() {
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                BoardPosition check = new BoardPosition(i, j);
                if (checkSpace(check)) {
                    return false;
                }
            }
        }
        return true;
    }
}