package inf122.savage.plugins;

import inf122.savage.engine.GameState;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseView extends AnchorPane implements Initializable {
    private AnchorPane board;
    private GridPane grid;
    public BaseView(AnchorPane board, BaseGame game){
        this.board = board;
        grid = new GridPane();



        this.board.getChildren().add(grid);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("BaseView created");
    }
}
