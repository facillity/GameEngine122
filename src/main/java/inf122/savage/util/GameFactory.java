package inf122.savage.util;

import inf122.savage.plugins.BaseController;
import inf122.savage.plugins.BaseGame;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public abstract class GameFactory {
    private static HashMap<String, Class<? extends BaseGame>> registry = new HashMap<String, Class<? extends BaseGame>>();

    public static void registerGame(String gameName, Class<? extends BaseGame> init){
        registry.put(gameName, init);
    }

    public static BaseController createGame(String gameName, String player1, String player2)
    throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<? extends BaseGame> modelClass = registry.get(gameName);

        BaseGame model = modelClass.getConstructor(String.class, String.class).newInstance(player1, player2);
        BaseController controller = new BaseController(model);
        return controller;
    }
}
