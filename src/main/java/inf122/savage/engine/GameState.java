package inf122.savage.engine;

public class GameState{
    private Player Player1;
    private Player Player2;
    private Player currentPlayer;
    private Board GameBoard;

    public GameState(Player P1, Player P2, Board GB){
        this.Player1 = P1;
        this.Player2 = P2;
        this.currentPlayer = P1;
        this.GameBoard = GB;
    }

    public GameState(Player P1, Player P2, int numRows, int numCols){
        this.Player1 = P1;
        this.Player2 = P2;
        this.currentPlayer = P1;
        makeBoard(numRows, numCols);
    }

    public boolean reset(){
        // Reset Player turn.
        this.currentPlayer = this.Player1;

        // Zero out GameBoard.
        this.GameBoard.zeroOutBoard();
        return true;
    }

    public boolean switchPlayer(){
        if (this.currentPlayer == this.Player1){
            this.currentPlayer = this.Player2;
        } else {
            this.currentPlayer = this.Player1;
        }
        return true;
    }

    private boolean makeBoard(int rowCount, int colCount){
        if (rowCount <= 0 || colCount <= 0){
            return false;
        }
        this.GameBoard = new Board(rowCount, colCount);
        return true;
    }
}