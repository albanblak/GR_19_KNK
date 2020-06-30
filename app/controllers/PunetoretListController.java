package controllers;

import components.ErrorPopupComponent;
import components.FindStudentComponent;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import models.Punetoret;
import models.PunetoretRole;
import models.Student;
import repositories.PunetoretRepository;
import repositories.StudentRepository;


import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PunetoretListController extends  ChildController implements Initializable {

   @FXML
   private TableView<Punetoret> tableView;
   @FXML
   private TableColumn<Punetoret,Integer> idColumn;
   @FXML
   private TableColumn<Punetoret,String> emriColumn;
   @FXML
   private TableColumn<Punetoret,String> mbiemriColumn;
   @FXML
   private TableColumn<Punetoret, PunetoretRole> roliColumn;
   @FXML
   private TableColumn<Punetoret,Integer> telColumn;
   @FXML
   private TableColumn<Punetoret,String> emailColumn;
   @FXML
   private TableColumn<Punetoret,String> vendiColumn;
   @FXML
   private TableColumn<Punetoret, Date> krijuarColumn;






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       try {
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
         emriColumn.setCellValueFactory(new PropertyValueFactory<>("emri"));
           mbiemriColumn.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
           roliColumn.setCellValueFactory(new PropertyValueFactory<>("roli"));
           telColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
           emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
           vendiColumn.setCellValueFactory(new PropertyValueFactory<>("vendi"));
           krijuarColumn.setCellValueFactory(new PropertyValueFactory<>("regjistruar"));
           tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            showPunetoret(0);
       }catch (Exception e){
           ErrorPopupComponent.show(e);
       }
    }

    private Punetoret findPunetoret() throws Exception{
       String text = new FindStudentComponent().showDialog();
       return PunetoretRepository.find(text);
    }


    /*@FXML
    private void onViewMenuItemClick(ActionEvent event){
        Punetoret selectd = tableView.getSelectionModel().getSelectedItem();
        if(selectd == null)
            return;
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/" + MainController.PUNETORET_DETAILS_VIEW+".fxml"));
            Pane pane = loader.load();

            PunetoretDetailsController controller = loader.getController();
            controller.setModel(selectd);



        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
    }
*/


  private void showPunetoret(int page) throws Exception{
        List<Punetoret> punetorets = PunetoretRepository.list(10,page);
        tableView.getItems().clear();
        tableView.setItems(FXCollections.observableList(punetorets));
  }

    @FXML
    public void onViewMenuItemClick(ActionEvent event) {
      System.out.println("bravo");
        Punetoret selectd = tableView.getSelectionModel().getSelectedItem();
        if(selectd == null)
            return;
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/" + MainController.PUNETORET_DETAILS_VIEW+".fxml"));
            Pane pane = loader.load();

            PunetoretDetailsController controller = loader.getController();
            controller.setModel(selectd);
            controller.setEditable(false);

            parentController.setView(MainController.PUNETORET_DETAILS_VIEW,pane,controller);

        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
    }
}
