package components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class UserCardComponent {

    public Node renderLayout() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/partials/user-card.fxml"));
        Pane pane = loader.load();
        return pane;
    }


}
