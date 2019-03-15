package inf122.gui;

import inf122.savage.plugins.GamePlugin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Savage extends Application {

    @Override
    public void init() throws Exception {
        System.out.println("Hello World");
    }



    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("InitView.fxml"));
        Parent root = loader.load();
        SavageController controller = loader.getController();

        for(GamePlugin g : GameRegistry.getGames()){
            controller.addGame(g);
        }


        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root, 650, 400));
        primaryStage.show();
    }


}
