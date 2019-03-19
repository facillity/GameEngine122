package inf122;

import inf122.savage.engine.GameState;
import inf122.savage.plugins.BaseController;
import inf122.savage.plugins.BaseGame;
import inf122.savage.plugins.BaseView;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class BattleshipView extends BaseView {
    public BattleshipView(AnchorPane board, BaseGame game, BaseController controller){
        super(board, game, controller);
    }
    public void draw(BaseGame game){

        System.out.println("Drawing");
        for(int r=0; r<game.getNumRows()/2; r++){
            for(int c=0; c<game.getNumCols(); c++){
                Canvas cell = (Canvas) this.getBoard().lookup("#" + r + ";" + c);
                GraphicsContext gc = cell.getGraphicsContext2D();
                if(game.getState().getGameBoard().getTile(r, c) == GameState.PLAYER_ONE){
                    game.getViewClass();
                    gc.setFill(Color.RED);
                    gc.fillOval(10, 10,cell.getWidth()-20, cell.getHeight()-20);

                }else if (game.getState().getGameBoard().getTile(r, c) == GameState.PLAYER_TWO){
                    gc.setFill(Color.BLUE);
                    gc.fillOval(10, 10,cell.getWidth()-20, cell.getHeight()-20);
                }
            }
        }

        if(game.getState().currentPlayer == GameState.PLAYER_ONE)
        {


        }

//        if(game.getState().getGameBoard().getTile(r, c) == GameState.PLAYER_TWO) {
//            for (int r = 0; r < game.getNumRows() / 2; r++) {
//                for (int c = 0; c < game.getNumCols(); c++) {
//
//                }
//            }
//        }

        // Check if game is over
        // if so, make a popup or something.
    }
}
