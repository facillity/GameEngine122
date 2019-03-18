package inf122.savage.plugins;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.geometry.VPos.CENTER;

public abstract class BaseView extends AnchorPane implements Initializable {
    private AnchorPane board;
    private GridPane grid;
    public BaseView(AnchorPane board, BaseGame game, BaseController controller){
        this.board = board;
        board.setPrefWidth(600);
        board.setPrefHeight(600);

        grid = new GridPane();

        double cellWidth = board.getPrefWidth() / game.getNumCols();
        double cellHeight = board.getPrefHeight() / game.getNumRows();

        for(int r=0; r<game.getNumRows(); r++) {
            for (int c = 0; c < game.getNumCols(); c++) {
                Canvas cell = new Canvas(cellWidth, cellHeight);
                cell.getGraphicsContext2D().setFill(Color.BLUE);
                cell.getGraphicsContext2D().fillRect(0, 0, cellWidth - 10, cellHeight - 10);
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

    public abstract void draw(BaseGame game);

    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("BaseView created");
    }
}
