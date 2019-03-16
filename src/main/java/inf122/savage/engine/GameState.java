package inf122.savage.engine;

public class GameState{
    private Player[] players;
    private int currentPlayer;
    private Board gameBoard;

    public static final int PLAYER_ONE = 1;
    public static final int PLAYER_TWO = 2;

    public GameState(Player p1, Player p2, Board board){
        this.players = new Player[2];
        this.players[0] = p1;
        this.players[1] = p2;
        this.currentPlayer = PLAYER_ONE;
        this.gameBoard = board;
    }

    public GameState(Player p1, Player p2, int numRows, int numCols){
        this.players = new Player[2];
        this.players[0] = p1;
        this.players[1] = p2;
        this.currentPlayer = PLAYER_ONE;
        makeBoard(numRows, numCols);
    }

    public boolean reset(){
        this.currentPlayer = PLAYER_ONE;
        this.gameBoard.zeroOutBoard();

        return true;
    }

    public boolean switchPlayer(){
        this.currentPlayer = (this.currentPlayer == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
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
        return this.players[this.currentPlayer-1];
    }

    public Player getPlayer(int index){
        return this.players[index];
    }

    public Board getGameBoard(){
        return this.gameBoard;
    }
}