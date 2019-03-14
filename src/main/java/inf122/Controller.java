package inf122;

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

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(gameName + ".fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle(gameName);
        stage.setScene(new Scene(root, 450, 450));
        stage.showAndWait();

    }



}
