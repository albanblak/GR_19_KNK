package components;

import controllers.partials.UserCardController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import models.User;

import java.util.concurrent.atomic.AtomicBoolean;


public class UserCardComponent {

    public Node renderLayout() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/partials/user-card.fxml"));
        Pane pane = loader.load();
        return pane;
    }

/*
    public Node getConent(User user, EventHandler<ActionEvent> editHandler, EventHandler<ActionEvent> deleteHandler,EventHandler<ActionEvent> activeHandler)throws  Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/partials/user-card.fxml"));

        Pane node = loader.load();

        UserCardController controller = loader.getController();
        controller.setUser(user);
        controller.setOnEditAction(editHandler);
        controller.setOnDeleteAction(deleteHandler);
        controller.setOnActiveAction(activeHandler);
        return node;
    }
*/
}
