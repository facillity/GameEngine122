package inf122.savage.plugins;

import inf122.savage.engine.Board;

public class TicTacToe extends BaseGame {
    public static final int NUM_ROWS = 3;
    public static final int NUM_COLS = 3;

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
        int[][] board = this.state.getGameBoard().getBoard();
        int win;

        if(checkRows(board) || checkColumns(board) || checkDiagonals(board)){
            return;
        }
    }

    /**
     * Checks if the game is over
     * @return True if game is over (and winner can be found)
     */
    private boolean isGameOver() {
        return this.winner != BaseGame.GAME_NOT_OVER;
    }

    /**
     * Checks if there is a winner in this row
     * @param row length 3 of tic tac toe elements
     * @return 0/1 if player wins, 2 if no winner
     */
    private int checkSeries(int[] row){
        int tile = row[0];
        if (tile != Board.EMPTY && tile == row[1] && tile == row[2]){
            return 1;
        }
        return 2;
    }

    private boolean checkRows(int[][] board){
        int win;
        for(int[] row : board){
            win = checkSeries(row);
            if (win != BaseGame.TIE){
                this.winner = win;
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns(int[][] board){
        int win;
        for(int c=0; c<board[0].length; c++){
            int[] col = {board[0][c], board[1][c], board[2][c]};
            win = checkSeries(col);
            if (win != BaseGame.TIE){
                this.winner = win;
                return true;
            }
        }

        return false;
    }

    private boolean checkDiagonals(int[][] board){
        int win;
        int[][] diags = {{board[0][0], board[1][1], board[2][2]},
                        {board[2][0], board[1][1], board[0][2]}};

        for(int[] diag : diags){
            win = checkSeries(diag);
            if (win != BaseGame.TIE){
                this.winner = win;
                return true;
            }
        }

        return false;
    }

}
