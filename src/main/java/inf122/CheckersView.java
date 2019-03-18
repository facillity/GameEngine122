package inf122;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import inf122.savage.plugins.BaseController;
import inf122.savage.plugins.BaseGame;
import inf122.savage.plugins.BaseView;
import inf122.savage.util.Coordinate;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class CheckersView extends BaseView {
    public CheckersView(AnchorPane board, BaseGame game, BaseController controller){
        super(board, game, controller);
    }

    @Override
    public void draw(BaseGame game){
        Coordinate selected = ((Checkers) game).getSelected();
        if(selected.isValid()){
            drawSelectedBorder(selected.getRow(), selected.getCol());
        }


        for(int r=0; r<game.getNumRows(); r++){
            for(int c=0; c<game.getNumCols(); c++){
                Canvas cell = (Canvas) this.getBoard().lookup("#" + r + ";" + c);
                GraphicsContext gc = cell.getGraphicsContext2D();
                gc.setFill(BG_COLOR);

                if(r != selected.getRow() && c != selected.getCol())
                    gc.fillRect(5, 5, cell.getWidth()-10, cell.getHeight()-10);


                if(game.getState().getGameBoard().getTile(r, c) == game.getState().PLAYER_ONE){
                    gc.setFill(Color.BLACK);
                    gc.fillOval(10, 10, cell.getWidth()-20, cell.getHeight()-20);
                }else if(game.getState().getGameBoard().getTile(r, c) == game.getState().PLAYER_TWO){
                    gc.setFill(Color.RED);
                    gc.fillOval(10, 10, cell.getWidth()-20, cell.getHeight()-20);
                }else{
                    gc.fillRect(5, 5, cell.getWidth()-10, cell.getHeight()-10);
                }
            }
        }
    }

    private void drawSelectedBorder(int row, int col){
        Canvas cell = (Canvas) this.getBoard().lookup("#" + row + ";" + col);
        GraphicsContext gc = cell.getGraphicsContext2D();
        gc.setFill(Color.YELLOW);
        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(3);
        gc.strokeRect(10, 10, cell.getWidth()-20, cell.getHeight()-20);
    }
}
