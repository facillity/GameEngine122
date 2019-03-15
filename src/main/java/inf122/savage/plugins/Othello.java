package inf122.savage.plugins;
import inf122.savage.engine.Board;
import java.util.HashSet;
import java.util.ArrayList;

public class Othello extends BaseGame{

    private static final int NUM_ROWS = 8;
    private static final int NUM_COLS = 8;

    public Othello(){
        super(NUM_ROWS, NUM_COLS);
    }

    public Othello(String p1, String p2){
        super(NUM_ROWS, NUM_COLS, p1, p2);
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

        return true;
    }

    private HashSet<ArrayList<Integer>> possibleMoves() {
        HashSet<ArrayList<Integer>> moveList = new HashSet<ArrayList<Integer>>();
        int currentTile = this.state.getCurrentPlayerInt();
        int oppositeTile = (currentTile == 0) ? 1 : 0;
        int [][] gameBoard = this.state.getGameBoard().getBoard();

        for(int row = 0; row < NUM_ROWS; ++row) {
            for(int col = 0; col < NUM_COLS; ++col) {
                if(gameBoard[row][col] == Board.EMPTY) {

                    HashSet<ArrayList<Integer>> validMoves = new HashSet<ArrayList<Integer>>();

                    if(gameBoard[row + 1][col] == oppositeTile) { // move down
                        int posX = row + 1;
                        while(gameBoard[posX][col] == oppositeTile) {
                            ArrayList<Integer> coords = new ArrayList();
                            coords.add(posX); coords.add(col); // Adds Row and Col as coordinates
                            validMoves.add(coords);
                            ++posX;
                        }
                        if(gameBoard[posX][col] == currentTile) {
                            for(ArrayList<Integer> coords: validMoves) {
                                moveList.add(coords);
                            }
                        }
                    }
                    //validMoves.clear();

					else if(this.state.getGameBoard().getTile(row - 1, col) == oppositeTile) { // move up

                    }

                    else if(this.state.getGameBoard().getTile(row, col + 1) == oppositeTile) { // move right

                    }

                    else if(this.state.getGameBoard().getTile(row, col - 1) == oppositeTile) { // move left

                    }

                    else if(this.state.getGameBoard().getTile(row + 1, col + 1) == oppositeTile) { // move down-right

                    }

                    else if(this.state.getGameBoard().getTile(row + 1, col - 1) == oppositeTile) { // move down-left

                    }

                    else if(this.state.getGameBoard().getTile(row - 1, col + 1) == oppositeTile) { // move up-right

                    }

                    else if(this.state.getGameBoard().getTile(row - 1, col - 1) == oppositeTile) { // move up-left

                    }
                }

            }
        }
        return moveList;
    }

}