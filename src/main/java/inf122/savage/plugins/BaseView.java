package inf122.savage.plugins;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class BaseView extends AnchorPane implements Initializable {

    private AnchorPane board;
    private GridPane grid;
    private BaseController controller;

    public static final Color BG_COLOR = Color.rgb(241, 220, 212);

    double cellWidth;
    double cellHeight;

    public BaseView(AnchorPane board, BaseGame game, BaseController controller){
        this.board = board;
        this.controller = controller;

        board.setPrefWidth(600);
        board.setPrefHeight(600);

        grid = new GridPane();


        cellWidth = board.getPrefWidth() / game.getNumCols();
        cellHeight = board.getPrefHeight() / game.getNumRows();

        for(int r=0; r<game.getNumRows(); r++) {
            for (int c = 0; c < game.getNumCols(); c++) {
                Canvas cell = new Canvas(cellWidth, cellHeight);

                cell.getGraphicsContext2D().setFill(Color.rgb(241, 220, 212));
                cell.getGraphicsContext2D().fillRect(5, 5, cellWidth - 10, cellHeight - 10);


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
                cell.getGraphicsContext2D().setFill(BG_COLOR);
                cell.getGraphicsContext2D().fillRect(0, 0, cellWidth - 10, cellHeight - 10);
            }
        }
    }


    public abstract void draw(BaseGame game);



    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("BaseView created");
    }
}
