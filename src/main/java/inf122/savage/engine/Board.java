package inf122.savage.engine;

/**
 * Board is a wrapper for a static-sized two-dimensional
 * list of primitive types for a grid board game.
 */
public class Board{
    private int[][] boardSpace;
    private int numRows;
    private int numCols;

	public static final int EMPTY = 0;

	public Board(int userNumRows, int userNumCols){
		if (userNumRows <= 0 || userNumCols <= 0){
			// TO-DO: Handle 0 & Negative rows. Return Null?
		}
		this.numRows = userNumRows;
		this.numCols = userNumCols;
		boardSpace = new int[userNumRows][userNumCols];
		zeroOutBoard();
	}

	public void zeroOutBoard(){
		for (int x = 0; x < numRows; x++){
			for (int y = 0; y < numCols; y++){
				boardSpace[x][y] = 0;
			}
		}
	}

	public boolean change(int userNumRow, int userNumCol, int newTile){
		if (userNumRow < 0 || userNumRow >= this.numRows){
			return false;
		} else if (userNumCol < 0 || userNumCol >= this.numCols){
			return false;
		}
		this.boardSpace[userNumRow][userNumCol] = newTile;
		return true;
	}

	public int getTile(int userNumRow, int userNumCol){
		if (userNumRow < 0 || userNumRow >= this.numRows){
			return -1;
		} else if (userNumCol < 0 || userNumCol >= this.numCols){
			return -1;
		}
		return this.boardSpace[userNumRow][userNumCol];
	}

	public int[][] getBoard(){
		return this.boardSpace;
	}

}