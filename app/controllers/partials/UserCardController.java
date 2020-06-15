package controllers.partials;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class UserCardController {

   public Node renderLayout() throws Exception{
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(getClass().getResource("../views/partials/user-card.fxml"));
       Pane pane = loader.load();
       return pane;
   }

}
