package inf122.savage.plugins;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BaseController implements EventHandler<MouseEvent> {
    private BaseView view;
    private BaseGame model;

    public BaseController(BaseGame game){
        this.model = game;
    }

    @FXML
    AnchorPane gameBoard;



    public void show() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("tictactoe.fxml"));
        loader.setController(this);


        Parent root = loader.load();

        view = new TicTacToeView(gameBoard, this.model, this);


        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("Base Game");
        stage.setScene(new Scene(root, 450, 450));
        stage.show();
    }

    @Override
    public void handle(MouseEvent e){
        // Can get canvas and draw on it now too.
        // But at the very least, make the move?

        String id = ((Node) e.getSource()).getId();
        int index = id.indexOf(";");
        int row = Integer.parseInt(id.substring(0, index));
        int col = Integer.parseInt(id.substring(index + 1, id.length()));
        System.out.println(row + "." + col);
        if (this.model.move(row, col)){
            System.out.println("Was a valid move");
            this.view.draw(this.model, this.gameBoard);
        } else {
            System.out.println("Not a valid move");
        }
    }
}
