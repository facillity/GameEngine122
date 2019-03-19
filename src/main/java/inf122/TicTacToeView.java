package inf122;

import inf122.savage.plugins.BaseController;
import inf122.savage.plugins.BaseGame;
import inf122.savage.plugins.BaseView;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import static inf122.savage.plugins.BaseGame.PLAYER_ONE;
import static inf122.savage.plugins.BaseGame.PLAYER_TWO;


public class TicTacToeView extends BaseView {
    public TicTacToeView(AnchorPane board, BaseGame game, BaseController controller){
        super(board, game, controller);
    }

    public void draw(BaseGame game){
        for(int r=0; r<game.getNumRows(); r++){
            for(int c=0; c<game.getNumCols(); c++){
                Canvas cell = this.getCanvasAt(r, c);
                GraphicsContext gc = cell.getGraphicsContext2D();
                if(game.getState().getGameBoard().getTile(r, c) == PLAYER_ONE){
                    gc.setStroke(Color.BLACK);
                    gc.strokeLine(PAD, PAD, cell.getWidth()-2*PAD, cell.getHeight()-2*PAD);
                    gc.strokeLine(cell.getWidth()-2*PAD, PAD, PAD, cell.getHeight()-2*PAD);
                }else if (game.getState().getGameBoard().getTile(r, c) == PLAYER_TWO){
                    gc.setStroke(Color.BLACK);
                    gc.strokeOval(PAD, PAD, cell.getWidth()-2*PAD, cell.getHeight()-2*PAD);
                }
            }
        }
    }

}
