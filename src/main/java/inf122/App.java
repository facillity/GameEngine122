package inf122;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("InitView.fxml"));
        stage.setTitle("INF122 GameEngine");
        stage.setScene(new Scene(root, 650, 400));
        stage.show();

    }


    public static void main( String[] args ){
        launch(args);
    }
}