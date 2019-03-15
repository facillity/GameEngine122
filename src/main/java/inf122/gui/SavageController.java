package inf122.gui;

import inf122.savage.plugins.GamePlugin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javafx.event.EventHandler;

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
    void launchGame(ActionEvent event) {
        String gameName = event.getTarget().toString().substring(event.getTarget().toString().indexOf("'"));
        System.out.println(gameName);

    }



}
