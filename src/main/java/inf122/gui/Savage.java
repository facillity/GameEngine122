package inf122.gui;

import inf122.Battleship;
import inf122.Checkers;
import inf122.Othello;
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
        GameFactory.registerGame("othello", Othello.class);
        GameFactory.registerGame("battleship", Battleship.class);
        GameFactory.registerGame("checkers", Checkers.class);
    }



    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("InitView.fxml"));
        Parent root = loader.load();
        SavageController controller = loader.getController();

        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root, 600, 600));

        primaryStage.show();
    }


}
