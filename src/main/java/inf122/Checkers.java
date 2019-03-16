package inf122;

import inf122.savage.engine.Board;
import inf122.savage.plugins.BaseGame;
import inf122.savage.plugins.BaseView;
import inf122.savage.util.Coordinate;


public class Checkers extends BaseGame {
    private static final int NUM_ROWS = 8;
    private static final int NUM_COLS = 8;
    private Coordinate selected;

    public Checkers(){
        super(NUM_ROWS, NUM_COLS);
    }

    public Checkers(String p1, String p2){
        super(NUM_ROWS, NUM_COLS, p1, p2);
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

        if(tile == this.state.getCurrentPlayerInt()){
            this.selected = new Coordinate(row, col);
            return true;
        }

        if(this.selected != null){
            if(tile == Board.EMPTY){
                int rowDiff = row - this.selected.getRow();
                int colDiff = col - this.selected.getCol();
                if(Math.abs(rowDiff) == 1 && Math.abs(colDiff) == 1){
                    // Valid move!
                    this.state.getGameBoard().change(row, col, this.state.getCurrentPlayerInt());
                    this.state.getGameBoard().change(selected.getRow(), selected.getCol(), Board.EMPTY);
                    this.switchPlayer(row, col);
                    return true;
                }
                if(Math.abs(rowDiff) == 2 && Math.abs(colDiff) == 2){
                    //Check the piece in between and consider if there is an enemy piece.
                    int dr = (rowDiff > 0) ? -1 : +1;
                    int dc = (colDiff > 0) ? -1 : +1;
                    int between = this.state.getGameBoard().getTile(this.selected.getRow() + dr, this.selected.getCol() + dc);
                    if(between != Board.EMPTY && between != this.state.getCurrentPlayerInt()){
                        // Valid move!
                        this.state.getGameBoard().change(row, col, this.state.getCurrentPlayerInt());
                        this.state.getGameBoard().change(selected.getRow(), selected.getCol(), Board.EMPTY);
                        this.state.getGameBoard().change(selected.getRow() + dr, selected.getCol() + dc, Board.EMPTY);
                        this.switchPlayer(row, col);
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
                int tile = this.state.getGameBoard().getTile(pos.getRow() + r, pos.getCol() + c);
                if(tile != Board.EMPTY && tile != this.state.getCurrentPlayerInt()){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks if there is a winner to the game.
     * If so, will update the winner.
     */
    private void checkWinner(){

    }

    /**
     * Checks if the game is over
     * @return True if game is over (and winner can be found)
     */
    private boolean isGameOver() {
        return this.winner != BaseGame.GAME_NOT_OVER;
    }

    /**
     * Switches the player if the move is over.
     * If the user can make another move (by eating another piece)
     * the turn does not change.
     */
    private void switchPlayer(int row, int col){
        Coordinate pos = new Coordinate(row, col);
        if (!this.canJump(pos)){
            this.state.switchPlayer();
        }
    }

    @Override
    public Class<? extends BaseView> getViewClass(){
        return BaseView.class;
    }
}
