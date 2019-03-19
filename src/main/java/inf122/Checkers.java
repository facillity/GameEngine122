package inf122;

import inf122.savage.plugins.BaseGame;
import inf122.savage.plugins.BaseView;
import inf122.savage.util.Coordinate;

import static inf122.savage.engine.Board.EMPTY;

public class Checkers extends BaseGame {
    private static final int NUM_ROWS = 10;
    private static final int NUM_COLS = 10;
    private Coordinate selected = new Coordinate(-1, -1);
    private boolean selectLock = false;

    public Checkers(){
        super(NUM_ROWS, NUM_COLS);
        this.createStartingBoard();
    }

    public Checkers(String p1, String p2){
        super(NUM_ROWS, NUM_COLS, p1, p2);
        this.createStartingBoard();
    }


    public Coordinate getSelected(){
        return this.selected;
    }

    /**
     * Attempts to make a move at this location. Moves in Checkers are two-phased:
     * If there is a selected location, and the attempted move is on an empty spot, try to make the move.
     * Otherwise, the selected row/col must correspond to a piece on the board that is the current Player's
     * @param row selected row number on the board
     * @param col selected column number on the board
     * @return true if the move was successful (either selection or movement)
     */
    @Override
    public boolean move(int row, int col) {
        int tile = this.state.getGameBoard().getTile(row, col);

        if(!selectLock && tile == this.state.getCurrentPlayerInt()){
            this.selected = new Coordinate(row, col);
            return true;
        }

        if(this.selected.getRow() != -1){
            if(tile == EMPTY){
                int rowDiff = row - this.selected.getRow();
                int colDiff = col - this.selected.getCol();
                if(!selectLock && Math.abs(rowDiff) == 1 && Math.abs(colDiff) == 1){
                    // Valid move!
                    this.state.getGameBoard().change(row, col, this.state.getCurrentPlayerInt());
                    this.state.getGameBoard().change(selected.getRow(), selected.getCol(), EMPTY);
                    this.switchPlayer();
                    this.checkWinner();
                    return true;
                }
                if(Math.abs(rowDiff) == 2 && Math.abs(colDiff) == 2){

                    //Check the piece in between and consider if there is an enemy piece.

                    int dr = (rowDiff > 0) ? 1 : -1;
                    int dc = (colDiff > 0) ? 1 : -1;

                    int between = this.state.getGameBoard().getTile(this.selected.getRow() + dr, this.selected.getCol() + dc);
                    if(between != EMPTY && between != this.state.getCurrentPlayerInt()){
                        // Valid move!
                        this.state.getGameBoard().change(row, col, this.state.getCurrentPlayerInt());
                        this.state.getGameBoard().change(selected.getRow(), selected.getCol(), EMPTY);
                        this.state.getGameBoard().change(selected.getRow() + dr, selected.getCol() + dc, EMPTY);
                        Coordinate newPos = new Coordinate(row, col);
                        if(canJump(newPos)){
                            this.selectLock = true;
                            this.selected = newPos;
                        }else{
                            this.selectLock = false;
                            switchPlayer();
                        }
                        this.checkWinner();
                        return true;
                    }
                }
                return false;
            }
        }
        // User tried to select an enemy tile or empty.
        return false;
    }

    /**
     * Determines a list of valid moves (empty spaces) with taking enemy space.
     * @param pos position of selected tile (current player's piece)
     * @return true if the user can play another move
     */
    private boolean canJump(Coordinate pos){
        int[] vals = {+1, -1};
        for(int r : vals){
            for(int c : vals){
                int secondR = (r > 0)? 2 : -2;
                int secondC = (c > 0)? 2 : -2;
                int tile = this.state.getGameBoard().getTile(pos.getRow() + r, pos.getCol() + c);
                int target = this.state.getGameBoard().getTile(pos.getRow() + secondR, pos.getCol() + secondC);
                if(tile != EMPTY && tile != this.state.getCurrentPlayerInt() && target == EMPTY){
                    // Can jump again
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void resetGame(){
        super.resetGame();

        this.createStartingBoard();
    }

    /**
     * Checks if there is a winner to the game.
     * If so, will update the winner.
     */
    private void checkWinner(){
        int[][] board = getState().getGameBoard().getBoard();
        int p1count = 0;
        int p2count = 0;
        for(int row = 0; row < NUM_ROWS; ++row) {
            for(int col = 0; col < NUM_COLS; ++col) {
                if(board[row][col] == PLAYER_ONE) {
                    ++p1count;
                }
                else if(board[row][col] == PLAYER_TWO){
                    ++p2count;
                }
            }
        }

        if(p1count == 0)
            this.winner = PLAYER_ONE;
        if(p2count == 0)
            this.winner = PLAYER_TWO;
    }


    /**
     * Switches the player if the move is over.
     * If the user can make another move (by eating another piece)
     * the turn does not change.
     */
    private void switchPlayer(){
        this.state.switchPlayer();
    }

    private void createStartingBoard(){
        for(int r=0; r<this.getNumRows(); r++){
            for(int c=0; c<this.getNumCols(); c++){
                int piece = 0;
                if(r < 4){
                    piece = PLAYER_ONE;
                }else if(r > 5){
                    piece = PLAYER_TWO;
                }
                if((r + c) % 2 == 1){
                    state.getGameBoard().change(r, c, piece);
                }
            }
        }
    }

    @Override
    public Class<? extends BaseView> getViewClass(){
        return CheckersView.class;
    }
}
