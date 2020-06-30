package controllers;

import components.ErrorPopupComponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Student;
import models.view.StudentViewModel;
import repositories.StudentRepository;
import utils.DateHelper;
import utils.Util;


import java.net.URL;
import java.util.ResourceBundle;

public class StudentDetailsController extends  ChildController implements Initializable {


    private boolean isEditable = false;
    @FXML
    private TextField idField;
    @FXML
    private TextField emriField;
    @FXML
    private TextField mbiemriField;
    @FXML
    private TextField ditelindjaField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField telField;
    @FXML
    private TextField fkField;
    @FXML
    private TextField dhomaField;
    @FXML
    private TextField vendiField;
    @FXML
    private TextField regjistruarField;


    private StudentViewModel viewModel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void onCancelButtonClick(ActionEvent event){
        try {
           parentController.setView(MainController.STUDENT_LIST_VIEW);
        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
    }

    @FXML
    private void onSaveButtonClick(ActionEvent event){
        try{
            if(viewModel.getId() > 0){
                StudentRepository.update(viewModel.getModel());
            }else{
                StudentRepository.create(viewModel.getModel());
            }
            parentController.setView(MainController.STUDENT_LIST_VIEW);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setEditable(boolean value){
        isEditable = value;
        idField.setDisable(!isEditable);
        emriField.setDisable(!isEditable);
        mbiemriField.setDisable(!isEditable);
        ditelindjaField.setDisable(!isEditable);
        emailField.setDisable(!isEditable);
        telField.setDisable(!isEditable);
        fkField.setDisable(!isEditable);
        dhomaField.setDisable(!isEditable);
        vendiField.setDisable(!isEditable);
        regjistruarField.setDisable(!isEditable);

    }


    public void setModel (Student model){
       viewModel = new StudentViewModel(model);
       idField.setText(Integer.toString(viewModel.getId()));
       emriField.setText(viewModel.getEmri());
       mbiemriField.setText(viewModel.getMbiemri());
       ditelindjaField.setText(DateHelper.toSqlFormat(viewModel.getDitelindja()));
       emailField.setText(viewModel.getEmail());
      telField.setText(Integer.toString(viewModel.getTel()));
       fkField.setText(viewModel.getFk());
       dhomaField.setText(Integer.toString(viewModel.getDhoma()));
       vendiField.setText(viewModel.getVendi());
       regjistruarField.setText(DateHelper.toSqlFormat(viewModel.getRegjistruar()));


       emriField.textProperty().bindBidirectional(viewModel.emriProperty());
       mbiemriField.textProperty().bindBidirectional(viewModel.mbiemriProperty());
       ditelindjaField.textProperty().bindBidirectional(viewModel.ditelindjaProperty());
       emailField.textProperty().bindBidirectional(viewModel.emailProperty());
           telField.textProperty().addListener((ov,oldVal,newVal) -> {
        if(!Util.isEmpty(newVal)){
               try {
                   viewModel.setTel(Integer.parseInt(newVal));
               }catch (Exception e){
    System.out.println("he");
               }
           }
       });
       fkField.textProperty().bindBidirectional(viewModel.fkProperty());
       dhomaField.textProperty().addListener((ov,oldVal,newVal) ->{
           if(!Util.isEmpty(newVal)){
               try {
                  viewModel.setDhoma(Integer.parseInt(newVal));
               }catch (Exception e){

               }
           }
       });

       vendiField.textProperty().bindBidirectional(viewModel.vendiProperty());




    }
}
