package inf122.gui;

import inf122.savage.plugins.BaseController;
import inf122.savage.util.GameFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;


public class SavageController {
    @FXML
    private TextField player1Name;

    @FXML
    private TextField player2Name;


    @FXML
    void launchGame(ActionEvent event) throws Exception {
        String gameName = ((Control)event.getSource()).getId();
        String p1 = (player1Name.getText().isEmpty()) ? "Player 1" : player1Name.getText();
        String p2 = (player2Name.getText().isEmpty()) ? "Player 2" : player2Name.getText();

        BaseController controller = GameFactory.createGame(gameName, p1, p2);
        controller.show();
    }

}
