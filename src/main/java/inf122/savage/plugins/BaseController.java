package inf122.savage.plugins;

import inf122.savage.engine.GameState;
import inf122.savage.engine.Player;
import inf122.savage.util.ViewComponentGenerator;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static inf122.savage.engine.GameState.PLAYER_ONE;
import static inf122.savage.engine.GameState.PLAYER_TWO;

public class BaseController implements EventHandler<MouseEvent> {
    private BaseView view;
    private static BaseGame model;

    public BaseController(BaseGame game){
        this.model = game;
    }

    @FXML
    AnchorPane gameBoard;

    @FXML
    Text player1Name;

    @FXML
    Text player1Wins;

    @FXML
    Text player1Score;

    @FXML
    Text player2Name;

    @FXML
    Text player2Wins;

    @FXML
    Text player2Score;

    Stage stage = new Stage();


    public void show() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gameView.fxml"));
        loader.setController(this);

        Parent root = loader.load();
        view = ViewComponentGenerator.getViewComponent(gameBoard, model, this);
        view.draw(this.model);
        displayPlayerInfo(this.model);



        Stage stage = new Stage();
        stage.setResizable(false);
        
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle(this.model.getName());
        stage.setScene(new Scene(root, 860, 630));

        stage.show();
    }

    public void displayPlayerInfo(BaseGame game)
    {
        player1Name.setText(game.getState().getPlayer(PLAYER_ONE).getName());
        System.out.println("********** WIN COUNT: "+ game.getState().getPlayer(PLAYER_ONE).getWinCount() );
        player1Wins.setText(String.valueOf(game.getState().getPlayer(PLAYER_ONE).getWinCount()));
        player1Score.setText(String.valueOf(game.getState().getCurrentPlayer().getScore()));

        player2Name.setText(game.getState().getPlayer(PLAYER_TWO).getName());
        player2Wins.setText(String.valueOf(game.getState().getPlayer(PLAYER_TWO).getWinCount()));
        player2Score.setText(String.valueOf(game.getState().getPlayer(PLAYER_TWO).getScore()));

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
            this.view.draw(this.model);
            displayPlayerInfo(this.model);
        } else {
            System.out.println("Not a valid move");
        }

        if(this.model.getWinner() == BaseGame.GAME_NOT_OVER)
        {
            System.out.println("here");
            // Game is not over
        }
        else if (this.model.getWinner() == PLAYER_ONE || this.model.getWinner() == PLAYER_TWO )
        {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Game Over!");
            alert.setHeaderText(this.model.getWinningPlayer().getName() + " wins!");
            alert.setContentText("Play again?");

            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK)
            {
               this.model.resetGame();
               this.view.resetBoard(this.model);
               this.view.draw(this.model);
            }
            else
            {
                this.stage.close();
            }
        }
        else if (this.model.getWinner() == BaseGame.TIE){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Game Over!");
        alert.setHeaderText("It's a tie!");
        alert.setContentText("Play again?");

        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK)
        {
            this.model.resetGame();
            this.view.resetBoard(this.model);
            this.view.draw(this.model);
        }
        else
        {
            this.stage.close();
        }
    }
}}
