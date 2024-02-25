package cpsc2150.extendedTicTacToe.controllers;

import cpsc2150.extendedTicTacToe.models.*;
import cpsc2150.extendedTicTacToe.views.*;

/**
 * <p>
 * The {@link TicTacToeController} class will handle communication between our {@link TicTacToeView}
 * and our model ({@link IGameBoard} and {@link BoardPosition})
 * </p>
 *
 * <p>
 * This is where you will write code
 * <p>
 *
 * You will need to include your {@link BoardPosition} class, the {@link IGameBoard} interface
 * and both of the {@link IGameBoard} implementations from Project 4.
 * If your code was correct you will not need to make any changes to your {@link IGameBoard} implementation class.
 *
 * @version 2.0
 */
public class TicTacToeController {

    /**
     * <p>
     * The current game that is being played
     * </p>
     */
    private IGameBoard curGame;

    /**
     * <p>
     * The screen that provides our view
     * </p>
     */
    private TicTacToeView screen;

    /**
     * <p>
     * Constant for the maximum number of players, max size of board, and min size of the board
     * </p>
     */
    public static final int MAX_PLAYERS = 10;
    private final int MAX_SIZE = 20;

    private final int MIN_SIZE = 3;
    private boolean gameOver;


    /**
     * <p>
     * The number of players for this game, and the characters for the game. Note that our player tokens are hard coded.
     * </p>
     */
    private int numPlayers;
    private char[] players = {'X','B','C','D','E','F','G','H','I','J'};
    private int curPlayer;

    /**
     * <p>
     * This creates a controller for running the Extended TicTacToe game
     * </p>
     *
     * @param model
     *      The board implementation
     * @param view
     *      The screen that is shown
     * @param np
     *      The number of players for this game.
     *
     * @post [ the controller will respond to actions on the view using the model. ]
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        this.curGame = model;
        this.screen = view;
        this.numPlayers = np;

        this.gameOver = false;
        this.curPlayer = 0;
    }

    /**
     * <p>
     * This processes a button click from the view.
     * </p>
     *
     * @param row
     *      The row of the activated button
     * @param col
     *      The column of the activated button
     *
     * @post [ will allow the player to place a marker in the position if it is a valid space, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button ]
     */
    public void processButtonClick(int row, int col) {
        if (gameOver) {
            newGame();
            return;
        }
        //input validation for the button clicked
        BoardPosition spot = new BoardPosition(row, col);
        String msg = "";
        if (!curGame.checkSpace(spot)) {
            msg = "That space is unavailable, please pick again";
            screen.setMessage(msg);
        }
        else {
            //places the marker on the board
            curGame.placeMarker(spot, players[curPlayer]);
            screen.setMarker(row, col, players[curPlayer]);
            if (curPlayer == numPlayers-1) {
                curPlayer = 0;
            }
            else {
                curPlayer++;
            }

            //checks for winner or draw and resets the board if so, or prints whose turn it is
            if (curGame.checkForWinner(spot)){
                Character winner = players[curPlayer-1];
                msg = winner + "Wins! Press anywhere to play again";
                screen.setMessage(msg);
                gameOver = true;
            }
            else if (curGame.checkForDraw()){
                msg = "Draw! Press anywhere to play again";
                screen.setMessage(msg);
                gameOver = true;
            }
            else {
                msg = players[curPlayer] + "\'s turn:";
                screen.setMessage(msg);
            }
        }

    }

    /**
     * <p>
     * This method will start a new game by returning to the setup screen and controller
     * </p>
     *
     * @post [ a new game gets started ]
     */
    private void newGame() {
        //close the current screen
        screen.dispose();

        //start back at the set up menu
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}