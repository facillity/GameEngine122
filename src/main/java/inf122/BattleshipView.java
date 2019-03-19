package inf122;

import inf122.savage.plugins.BaseController;
import inf122.savage.plugins.BaseGame;
import inf122.savage.plugins.BaseView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import static inf122.savage.plugins.BaseGame.PLAYER_ONE;
import static inf122.savage.plugins.BaseGame.PLAYER_TWO;

class BattleshipHandler implements EventHandler<ActionEvent>{
    private Battleship game;
    BattleshipHandler(BaseGame game){
        this.game = (Battleship) game;
    }

    @Override
    public void handle(ActionEvent event){
        this.game.changeOrientation();
    }
}


public class BattleshipView extends BaseView {
    private  boolean swap = false;



    Button rotateButton = new Button("Rotate ship");

    public BattleshipView(AnchorPane board, BaseGame game, BaseController controller) {
        super(board, game, controller);


        board.getChildren().add(rotateButton);
        rotateButton.setTranslateY(board.getPrefHeight());
        rotateButton.setOnAction(new BattleshipHandler(game));

    }

    public void draw(BaseGame game){

        for(int r=0; r<game.getNumRows(); r++){
            for(int c=0; c<game.getNumCols(); c++){
                Canvas cell = this.getCanvasAt(r, c);
                GraphicsContext gc = cell.getGraphicsContext2D();
                if(game.getState().currentPlayer == PLAYER_ONE && !swap ||
                        game.getState().currentPlayer == PLAYER_TWO  && swap) {

                            if(r >= 10) {
                                gc.setFill(Color.LIGHTGRAY);
                                gc.fillRect(0, 0, cell.getWidth(), cell.getHeight());
                            }
                            else
                            {

                                gc.setFill(Color.rgb(241, 220, 212));
                                gc.fillRect(0, 0, cell.getWidth(), cell.getHeight());
                            }

                }

                if(game.getState().currentPlayer == PLAYER_TWO && !swap ||
                        game.getState().currentPlayer == PLAYER_ONE  && swap) {

                            if(r < 10) {
                                gc.setFill(Color.LIGHTGRAY);
                                gc.fillRect(0, 0, cell.getWidth(), cell.getHeight());
                            }
                            else
                            {
                                gc.setFill(Color.rgb(241, 220, 212));
                                gc.fillRect(0, 0, cell.getWidth(), cell.getHeight());
                            }

                }

                if(((Battleship) game).turn == 10)
                {
                    swap = true;
                }
                if(((Battleship) game).turn < 5){
                    if(game.getState().getGameBoard().getTile(r, c) == PLAYER_ONE) {
                        gc.setFill(Color.RED);
                        gc.fillOval(PAD, PAD, cell.getWidth()-2*PAD, cell.getHeight()-2*PAD);
                    }
                    // player 1 sets up ships
                } else if (((Battleship) game).turn >= 5 && ((Battleship) game).turn < 10){
                    if (game.getState().getGameBoard().getTile(r, c) == PLAYER_TWO){
                    gc.setFill(Color.BLUE);
                    gc.fillOval(PAD, PAD, cell.getWidth()-2*PAD, cell.getHeight()-2*PAD);
                }
                } else if (((Battleship) game).turn >= 10){
                    // after set up and player 1's turn
                    // if board is second half
                        // draw stuff

                        if (game.getState().getGameBoard().getTile(r, c) == 3){
                            gc.setFill(Color.GREEN); // missed
                            gc.fillOval(PAD, PAD, cell.getWidth()-2*PAD, cell.getHeight()-2*PAD);
                        } else if (game.getState().getGameBoard().getTile(r, c) == 4){
                            gc.setFill(Color.BLACK); // hit
                            gc.fillOval(PAD, PAD, cell.getWidth()-2*PAD, cell.getHeight()-2*PAD);
                        } else if (game.getState().getGameBoard().getTile(r, c) == 5){
                            gc.setFill(Color.DEEPPINK); // missed
                            gc.fillOval(PAD, PAD, cell.getWidth()-2*PAD, cell.getHeight()-2*PAD);
                        }


                }
            }
        }
    }
}

//public void player1Setup(BaseGame game)
//{
//
//}
