package inf122;

import inf122.savage.plugins.BaseController;
import inf122.savage.plugins.BaseGame;
import inf122.savage.plugins.BaseView;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class TicTacToeView extends BaseView {
    public TicTacToeView(AnchorPane board, BaseGame game, BaseController controller){
        super(board, game, controller);
    }

    public void draw(BaseGame game){
        System.out.println("Drawing");
        for(int r=0; r<game.getNumRows(); r++){
            for(int c=0; c<game.getNumCols(); c++){
                Canvas cell = (Canvas) this.getBoard().lookup("#" + r + ";" + c);
                GraphicsContext gc = cell.getGraphicsContext2D();
                if(game.getState().getGameBoard().getTile(r, c) == game.getState().PLAYER_ONE){
                    gc.setStroke(Color.BLACK);
                    gc.strokeLine(5, 5, cell.getWidth()-20, cell.getHeight()-20);
                    gc.strokeLine(cell.getWidth()-20, 5, 5, cell.getHeight()-20);
                }else if (game.getState().getGameBoard().getTile(r, c) == game.getState().PLAYER_TWO){
                    gc.setFill(Color.WHITE);
                    gc.fillOval(5, 5, cell.getWidth()-20, cell.getHeight()-20);
                }
            }
        }
        // Check if game is over
        // if so, make a popup or something.
    }
}
