package controllers;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ChildController extends MainController implements Initializable {


   public MainController parentController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }




    public void setParentController(MainController controller){
        this.parentController = controller;
    }



}
