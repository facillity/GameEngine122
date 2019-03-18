package inf122.savage.plugins;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class BaseView extends AnchorPane implements Initializable {

    private AnchorPane board;
    private GridPane grid;
    private BaseController controller;

    public BaseView(AnchorPane board, BaseGame game, BaseController controller){
        this.board = board;
        this.controller = controller;
        grid = new GridPane();


        for(int r=0; r<game.getNumRows(); r++){
            for(int c=0; c<game.getNumCols(); c++){
                Canvas cell = new Canvas(100, 100);
                cell.getGraphicsContext2D().setFill(Color.rgb(241, 220, 212));
                cell.getGraphicsContext2D().fillRect(0, 0, 90, 90);
                cell.setId(r + ";" + c);
                cell.setOnMouseClicked(controller);
                grid.add(cell, c, r);
            }
        }
        board.getChildren().add(grid);
    }

    public AnchorPane getBoard(){
        return this.board;
    }

    public void resetBoard(BaseGame game)
    {
        for(int r=0; r<game.getNumRows(); r++) {
            for (int c = 0; c < game.getNumCols(); c++) {
                Canvas cell = (Canvas) this.getBoard().lookup("#" + r + ";" + c);
                GraphicsContext gc = cell.getGraphicsContext2D();
                gc.clearRect(0, 0, cell.getWidth(), cell.getHeight());
                cell.getGraphicsContext2D().setFill(Color.rgb(241, 220, 212));
                cell.getGraphicsContext2D().fillRect(0, 0, 90, 90);
            }
        }
    }


    public abstract void draw(BaseGame game);



    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("BaseView created");
    }
}
