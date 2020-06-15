package controllers;

import components.ErrorPopupComponent;
import components.UserCardComponent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;



public class UserListController implements Initializable {

   @FXML
   private FlowPane contentPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try{
           for (int i =0 ; i <=20; i++){
               UserCardComponent userCard = new UserCardComponent();
               contentPane.getChildren().add(userCard.renderLayout());
           }
        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
    }





}
