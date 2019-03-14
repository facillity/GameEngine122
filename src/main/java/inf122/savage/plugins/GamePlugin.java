package inf122.savage.plugins;

import inf122.savage.engine.*;

public interface GamePlugin{
	/**
	 * Wrapper to get the current player from the GameState
	 */
	public Player currentPlayer();

	/**
	 * Attempt to make a row in the row/column provided
	 * Returns true and changes the GameState if succeeded.
	 * Will return false if the move is invalid in any way
	 * @return success or failure of move.
	 */
	public boolean move(int row, int col);

	/**
	 * Returns the name of the game
	 * @return String representation of the Game
	 */
	public String getName();

	/**
	 * Returns the index for winning player of the game.
	 * @return 0/1 for player 1/2; 2 for tie; 3 if game not over
	 */
	public int getWinner();


	/**
	 * Returns the winner of the game
	 * @return Player object for winner
	 */
	public Player getWinningPlayer();

}