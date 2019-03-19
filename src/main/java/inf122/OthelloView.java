package inf122;

import inf122.savage.engine.GameState;
import inf122.savage.plugins.BaseController;
import inf122.savage.plugins.BaseGame;
import inf122.savage.plugins.BaseView;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class OthelloView extends BaseView {
    final Color CELL_BG_COLOR = Color.ORANGE;

    public OthelloView(AnchorPane board, BaseGame game, BaseController controller){ super(board, game, controller);
    }

    public void draw(BaseGame game){


        System.out.println("Drawing");
        this.resetBoard(game);

        for(int r=0; r<game.getNumRows(); r++){
            for(int c=0; c<game.getNumCols(); c++){
                Canvas cell = this.getCanvasAt(r, c);
                GraphicsContext gc = cell.getGraphicsContext2D();

                if(game.getState().getGameBoard().getTile(r, c) == BaseGame.PLAYER_ONE){
                    gc.setFill(Color.BLACK);
                    gc.fillOval(10, 10,cell.getWidth()-20, cell.getHeight()-20);
                }else if (game.getState().getGameBoard().getTile(r, c) == BaseGame.PLAYER_TWO){
                    gc.setFill(Color.WHITE);
                    gc.fillOval(10, 10,cell.getWidth()-20, cell.getHeight()-20);
                }
            }
        }


        // Check if game is over
        // if so, make a popup or something.
    }

    public void resetBoard(BaseGame game)
    {
        for(int r=0; r<game.getNumRows(); r++) {
            for (int c = 0; c < game.getNumCols(); c++) {
                Canvas cell = this.getCanvasAt(r, c);
                GraphicsContext gc = cell.getGraphicsContext2D();
                gc.clearRect(0, 0, cell.getWidth(), cell.getHeight());
                cell.getGraphicsContext2D().setFill(CELL_BG_COLOR);
                cell.getGraphicsContext2D().fillRect(0, 0, cell.getWidth() - 10, cell.getHeight() - 10);
            }
        }
    }

}
