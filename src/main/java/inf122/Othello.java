package inf122;
import inf122.savage.engine.Board;
import inf122.savage.plugins.BaseGame;
import inf122.savage.plugins.BaseView;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;

public class Othello extends BaseGame{
	
    public static final int NUM_ROWS = 8;
    public static final int NUM_COLS = 8;
    
    public static int END_CONDITION = 0;
    
    public Othello(){
        super(NUM_ROWS, NUM_COLS);
    }

    public Othello(String p1, String p2){
        super(NUM_ROWS, NUM_COLS, p1, p2);
        int [][] gameBoard = this.state.getGameBoard().getBoard();
        for(int row = 0; row < NUM_ROWS; ++row) {
			for(int col = 0; col < NUM_COLS; ++col) {
				gameBoard[row][col] = Board.EMPTY;
			}
		}
        
        gameBoard[3][3] = 1;
        gameBoard[3][4] = 2;
        gameBoard[4][4] = 1;
        gameBoard[4][3] = 2;
    }

	@Override
	public boolean move(int row, int col) {
        //Checks if move is out of bounds
        if(row < 0 || row > NUM_ROWS - 1 || col < 0 || col > NUM_COLS - 1){
            return false;
        }
        // Checks if there is already something at this position
        if(this.state.getGameBoard().getTile(row, col) != Board.EMPTY){
            return false;
        }
        
        //Checks for all possible moves available for current player
        HashMap<Integer, HashSet<ArrayList<Integer>>> moveList = this.possibleMoves();
        
        // If no moves available for current player, increment END_CONDITION. If END_CONDITION reaches 2, both players do not have
        // available moves meaning the end of the game has been reached.
        if(moveList.isEmpty()) {
        	// if no possible move for player
        	++END_CONDITION;
        	this.state.switchPlayer();
        	return false;
        }
        else {
        	END_CONDITION = 0;
        	int coord = this.concatInt(row, col);
        	int currentTile = this.state.getCurrentPlayerInt();
        	System.out.println("Current player: " + currentTile);
        	if(!moveList.containsKey(coord)) {
        		return false;
        	}
        	this.state.getGameBoard().getBoard()[row][col] = currentTile;
        	for(ArrayList<Integer> coords: moveList.get(coord)) {
        		System.out.println(this.concatInt(coords.get(0), coords.get(1)));
        		this.state.getGameBoard().getBoard()[coords.get(0)][coords.get(1)] = currentTile;
        	}
        }
        this.state.switchPlayer();
        return true;
	}
	
	public boolean gameOver() {
		return END_CONDITION >= 2;
	}
	
	public void calculateWinner() {
		int[][] gameBoard = this.state.getGameBoard().getBoard();
		int player1 = 0;
		int player2 = 0;
        for(int row = 0; row < NUM_ROWS; ++row) {
			for(int col = 0; col < NUM_COLS; ++col) {
				if(gameBoard[row][col] == 1) {
					++player1;
				}
				else if(gameBoard[row][col] == 2){
					++player2;
				}
			}
		}
        if(player1 == player2) {
        	this.winner = BaseGame.TIE;
        }
        else {
        	this.winner = (player1 > player2) ? 1 : 2;
        }
	}
	
	public void printBoard() {
		int[][] gameBoard = this.state.getGameBoard().getBoard();
		String board = "";
        for(int row = 0; row < NUM_ROWS; ++row) {
			for(int col = 0; col < NUM_COLS; ++col) {
				board += gameBoard[row][col] + " ";
			}
			board+= "\n";
		}
        System.out.println(board);
	}
	
	public int concatInt(int num1, int num2) {
		// Concat two ints together. 
		// Ex: concatInt(1, 2) = 12;
		return Integer.parseInt(Integer.toString(num1) + Integer.toString(num2));
	}

	private HashMap<Integer, HashSet<ArrayList<Integer>>> possibleMoves() {
		HashMap<Integer, HashSet<ArrayList<Integer>>> moveList = new HashMap<Integer, HashSet<ArrayList<Integer>>>();
		int currentTile = this.state.getCurrentPlayerInt();
		int oppositeTile = (currentTile == 1) ? 2 : 1;
		int [][] gameBoard = this.state.getGameBoard().getBoard();
		// Loop through board to find empty rows.
		for(int row = 0; row < NUM_ROWS; ++row) {
			for(int col = 0; col < NUM_COLS; ++col) {
				if(gameBoard[row][col] == Board.EMPTY) {
					// Store coordinates that needs to flip if coordinate is valid.
					// ArrayList use to store as coordinates where as HashSet holds all flippable coords from given coordinate.
					// Applies logic of Othello, checks in every direction to see if a coordinate is flippable / valid.
					if(this.state.getGameBoard().getTile(row + 1, col) == oppositeTile) { // move down
						int posX = row + 1;
						System.out.println("Start here: ("+ row + ", " + col + ")");
						HashSet<ArrayList<Integer>> moveSet = new HashSet<ArrayList<Integer>>();
						while(this.state.getGameBoard().getTile(posX, col) == oppositeTile) {
							ArrayList<Integer> coords = new ArrayList<Integer>();
							coords.add(posX); coords.add(col); // Adds Row and Col as coordinates
							moveSet.add(coords);
							System.out.println("Next: ("+ coords.get(0) + ", " + coords.get(1) + ")");
							++posX;
						}
						if(this.state.getGameBoard().getTile(posX, col) == currentTile) {
							System.out.println("Valid match!");
							moveList.put(this.concatInt(row,  col), moveSet);
						}
						else {System.out.println("Invalid match!");}
					}
					
					if(this.state.getGameBoard().getTile(row - 1, col) == oppositeTile) { // move up
						int posX = row - 1;
						System.out.println("Start here: ("+ row + ", " + col + ")");
						HashSet<ArrayList<Integer>> moveSet = new HashSet<ArrayList<Integer>>();
						while(this.state.getGameBoard().getTile(posX, col) == oppositeTile) {
							ArrayList<Integer> coords = new ArrayList<Integer>();
							coords.add(posX); coords.add(col); // Adds Row and Col as coordinates
							moveSet.add(coords);
							System.out.println("Next: ("+ coords.get(0) + ", " + coords.get(1) + ")");
							--posX;
						}
						if(this.state.getGameBoard().getTile(posX, col) == currentTile) {
							System.out.println("Valid match!");/*
							System.out.println("Valid coordinate " + 
									this.concatInt(row,  col) +
									" at : (" + row + ", " + col + ")");*/
							moveList.put(this.concatInt(row,  col), moveSet);
						}
						else {System.out.println("Invalid match!");}
					}
					
					if(this.state.getGameBoard().getTile(row, col + 1) == oppositeTile) { // move right
						int posY = col + 1;
						System.out.println("Start here: ("+ row + ", " + col + ")");
						HashSet<ArrayList<Integer>> moveSet = new HashSet<ArrayList<Integer>>();
						while(this.state.getGameBoard().getTile(row, posY) == oppositeTile) {
							ArrayList<Integer> coords = new ArrayList<Integer>();
							coords.add(row); coords.add(posY); // Adds Row and Col as coordinates
							moveSet.add(coords);
							System.out.println("Next: ("+ coords.get(0) + ", " + coords.get(1) + ")");
							++posY;
						}
						if(this.state.getGameBoard().getTile(row, posY) == currentTile) {
							System.out.println("Valid match!");
							moveList.put(this.concatInt(row,  col), moveSet);
						}
						else {System.out.println("Invalid match!");}
					}
					
					if(this.state.getGameBoard().getTile(row, col - 1) == oppositeTile) { // move left
						int posY = col - 1;
						System.out.println("Start here: ("+ row + ", " + col + ")");
						HashSet<ArrayList<Integer>> moveSet = new HashSet<ArrayList<Integer>>();
						while(this.state.getGameBoard().getTile(row, posY) == oppositeTile) {
							ArrayList<Integer> coords = new ArrayList<Integer>();
							coords.add(row); coords.add(posY); // Adds Row and Col as coordinates
							moveSet.add(coords);
							System.out.println("Next: ("+ coords.get(0) + ", " + coords.get(1) + ")");
							--posY;
						}
						if(this.state.getGameBoard().getTile(row, posY) == currentTile) {
							System.out.println("Valid match!");
							moveList.put(this.concatInt(row,  col), moveSet);
						}
						else {System.out.println("Invalid match!");}
					}
					
					if(this.state.getGameBoard().getTile(row + 1, col + 1) == oppositeTile) { // move down-right
						int posX = row + 1;
						int posY = col + 1;
						System.out.println("Start here: ("+ row + ", " + col + ")");
						HashSet<ArrayList<Integer>> moveSet = new HashSet<ArrayList<Integer>>();
						while(this.state.getGameBoard().getTile(posX, posY) == oppositeTile) {
							ArrayList<Integer> coords = new ArrayList<Integer>();
							coords.add(posX); coords.add(posY); // Adds Row and Col as coordinates
							moveSet.add(coords);
							System.out.println("Next: ("+ coords.get(0) + ", " + coords.get(1) + ")");
							++posX;
							++posY;
						}
						if(this.state.getGameBoard().getTile(posX, posY) == currentTile) {
							System.out.println("Valid match!");
							moveList.put(this.concatInt(row,  col), moveSet);
						}
						else {System.out.println("Invalid match!");}
					}
					
					if(this.state.getGameBoard().getTile(row + 1, col - 1) == oppositeTile) { // move down-left
						int posX = row + 1;
						int posY = col - 1;
						System.out.println("Start here: ("+ row + ", " + col + ")");
						HashSet<ArrayList<Integer>> moveSet = new HashSet<ArrayList<Integer>>();
						while(this.state.getGameBoard().getTile(posX, posY) == oppositeTile) {
							ArrayList<Integer> coords = new ArrayList<Integer>();
							coords.add(posX); coords.add(posY); // Adds Row and Col as coordinates
							moveSet.add(coords);
							System.out.println("Next: ("+ coords.get(0) + ", " + coords.get(1) + ")");
							++posX;
							--posY;
						}
						if(this.state.getGameBoard().getTile(posX, posY) == currentTile) {
							System.out.println("Valid match!");
							moveList.put(this.concatInt(row,  col), moveSet);
						}
						else {System.out.println("Invalid match!");}
					}
					
					if(this.state.getGameBoard().getTile(row - 1, col + 1) == oppositeTile) { // move up-right
						int posX = row - 1;
						int posY = col + 1;
						
						System.out.println("Start here: ("+ row + ", " + col + ")");
						HashSet<ArrayList<Integer>> moveSet = new HashSet<ArrayList<Integer>>();
						while(this.state.getGameBoard().getTile(posX, posY) == oppositeTile) {
							ArrayList<Integer> coords = new ArrayList<Integer>();
							coords.add(posX); coords.add(posY); // Adds Row and Col as coordinates
							moveSet.add(coords);
							System.out.println("Next: ("+ coords.get(0) + ", " + coords.get(1) + ")");
							--posX;
							++posY;
						}
						if(this.state.getGameBoard().getTile(posX, posY) == currentTile) {
							System.out.println("Valid match!");
							moveList.put(this.concatInt(row,  col), moveSet);
						}
						else {System.out.println("Invalid match!");}
					}
					
					if(this.state.getGameBoard().getTile(row - 1, col - 1) == oppositeTile) { // move up-left
						int posX = row - 1;
						int posY = col - 1;
						System.out.println("Start here: ("+ row + ", " + col + ")");
						HashSet<ArrayList<Integer>> moveSet = new HashSet<ArrayList<Integer>>();
						while(this.state.getGameBoard().getTile(posX, posY) == oppositeTile) {
							ArrayList<Integer> coords = new ArrayList<Integer>();
							coords.add(posX); coords.add(posY); // Adds Row and Col as coordinates
							moveSet.add(coords);
							System.out.println("Next: ("+ coords.get(0) + ", " + coords.get(1) + ")");
							--posX;
							--posY;
						}
						if(this.state.getGameBoard().getTile(posX, posY) == currentTile) {
							System.out.println("Valid match!");
							moveList.put(this.concatInt(row,  col), moveSet);
						}
						else {System.out.println("Invalid match!");}
					}
				}
				
			}
		}
		return moveList;
	}

    @Override
    public Class<? extends BaseView> getViewClass(){
        return OthelloView.class;
    }

}