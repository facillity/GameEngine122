package inf122.gui;

import inf122.savage.plugins.BaseController;
import inf122.savage.plugins.GamePlugin;
import inf122.savage.plugins.TicTacToe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;

import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;

class GameButtonLauncher implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent e){
        String gameName = ((Button) e.getSource()).getId();
        System.out.println(gameName);
    }

}


public class SavageController {

    @FXML
    AnchorPane btnslist;


    public void addGame(GamePlugin game){
        Button gameBtn = new Button(game.getName());
        gameBtn.setId(game.getName());
        gameBtn.setOnAction(new GameButtonLauncher());
        btnslist.getChildren().add(gameBtn);
    }

    @FXML
    void ff614a(ActionEvent event) {

    }

    @FXML
    void launchGame(ActionEvent event) throws Exception {
        String gameName = ((Control)event.getSource()).getId();
        System.out.println(gameName);

        //create new scene from class factory gives



        BaseController controller = new BaseController(new TicTacToe());
        controller.show();

    }



}
