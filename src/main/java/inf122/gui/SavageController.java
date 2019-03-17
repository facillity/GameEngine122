package inf122.gui;

import inf122.savage.plugins.BaseController;
import inf122.TicTacToe;
import inf122.savage.util.GameFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;

import javafx.event.EventHandler;


public class SavageController {
    @FXML
    void launchGame(ActionEvent event) throws Exception {
        String gameName = ((Control)event.getSource()).getId();
        System.out.println(gameName);

        BaseController controller = GameFactory.createGame(gameName, "Player 1", "Player 2");

        controller.show();
    }



}
