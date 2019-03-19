package inf122.savage.engine;

public class GameState{
    private Player[] players;
    public int currentPlayer;
    public Board gameBoard;

    private static final int PLAYER_ONE = 1;
    private static final int PLAYER_TWO = 2;

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

    public void switchPlayer(){
        this.currentPlayer = (this.currentPlayer == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
    }

    private void makeBoard(int rowCount, int colCount){
        if (!(rowCount <= 0 && colCount <= 0)){
            this.gameBoard = new Board(rowCount, colCount);
        }
    }

    public int getCurrentPlayerInt(){
        return this.currentPlayer;
    }

    public Player getCurrentPlayer(){
        return this.getPlayer(this.currentPlayer);
    }

    public Player getPlayer(int index){
        if(index == PLAYER_ONE)
            return this.players[0];
        return this.players[1];
    }

    public Board getGameBoard(){
        return this.gameBoard;
    }
}