package inf122.gui;

import inf122.savage.plugins.BaseController;
import inf122.TicTacToe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;

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
    void launchGame(ActionEvent event) throws Exception {
        String gameName = ((Control)event.getSource()).getId();
        System.out.println(gameName);

        //create new scene from class factory gives



        BaseController controller = new BaseController(new TicTacToe());
        controller.show();

    }



}
