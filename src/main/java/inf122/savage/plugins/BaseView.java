package inf122.savage.plugins;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseView extends AnchorPane implements Initializable {
    private AnchorPane board;
    private GridPane grid;
    public BaseView(AnchorPane board, BaseGame game, BaseController controller){
        this.board = board;
        grid = new GridPane();


        for(int r=0; r<game.getNumRows(); r++){
            for(int c=0; c<game.getNumCols(); c++){
                Canvas cell = new Canvas(100, 100);
                cell.getGraphicsContext2D().setFill(Color.BLUE);
                cell.getGraphicsContext2D().fillRect(0, 0, 90, 90);
                cell.setId(r + ";" + c);
                cell.setOnMouseClicked(controller);
                grid.add(cell, c, r);
            }
        }
        board.getChildren().add(grid);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("BaseView created");
    }
}
