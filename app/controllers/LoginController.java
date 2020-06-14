package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

   @FXML
   private void onLoginButtonClicked(ActionEvent event){
        try {
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

        }
   }


}
