package cpsc2150.extendedTicTacToe.models;
import java.util.*;


/**
 * This class is used to implement IGameBoard
 *
 * @author Sumanth Pandiri
 * @version 1.0
 *
 * @invariant board is a hash map from character to board position, and
 *            it only stores characters specified by the user or ' '
 *            AND MINNUM ≤ numRows ≤ MAXNUMROWS
 *            AND MINNUM ≤ numColumns ≤ MAXNUMCOLUMNS
 *            AND MINNUM ≤ numTokens ≤ MAXNUMTOKENS
 *            AND numTokens ≤ numRows
 *            AND numTokens ≤ numColumns
 *
 * @correspondence NUMROWS = numRows AND
 *                 NUMCOLUMNS = numColumns AND
 *                 NUMTOKENS = numTokens AND
 *                 self = HashMap<Character, List<BoardPosition>>
 */

public class GameBoardMem extends AbsGameBoard implements IGameBoard {
    private Map<Character, List<BoardPosition>> myMap;
    private int numRows;
    private int numColumns;
    private int numTokens;



    /**
     * Parameterized constructor creates an object of the GameBoardMem class and inputs
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
     * @post    board = new HashMap<Character, List<BoardPosition>>()
     *          AND object of GameBoardMem class is created
     *          AND board is empty
     *          AND numRows = r
     *          AND numColumns = c
     *          AND numTokens = t
     *
     */
    public GameBoardMem(int r, int c, int t){
        numRows = r;
        numColumns = c;
        numTokens = t;
        myMap = new HashMap<Character, List<BoardPosition>>();

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
        char player;
        for (Map.Entry<Character, List<BoardPosition>> entry : myMap.entrySet()) {
            char key = entry.getKey();
            List<BoardPosition> value = entry.getValue();
            if (value.contains(pos)) {
                return key;
            }
        }
        return ' ';
    }


    public void placeMarker(BoardPosition marker, char player) {
        if (myMap.containsKey(player)) {
            List<BoardPosition> newList = myMap.get(player);
            newList.add(marker);
            myMap.put(player, newList);
        }
        else {
            List<BoardPosition> newList = new ArrayList<BoardPosition>();
            newList.add(marker);
            myMap.put(player, newList);
        }
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if (myMap.containsKey(player)) {
            if (myMap.get(player).contains(pos)) {
                return true;
            }
        }
        return false;
    }
}
