package inf122;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {


    @FXML
    void ff614a(ActionEvent event) {

    }

    @FXML
    void launchGame(ActionEvent event) {
        String gameName = event.getTarget().toString().substring(event.getTarget().toString().indexOf("'"));
        System.out.println(gameName);

    }
}
