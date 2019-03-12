package inf122.savage.plugins;

import inf122.savage.engine.*;

public abstract class BaseGame implements GamePlugin{
	private GameState state;

	public BaseGame(int row, int col, String player1, String player2){
		// Player p1 = PlayerFactory.getPlayer(player1, this.getName());
		// Player p2 = PlayerFactory.getPlayer(player2, this.getName());
		// this.state = new GameState(row, col, p1, p2);
	}

	/**
	  * Default method just gives you class name 
	  * i.e. "BaseGame"
	  * Changing this value will change what the JSON is indexed by.
	  */
	@Override
	public String getName(){
		return this.getClass().getSimpleName();
	}

	@Override
	public abstract boolean move(int row, int col);


	/**
	  * Default method for getting the current player
	  * This is likely not to be changed.
	  */
	// @Override
	// public Player currentPlayer(){
	// 	return this.state.currentPlayer();
	// }
}