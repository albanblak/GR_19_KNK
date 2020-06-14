package controllers;

import components.ErrorPopupComponent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private VBox contentPane;

    public final String STUDENT_LIST_VIEW = "student-list";
    public final String STUDENT_DETAILS_VIEW = "student-details";


    private static final String VIEW_PATH = "../views";



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


   @FXML
   private void onStudentNavButtonClick(ActionEvent event){
        try {
           this.setView(STUDENT_DETAILS_VIEW);
           System.out.println("Student clicked");
        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
   }







    public void setView(String view) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewPath(view)));
        Pane pane = null;
        switch (view) {
            case STUDENT_DETAILS_VIEW:
                pane = loader.load();
                contentPane.setAlignment(Pos.TOP_LEFT);
                break;
            case STUDENT_LIST_VIEW:
                pane = loader.load();
                break;
            default:
                throw new Exception("ERR_VIEW_NOT_FOUND");


        }
        contentPane.getChildren().clear();
        contentPane.getChildren().add(pane);
        contentPane.setVgrow(pane, Priority.ALWAYS);


    }



    private  String viewPath(String view){
        return VIEW_PATH + "/" +  view + ".fxml";
    }


}
