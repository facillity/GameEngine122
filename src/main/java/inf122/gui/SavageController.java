package inf122.gui;

import inf122.savage.plugins.BaseController;
import inf122.TicTacToe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Control;


public class SavageController {
    @FXML
    void launchGame(ActionEvent event) throws Exception {
        String gameName = ((Control)event.getSource()).getId();
        System.out.println(gameName);

        //create new scene from class factory gives
//        BaseController controller = GameFactory.createGame(gameName);
//        controller.show();
////
        BaseController controller = new BaseController(new TicTacToe());
        controller.show();

    }



}
