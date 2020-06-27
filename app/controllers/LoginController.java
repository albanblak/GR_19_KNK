package controllers;

import components.ErrorPopupComponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.User;
import repositories.UserRepository;
import utils.SecurityHelper;


//import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private TextField pwdField;
    @FXML
    private Button loginButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

   @FXML
   private void onLoginButtonClicked(ActionEvent event){
        try {
            int id =  Integer.parseInt(emailField.getText());
            String pwd = pwdField.getText();

            if(login(id,pwd)==null){
               throw new Exception("Invalid credentials");

            }




            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/main-screen.fxml"));
            Pane root = loader.load();
            MainController mainController = loader.getController();
            mainController.setView( mainController.STUDENT_LIST_VIEW);
            Scene scene = new Scene(root);
            Stage primaryStage =(Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setMinWidth(scene.getWidth());
            primaryStage.setMinHeight(scene.getHeight());
            System.out.println("button clicked");

        }catch (Exception e){
           System.out.println("something went wrong");
           e.printStackTrace();
           ErrorPopupComponent.show(e);

        }
   }



   private User login(int id, String password) throws Exception {
       User user = UserRepository.find(id);
       if(user == null)
           return null;
       return SecurityHelper.computeHash(password,user.getSalt()).equals(user.getPassword()) ? user : null;
   }



}
