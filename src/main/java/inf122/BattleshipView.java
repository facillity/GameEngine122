package inf122;

import inf122.savage.engine.GameState;
import inf122.savage.plugins.BaseController;
import inf122.savage.plugins.BaseGame;
import inf122.savage.plugins.BaseView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

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


    AnchorPane p2anchor;

    Button rotateButton = new Button("Rotate ship");

    public BattleshipView(AnchorPane board, BaseGame game, BaseController controller) {
        super(board, game, controller);


        board.getChildren().add(rotateButton);
        rotateButton.setTranslateY(board.getPrefHeight());
//        board.getScene().getWindow().sizeToScene();
        rotateButton.setOnAction(new BattleshipHandler(game));

    }

    public void draw(BaseGame game){

        System.out.println("Drawing");
        for(int r=0; r<game.getNumRows(); r++){
            for(int c=0; c<game.getNumCols(); c++){
                Canvas cell = (Canvas) this.getBoard().lookup("#" + r + ";" + c);
                GraphicsContext gc = cell.getGraphicsContext2D();
                if(game.getState().currentPlayer == GameState.PLAYER_ONE && !swap ||
                        game.getState().currentPlayer == GameState.PLAYER_TWO  && swap) {
                    //System.out.println("here1");

                            if(r >= 10) {
                                gc.setFill(Color.LIGHTGRAY);
                                gc.fillRect(5, 5, cell.getWidth() - 10, cell.getHeight() - 10);
                            }
                            else
                            {

                                gc.setFill(Color.rgb(241, 220, 212));
                                gc.fillRect(5, 5, cell.getWidth() - 10, cell.getHeight() - 10);
                            }

                }

                if(game.getState().currentPlayer == GameState.PLAYER_TWO && !swap ||
                        game.getState().currentPlayer == GameState.PLAYER_ONE  && swap) {

                            if(r < 10) {
                                gc.setFill(Color.LIGHTGRAY);
                                gc.fillRect(5, 5, cell.getWidth() - 10, cell.getHeight() - 10);
                            }
                            else
                            {
                                gc.setFill(Color.rgb(241, 220, 212));
                                gc.fillRect(5, 5, cell.getWidth() - 10, cell.getHeight() - 10);
                            }

                }

                if(((Battleship) game).turn == 10)
                {
                    swap = true;
                }
                if(((Battleship) game).turn < 5){
                    if(game.getState().getGameBoard().getTile(r, c) == GameState.PLAYER_ONE) {
                        gc.setFill(Color.RED);
                        gc.fillOval(10, 10, cell.getWidth() - 20, cell.getHeight() - 20);
                    }
                    // player 1 sets up ships
                } else if (((Battleship) game).turn >= 5 && ((Battleship) game).turn < 10){
                    if (game.getState().getGameBoard().getTile(r, c) == GameState.PLAYER_TWO){
                    gc.setFill(Color.BLUE);
                    gc.fillOval(10, 10,cell.getWidth()-20, cell.getHeight()-20);
                }
                } else if (((Battleship) game).turn >= 10){
                    // after set up and player 1's turn
                    // if board is second half
                        // draw stuff

                        if (game.getState().getGameBoard().getTile(r, c) == 3){
                            gc.setFill(Color.GREEN); // missed
                            gc.fillOval(10, 10,cell.getWidth()-20, cell.getHeight()-20);
                        } else if (game.getState().getGameBoard().getTile(r, c) == 4){
                            gc.setFill(Color.BLACK); // hit
                            gc.fillOval(10, 10,cell.getWidth()-20, cell.getHeight()-20);
                        } else if (game.getState().getGameBoard().getTile(r, c) == 5){
                            gc.setFill(Color.DEEPPINK); // missed
                            gc.fillOval(10, 10,cell.getWidth()-20, cell.getHeight()-20);
                        }


                }
//                if(game.getState().getGameBoard().getTile(r, c) == GameState.PLAYER_ONE){
//                    gc.setFill(Color.RED);
//                    gc.fillOval(10, 10,cell.getWidth()-20, cell.getHeight()-20);
//
//                }else if (game.getState().getGameBoard().getTile(r, c) == GameState.PLAYER_TWO){
//                    gc.setFill(Color.BLUE);
//                    gc.fillOval(10, 10,cell.getWidth()-20, cell.getHeight()-20);
//                }
            }
        }

    }
}

//public void player1Setup(BaseGame game)
//{
//
//}
