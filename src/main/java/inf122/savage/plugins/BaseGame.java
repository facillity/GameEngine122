package inf122.savage.plugins;

import inf122.savage.engine.*;
import inf122.savage.util.PlayerFactory;


public abstract class BaseGame {
	protected GameState state;
	protected int winner = GAME_NOT_OVER;

	private static final int DEFAULT_NUM_ROWS = 8;
	private static final int DEFAULT_NUM_COLS = 8;

	public static final int PLAYER_ONE = 1;
	public static final int PLAYER_TWO = 2;
	public static final int GAME_NOT_OVER = 3;
	public static final int TIE = 4;

	private int numRows;
	private int numCols;

	public BaseGame(){
		this(DEFAULT_NUM_ROWS, DEFAULT_NUM_COLS);
	}

	public BaseGame(int row, int col){
		this(row, col, "Player 1", "Player 2");
	}

	public BaseGame(int row, int col, String p){
		this(row, col, p, "Player 2");
	}

	public BaseGame(int row, int col, String player1, String player2){
		this.numRows = row;
		this.numCols = col;
		Player p1 = PlayerFactory.loadPlayer(player1, this.getName());
		Player p2 = PlayerFactory.loadPlayer(player2, this.getName());
		this.state = new GameState(p1, p2, row, col);
	}


	public Player getWinningPlayer(){
		return this.state.getPlayer(this.winner);
	}

	public int getNumRows(){
		return this.numRows;
	}

	public int getNumCols(){
		return this.numCols;
	}

	/**
	 * Default method for getting the current player
	 * This is likely not to be changed.
	 */
	protected Player currentPlayer(){
		return this.state.getCurrentPlayer();
	}

	public int getWinner() {
		return this.winner;
	}

	/**
	 * Default method just gives you class name
	 * i.e. "BaseGame"
	 * Changing this value will change what the JSON is indexed by.
	 */
	public String getName(){
		return this.getClass().getSimpleName();
	}

	public GameState getState(){
		return this.state;
	}

	public void resetGame(){
		this.state.currentPlayer = PLAYER_ONE;
		this.state.gameBoard.zeroOutBoard();
		if(this.winner < GAME_NOT_OVER){
			// is not tie or game not over
			this.getWinningPlayer().addWin();
			PlayerFactory.savePlayer(this.getWinningPlayer(), this.getName());
		}


		this.winner = GAME_NOT_OVER;
	}

	public abstract boolean move(int row, int col);

	public abstract Class<? extends BaseView> getViewClass();
}