package inf122;

import inf122.savage.plugins.BaseController;
import inf122.savage.plugins.BaseGame;
import inf122.savage.plugins.BaseView;
import inf122.savage.util.Coordinate;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import static inf122.savage.plugins.BaseGame.PLAYER_ONE;
import static inf122.savage.plugins.BaseGame.PLAYER_TWO;

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
                Canvas cell = this.getCanvasAt(r, c);
                GraphicsContext gc = cell.getGraphicsContext2D();
                gc.setFill(BG_COLOR);


                if(!selected.equals(new Coordinate(r, c)))
                    gc.fillRect(0, 0, cell.getWidth(), cell.getHeight());

                if(game.getState().getGameBoard().getTile(r, c) == PLAYER_ONE){
                    gc.setFill(Color.BLACK);
                    gc.fillOval(PAD, PAD, cell.getWidth()-2*PAD, cell.getHeight()-2*PAD);
                }else if(game.getState().getGameBoard().getTile(r, c) == PLAYER_TWO){
                    gc.setFill(Color.RED);
                    gc.fillOval(PAD, PAD, cell.getWidth()-2*PAD, cell.getHeight()-2*PAD);
                }else{
                    gc.fillRect(0, 0, cell.getWidth(), cell.getHeight());
                }
            }
        }
    }

    private void drawSelectedBorder(int row, int col){
        Canvas cell = this.getCanvasAt(row, col);
        GraphicsContext gc = cell.getGraphicsContext2D();
        gc.setFill(Color.YELLOW);
        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(3);
        gc.strokeRect(PAD, PAD, cell.getWidth()-2*PAD, cell.getHeight()-2*PAD);
    }
}
