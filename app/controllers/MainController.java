package controllers;

import com.sun.glass.ui.EventLoop;
import components.ErrorPopupComponent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import models.Student;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private VBox contentPane;
    @FXML
    private Label label;

    public static  final String STUDENT_LIST_VIEW = "student-list";
    public static final String STUDENT_DETAILS_VIEW = "student-details";
    public static final String PUNETORET_LIST_VIEW = "punetoret-list";
    public static final String PUNETORET_DETAILS_VIEW = "puntetoret-details";
    public static final String USERS_LIST_VIEW = "user-list";
    public static final String USERS_DETAILS_VIEW = "user-details";


    private static final String VIEW_PATH = "../views";



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.setText("hello");
    }


   @FXML
   private void onStudentNavButtonClick(ActionEvent event){
        try {
           this.setView(STUDENT_LIST_VIEW);
           System.out.println("Student clicked");
        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
   }

   @FXML
   private void onStudentMenuClick(ActionEvent event){
        try{
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource(viewPath(STUDENT_DETAILS_VIEW)));
          Pane pane1 = loader.load();

          StudentDetailsController controller = loader.getController();
          controller.setModel(new Student());
          controller.setEditable(true);

          this.setView(STUDENT_DETAILS_VIEW,pane1,controller);

        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
   }

   @FXML
   private void onPunetoretNavClick(ActionEvent event){
        try{
           this.setView(PUNETORET_LIST_VIEW);
        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
   }

   @FXML
   private void onPuntetoretMenuClick(ActionEvent event){
        try {
            this.setView(PUNETORET_DETAILS_VIEW);

        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
   }

   @FXML
   private void onUsersMenuClick(ActionEvent event){
        try {
           this.setView(USERS_LIST_VIEW);
        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
   }

   @FXML
   private void onExitButtonMenuClick(ActionEvent event){
        try {
            Stage primaryStage = (Stage)label.getScene().getWindow();
            primaryStage.close();
        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
   }

   @FXML
   private  void onLogoutButtonNavClick(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(viewPath("login")));
            Scene scene  = new Scene(root);
            Stage primaryStage = null;
            if(event.getSource() instanceof MenuItem){
                primaryStage = (Stage)label.getScene().getWindow();
            }else{
                primaryStage =(Stage)((Node) event.getSource()).getScene().getWindow();
            }
            primaryStage.setScene(scene);
        }
        catch (Exception e){
            ErrorPopupComponent.show(e);
        }
   }

   @FXML
   private void onInsertStudentClick(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(viewPath(STUDENT_DETAILS_VIEW)));

            Pane pane = loader.load();
            StudentDetailsController controller = loader.getController();
            controller.setModel(new Student());
            controller.setEditable(true);
        }catch (Exception e){

        }
   }


    public void setView(String view) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent node = null ;
        //Pane pane = null;
        switch (view) {
            case STUDENT_DETAILS_VIEW:
                loader.setLocation(getClass().getResource(viewPath(STUDENT_DETAILS_VIEW)));
                node = loader.load();
                contentPane.setAlignment(Pos.TOP_LEFT);
                break;
            case STUDENT_LIST_VIEW:
                loader.setLocation(getClass().getResource(viewPath(STUDENT_LIST_VIEW)));
                node = loader.load();
                break;
            case PUNETORET_LIST_VIEW:
                loader.setLocation(getClass().getResource(viewPath(PUNETORET_LIST_VIEW)));
                node = loader.load();
                break;
            case PUNETORET_DETAILS_VIEW:
                loader.setLocation(getClass().getResource(viewPath(PUNETORET_DETAILS_VIEW)));
                node = loader.load();
                break;
            case USERS_LIST_VIEW:
                try{

                    loader.setLocation(getClass().getResource(viewPath(USERS_LIST_VIEW)));
                    System.out.println("e dej qitu o mire");
                    node = loader.load();
                    System.out.println("dej qitu o mire");
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("hello");
                throw new Exception("ERR_VIEW_NOT_FOUND");
        }

        ChildController controller = loader.getController();
        setView(view,node,controller);
    }


    public void setView(String screen,Parent node, ChildController controller) throws Exception{
        controller.setParentController(this);

       contentPane.getChildren().clear();
        contentPane.getChildren().add(node);
       contentPane.setVgrow(node, Priority.ALWAYS);
        switch (screen) {

            case STUDENT_DETAILS_VIEW:
                contentPane.setAlignment(Pos.TOP_CENTER);
                break;
            case PUNETORET_LIST_VIEW:
                contentPane.setAlignment(Pos.TOP_LEFT);
                break;
           // case USERS_DETAILS_VIEW:
            //    contentPane.setAlignment(Pos.TOP_CENTER);
             //   break;
            case STUDENT_LIST_VIEW:
                contentPane.setAlignment(Pos.TOP_CENTER);
                break;

            case USERS_LIST_VIEW:
                contentPane.setAlignment(Pos.TOP_CENTER);
                break;

            case USERS_DETAILS_VIEW:
                contentPane.setAlignment(Pos.TOP_CENTER);
                break;
            case PUNETORET_DETAILS_VIEW:
                contentPane.setAlignment(Pos.CENTER);
                break;
            default:
                throw new Exception("ERR_SCREEN_NOT_FOUND");
        }

    }



    private  String viewPath(String view){
        return VIEW_PATH + "/" +  view + ".fxml";
    }


}
