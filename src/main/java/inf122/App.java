package inf122;

import inf122.gui.GameRegistry;
import inf122.gui.Savage;
import inf122.savage.plugins.Checkers;
import inf122.savage.plugins.TicTacToe;
import javafx.application.Application;


public class App
{

    public static void main( String[] args ){
//        GameRegistry.addGame(new TicTacToe());
//        GameRegistry.addGame(new Checkers());
        Application.launch(Savage.class, args);
    }
}