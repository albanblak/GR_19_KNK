package controllers;

import components.ErrorPopupComponent;
import components.FindStudentComponent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentListController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

  @FXML
  private void setOnFindButtonClick(ActionEvent event){
        this.findProduct();
  }

    private void findProduct() {
        try {
           String res = new FindStudentComponent().showDialog();
           System.out.println(res);
        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
    }



}
