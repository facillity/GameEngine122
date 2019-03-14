package inf122.savage.engine;

public class GameState{
    private Player[] players;
    private int currentPlayer;
    private Board gameBoard;

    public GameState(Player p1, Player p2, Board board){
        this.players = new Player[2];
        this.players[0] = p1;
        this.players[1] = p2;
        this.currentPlayer = 0;
        this.gameBoard = board;
    }

    public GameState(Player p1, Player p2, int numRows, int numCols){
        this.players = new Player[2];
        this.players[0] = p1;
        this.players[1] = p2;
        this.currentPlayer = 0;
        makeBoard(numRows, numCols);
    }

    public boolean reset(){
        this.currentPlayer = 0;
        this.gameBoard.zeroOutBoard();

        return true;
    }

    public boolean switchPlayer(){
        this.currentPlayer = (this.currentPlayer == 0) ? 1 : 0;
        return true;
    }

    private boolean makeBoard(int rowCount, int colCount){
        if (rowCount <= 0 || colCount <= 0){
            return false;
        }
        this.gameBoard = new Board(rowCount, colCount);
        return true;
    }

    public int getCurrentPlayerInt(){
        return this.currentPlayer;
    }

    public Player getCurrentPlayer(){
        return this.players[this.currentPlayer];
    }

    public Player getPlayer(int index){
        return this.players[index];
    }

    public Board getGameBoard(){
        return this.gameBoard;
    }
}