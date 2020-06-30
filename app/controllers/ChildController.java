package controllers;

import com.sun.tools.javac.Main;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class ChildController extends  MainController implements Initializable{
    public MainController parentController;

    public void setParentController(MainController controller){
        this.parentController =  controller;
    }
}
