package inf122.gui;

import inf122.savage.plugins.BaseController;
import inf122.TicTacToe;
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

    private String p1Name = "player 1";
    private String p2Name = "player 2";

    @FXML
    void launchGame(ActionEvent event) throws Exception {
        String gameName = ((Control)event.getSource()).getId();
        System.out.println(gameName);

        BaseController controller = GameFactory.createGame(gameName, p1Name, p2Name);
        controller.show();
    }

    @FXML
    void enterPlayer1Name(ActionEvent event) throws Exception
    {
        p1Name = player1Name.getText();
    }

    @FXML
    void enterPlayer2Name(ActionEvent event) throws Exception
    {
        p2Name = player2Name.getText();
    }


}
