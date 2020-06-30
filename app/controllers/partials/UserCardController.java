package controllers.partials;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import models.User;



import java.net.URL;
import java.util.ResourceBundle;

public class UserCardController implements Initializable {

    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private CheckBox isActiveCheckBox;
    @FXML
    private Label idLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label nameLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


   public void setUser(User user){
        idLabel.setText("Identifier:" + user.getId());
        nameLabel.setText(user.getName());
        emailLabel.setText(user.getEmail());
        isActiveCheckBox.setSelected(user.getActive());
   }


   public void setOnEditAction(EventHandler<ActionEvent> handler){
        this.editButton.setOnAction(handler);
   }


   public void setOnDeleteAction(EventHandler<ActionEvent> handler){
        this.deleteButton.setOnAction(handler);
   }



   public void setOnActiveAction(EventHandler<ActionEvent> handler){
        this.isActiveCheckBox.setOnAction(handler);
   }



  /* public Node renderLayout() throws Exception{
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(getClass().getResource("../views/partials/user-card.fxml"));
       Pane pane = loader.load();
       return pane;
   }*/



}
