package controllers;

import components.ErrorPopupComponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import models.Punetoret;
import models.PunetoretRole;
import models.Student;
import models.view.PunetoretViewModel;
import models.view.StudentViewModel;
import repositories.PunetoretRepository;
import utils.DateHelper;
import utils.Util;

import java.net.URL;
import java.util.ResourceBundle;

public class PunetoretDetailsController extends ChildController implements Initializable {

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
    private TextField vendiField;
    @FXML
    private TextField roliField;
    @FXML
    private TextField telField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField regjistruarField;

    private PunetoretViewModel viewModel;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void onCancelButtonCLick(ActionEvent event){
        try {
           parentController.setView(MainController.PUNETORET_LIST_VIEW);
        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
    }

    @FXML
    private void onSaveButtonClick(ActionEvent event){
        try {
        if(viewModel.getId() > 0){
            PunetoretRepository.update(viewModel.getModel());
        }else{
            PunetoretRepository.create(viewModel.getModel());
        }
        parentController.setView(MainController.PUNETORET_LIST_VIEW);
        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
    }


    public void setModel (Punetoret model){
        viewModel = new PunetoretViewModel(model);
        idField.setText(Integer.toString(viewModel.getId()));
        emriField.setText(viewModel.getEmri());
        mbiemriField.setText(viewModel.getMbiemri());
        ditelindjaField.setText(DateHelper.toSqlFormat(viewModel.getDitelindja()));
        if(viewModel.getRoli() == PunetoretRole.Teknik){
            roliField.setText("Teknik");
        }else if(viewModel.getRoli() == PunetoretRole.Recepsionist){
            roliField.setText("Recepsionist");
        }else if(viewModel.getRoli() == PunetoretRole.Shef){
            roliField.setText("Shef");
        }else if(viewModel.getRoli() == PunetoretRole.Referent){
            roliField.setText("Referent");
        }
        emailField.setText(viewModel.getEmail());
        telField.setText(Integer.toString(viewModel.getTel()));

        vendiField.setText(viewModel.getVendi());
        regjistruarField.setText(DateHelper.toSqlFormat(viewModel.getRegjistruar()));


        emriField.textProperty().bindBidirectional(viewModel.emriProperty());
        mbiemriField.textProperty().bindBidirectional(viewModel.mbiemriProperty());
        ditelindjaField.textProperty().bindBidirectional(viewModel.ditelindjaProperty());
        emailField.textProperty().bindBidirectional(viewModel.emailProperty());
        roliField.textProperty().bindBidirectional(viewModel.roliProperty());

        telField.textProperty().addListener((ov,oldVal,newVal) -> {
            if(!Util.isEmpty(newVal)){
                try {
                    viewModel.setTel(Integer.parseInt(newVal));
                }catch (Exception e){
                    System.out.println("he");
                }
            }
        });


        vendiField.textProperty().bindBidirectional(viewModel.vendiProperty());

    }


    public void setEditable(boolean value){
        isEditable = value;
        idField.setDisable(!isEditable);
        emriField.setDisable(!isEditable);
        mbiemriField.setDisable(!isEditable);
        ditelindjaField.setDisable(!isEditable);
        emailField.setDisable(!isEditable);
        telField.setDisable(!isEditable);
        roliField.setDisable(!isEditable);
        vendiField.setDisable(!isEditable);
        regjistruarField.setDisable(!isEditable);

    }
}
