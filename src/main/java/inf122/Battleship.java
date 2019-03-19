package inf122;


import java.util.ArrayList;

import inf122.savage.engine.GameState;
import inf122.savage.engine.Player;
import inf122.savage.plugins.BaseGame;
import inf122.savage.plugins.BaseView;

public class Battleship extends BaseGame {
    private static final int NUM_ROWS = 20;
    private static final int NUM_COLS = 10;

    private static final int EMPTY = 0;
    private static final int PLAYER_1 = 1;
    private static final int PLAYER_2 = 2;
    private static final int MISS = 3;
    private static final int HIT = 4;
    private static final int SUNK = 5;

    public int turn = 0;
    private boolean horizontal = true;

    private ArrayList<ArrayList<ArrayList<Integer>>> player1ships = new ArrayList<ArrayList<ArrayList<Integer>>>();
    private ArrayList<ArrayList<ArrayList<Integer>>> player2ships = new ArrayList<ArrayList<ArrayList<Integer>>>();

    public Battleship(){
        super(NUM_ROWS, NUM_COLS);
        this.winner = GAME_NOT_OVER;
    }

    public Battleship(String p1, String p2){
        super(NUM_ROWS, NUM_COLS, p1, p2);
        this.winner = GAME_NOT_OVER;
    }

    /**
     * Changes orientation of ship.
     * @return
     */
    public boolean changeOrientation(){
        horizontal = !horizontal;
        return true;
    }

    /**
     * Tries to place a ship given the specified parameters onto the board.
     * @param player 1 or 2 based on player
     * @param row
     * @param col
     * @param horizontal places the ship horizontal if true, vertical otherwise.
     * @param shipSize size of ship
     * @return true if ship placement was successful, false otherwise.
     */
    private boolean placeShip(int player, int row, int col, boolean horizontal, int shipSize){
        ArrayList<ArrayList<Integer>> coords = new ArrayList<ArrayList<Integer>>();
        int useRow;
        if (player == 1){
            useRow = row;
        } else {
            useRow = row;
        }
        try {

            for (int idx = 0; idx < shipSize; idx++) {
                if (state.getGameBoard().getTile(useRow, col) == 0) {
                    // if error it returns false because its out of bounds.
                    // valid
                    ArrayList<Integer> tempCoords = new ArrayList<Integer>();
                    tempCoords.add(useRow);
                    tempCoords.add(col);
                    coords.add(tempCoords);
                    if (horizontal) {
                        col++;
                    } else {
                        useRow++;
                    }
                } else {
                    //already used space.
                    return false;
                }

            }
        } catch (Exception e) {
            return false;
        } finally {
            for (int idx = 0; idx < coords.size(); idx++){
                boolean changed = state.getGameBoard().change(coords.get(idx).get(0), coords.get(idx).get(1), player);
                if (!changed){
                    System.out.println("Something went wrong and whole board needs to be reset.");
                }
            }
            // add ship to list of ships
            if (player == 1){
                player1ships.add(coords);
            } else {
                player2ships.add(coords);
            }
            return true;
        }
    }

    @Override
    public boolean move(int row, int col){
        if (turn == 0) {
            if (placeShip(1, row, col, this.horizontal, 5)){
                turn++;
                return true;
            }
        } else if (turn == 1) {
            if (placeShip(1, row, col, this.horizontal, 4)){
                turn++;
                return true;
            }
        } else if (turn == 2) {
            if (placeShip(1, row, col, this.horizontal, 3)){
                turn++;
                return true;
            }
        } else if (turn == 3) {
            if (placeShip(1, row, col, this.horizontal, 3)){
                turn++;
                return true;
            }
        } else if (turn == 4) {
            if (placeShip(1, row, col, this.horizontal, 2)){
                turn++;
                this.state.switchPlayer();
                return true;
            }
        } else if (turn == 5) {
            if (placeShip(2, row, col, this.horizontal, 5)){
                turn++;
                return true;
            }
        } else if (turn == 6) {
            if (placeShip(2, row, col, this.horizontal, 4)){
                turn++;
                return true;
            }
        } else if (turn == 7) {
            if (placeShip(2, row, col, this.horizontal, 3)){
                turn++;
                return true;
            }
        } else if (turn == 8) {
            if (placeShip(2, row, col, this.horizontal, 3)){
                turn++;
                return true;
            }
        } else if (turn == 9) {
            if (placeShip(2, row, col, this.horizontal, 2)){
                turn++;
                this.state.switchPlayer();
                return true;
            }
        } else {
            if (currentPlayer() == state.getPlayer(0)){
                // Current player is first player.
                if (state.getGameBoard().getTile(row, col) == 0 ){
                    // Miss!
                    state.getGameBoard().change(row, col, MISS);
                    this.state.switchPlayer();
                    checkShips();
                    checkWinner();
                    return true;
                } else if (state.getGameBoard().getTile(row, col) == 2) {
                    // Hit!
                    state.getGameBoard().change(row, col, HIT); // hit or miss, i guess they never miss huh
                    state.getPlayer(0).addScore(1);
                    this.state.switchPlayer();
                    checkShips();
                    checkWinner();
                    return true;
                }
                else {
                    return false;
                }
//                if (checkShips(1) || checkShips(2)){
//
//                }
            } else {
                // Current player is second player.
                int useRow = row;
                if (state.getGameBoard().getTile(useRow, col) == 0){
                    // Miss!
                    state.getGameBoard().change(useRow, col, MISS);
                    this.state.switchPlayer();
                    checkShips();
                    checkWinner();
                    return true;
                } else if (state.getGameBoard().getTile(useRow, col) == 1){
                    // Hit!
                    state.getGameBoard().change(useRow, col, HIT);
                    state.getPlayer(1).addScore(1);
                    this.state.switchPlayer();
                    checkShips();
                    checkWinner();
                    return true;
                } else {
                    return false;
                }
            }
//            return false;
        }
        return false;
    }

    /**
     * Runs logic to check if a ship has been sunk. If it has, it updates the board and the ship list
     * stored inside this class for the specified player.
     * @return true if a ship has been sunk, false otherwise.
     */
    private boolean checkShips(){
        // returns true if ship was sunk, false otherwise
        for (int idx = 0; idx < player1ships.size(); idx++){
            int hit_count = 0;
            for (int coord_idx = 0; coord_idx < player1ships.get(idx).size(); coord_idx++){

                if (state.getGameBoard().getTile(player1ships.get(idx).get(coord_idx).get(0),
                        player1ships.get(idx).get(coord_idx).get(1)) == 4){
                    hit_count ++;
                }
            }
            if (hit_count == player1ships.get(idx).size()){
                // ship was sunk
                for (int coord_idx = 0; coord_idx < player1ships.get(idx).size(); coord_idx++){
                    state.getGameBoard().change(player1ships.get(idx).get(coord_idx).get(0),
                            player1ships.get(idx).get(coord_idx).get(1), 5) ;
                }
                player1ships.remove(idx);
                return true;
            } // else ship wasn't sunk
        }
        for (int idx = 0; idx < player2ships.size(); idx++){
            int hit_count = 0;
            for (int coord_idx = 0; coord_idx < player2ships.get(idx).size(); coord_idx++){

                if (state.getGameBoard().getTile(player2ships.get(idx).get(coord_idx).get(0),
                        player2ships.get(idx).get(coord_idx).get(1)) == 4){
                    hit_count ++;
                }
            }
            if (hit_count == player2ships.get(idx).size()){
                // ship was sunk
                for (int coord_idx = 0; coord_idx < player2ships.get(idx).size(); coord_idx++){
                    state.getGameBoard().change(player2ships.get(idx).get(coord_idx).get(0),
                            player2ships.get(idx).get(coord_idx).get(1), 5) ;
                }
                player2ships.remove(idx);
                return true;
            } // else ship wasn't sunk
        }
        return false;

    }

    /**
     * Checks to see if there is a winning player.
     * @return 0= player 1 won, 1= player 2 won, 3= game not over
     */
    public int getWinner(){
        if (player1ships.size() == 0 && turn >= 10){
            System.out.println("Player1win");
            return PLAYER_2;
        } else if (player2ships.size() == 0 && turn >= 10){
            System.out.println("Player2win");
            return PLAYER_1;
        } else {
            return GAME_NOT_OVER;
        }
    }


    /**
     * Returns the player object of the winning player if there is one.
     * WARNING: WILL RETURN NULL IF NO PLAYER HAS WON.
     * @return Player or NULL
     */
    public Player getWinningPlayer(){
        if (this.getWinner() == PLAYER_1){
            return state.getPlayer(GameState.PLAYER_ONE);
        } else if (this.getWinner() == PLAYER_1){
            return state.getPlayer(GameState.PLAYER_TWO);
        } else {
            return null;
        }
    }

    @Override
    public Class<? extends BaseView> getViewClass() {
        return BattleshipView.class;
    }

    private void checkWinner(){
        if (this.getWinner() == 3){
            // game is still going
        } else if (this.getWinner() == PLAYER_1){
            this.winner = PLAYER_1;
        } else if (this.getWinner() == PLAYER_2){
            this.winner = PLAYER_2;
        }

    }
}
