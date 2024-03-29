package inf122.savage.util;

import inf122.savage.plugins.BaseController;
import inf122.savage.plugins.BaseGame;
import inf122.savage.plugins.BaseView;
import javafx.scene.layout.AnchorPane;

import java.lang.reflect.InvocationTargetException;

public abstract class ViewComponentGenerator {
    public static BaseView getViewComponent(AnchorPane pane, BaseGame game, BaseController controller)
        throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<? extends BaseView> viewClass = game.getViewClass();
        return viewClass.getConstructor(AnchorPane.class, BaseGame.class, BaseController.class)
                .newInstance(pane, game, controller);
    }
}
