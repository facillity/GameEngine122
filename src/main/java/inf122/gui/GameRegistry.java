package inf122.gui;

import inf122.savage.plugins.GamePlugin;

import java.util.ArrayList;
import java.util.List;

public abstract class GameRegistry {
    private static List<GamePlugin> games = new ArrayList<GamePlugin>();

    static List<GamePlugin> getGames(){
        return GameRegistry.games;
    }

    public static void addGame(GamePlugin g){
        GameRegistry.games.add(g);
    }
}
