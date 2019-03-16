package inf122.savage.plugins;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BaseController {
    private BaseView view;
    private BaseGame model;

    public BaseController(BaseGame game){
        this.model = game;
    }

    @FXML
    AnchorPane gameBoard;


    public void show() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("tictactoe.fxml"));
        loader.setController(this);

        Parent root = loader.load();

        view = new BaseView(gameBoard, this.model);


        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("Base Game");
        stage.setScene(new Scene(root, 450, 450));
        stage.show();
    }
}
