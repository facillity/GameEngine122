package inf122;

import inf122.savage.plugins.TicTacToe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {


    @FXML
    void ff614a(ActionEvent event) {

    }

    @FXML
    void launchGame(ActionEvent event) throws Exception {
        String gameName = ((Control)event.getSource()).getId();
        System.out.println(gameName);

        //create new scene from class factory gives



        TicTacToe testGame = new TicTacToe();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(gameName + ".fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle(gameName);
        stage.setScene(new Scene(root, 450, 450));
        stage.show();

        if(testGame.getWinner() == 3)
        {
            System.out.println("Accessing tic tac toe info");
        }
//
//        while(testGame.getWinner() != 1 || testGame.getWinner() != 0)
//        {
//
//        }

    }

}
