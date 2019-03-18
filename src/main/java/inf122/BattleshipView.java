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
        for(int r=0; r<game.getNumRows(); r++){
            for(int c=0; c<game.getNumCols(); c++){
                Canvas cell = (Canvas) this.getBoard().lookup("#" + r + ";" + c);
                GraphicsContext gc = cell.getGraphicsContext2D();
                if(game.getState().getGameBoard().getTile(r, c) == GameState.PLAYER_ONE){
                    gc.setStroke(Color.BLACK);
                    gc.strokeLine(5, 5, cell.getWidth()-10, cell.getHeight()-10);
                    gc.strokeLine(cell.getWidth()-10, 5, 5, cell.getHeight()-10);
                }else if (game.getState().getGameBoard().getTile(r, c) == GameState.PLAYER_TWO){
                    gc.setStroke(Color.BLACK);
                    gc.strokeOval(10, 10,cell.getWidth()-20, cell.getHeight()-20);
                }
            }
        }


        // Check if game is over
        // if so, make a popup or something.
    }
}
