package inf122.gui;

import inf122.TicTacToe;
import inf122.savage.util.GameFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Savage extends Application {

    @Override
    public void init() throws Exception {
        GameFactory.registerGame("tictactoe", TicTacToe.class);
        //Will register other games like this too.
        //The string is the ID you use for the buttons in the FXML.
    }



    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("InitView.fxml"));
        Parent root = loader.load();
        SavageController controller = loader.getController();


        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root, 300, 285));
        primaryStage.show();
    }


}
