package inf122.savage.plugins;

import inf122.savage.engine.Board;

public class TicTacToe extends BaseGame {
    private static final int NUM_ROWS = 3;
    private static final int NUM_COLS = 3;

    public TicTacToe(){
        super(NUM_ROWS, NUM_COLS);
    }

    public TicTacToe(String p1, String p2){
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

        this.state.getGameBoard().change(row, col, this.state.getCurrentPlayerInt() + 1);

        this.state.switchPlayer();

        this.checkWinner();

        return true;
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

    //test for now
    @Override
    public int getWinner() {
        return 3;
    }
}