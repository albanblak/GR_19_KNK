package controllers;

import com.sun.javafx.image.IntPixelGetter;
import components.ErrorPopupComponent;
import components.FindStudentComponent;
import components.PaginationComponent;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.WindowEvent;
import models.Student;
import repositories.StudentRepository;


import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class StudentListController extends  ChildController implements Initializable {
    private PaginationComponent paginationComponent;
   @FXML
   private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, Integer> idColumn;
    @FXML
    private TableColumn<Student, String> emriColumn;
    @FXML
    private TableColumn<Student,String> mbiemriColumn;
    @FXML
    private TableColumn<Student,Date> ditelindjaColumn;
    @FXML
    private TableColumn<Student,String> emailColumn;
    @FXML
    private  TableColumn<Student,Integer> telColumn;
    @FXML
    private TableColumn<Student,String>  fkColumn;
    @FXML
    private TableColumn<Student,Integer> dhomaColumn;
    @FXML
    private TableColumn<Student,String> vendiColumn;
    @FXML
    private TableColumn<Student, Date> regjistruarColumn;
    @FXML
    private ToggleButton multipleButton;
    @FXML
    private MenuItem viewMenuItem;
    @FXML
    private MenuItem editMenuItem;
    @FXML
    private MenuItem  removeMenuItem;

    @FXML
    private HBox paginationPane;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{

            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            emriColumn.setCellValueFactory(new PropertyValueFactory<>("emri"));
            mbiemriColumn.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
            ditelindjaColumn.setCellValueFactory(new PropertyValueFactory<>("ditelindja"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            telColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
            fkColumn.setCellValueFactory(new PropertyValueFactory<>("fk"));
            dhomaColumn.setCellValueFactory(new PropertyValueFactory<>("dhoma"));
            vendiColumn.setCellValueFactory(new PropertyValueFactory<>("vendi"));
            regjistruarColumn.setCellValueFactory(new PropertyValueFactory<>("regjistruar"));
            tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            showStudent(0);
            paginationComponent = new PaginationComponent(studentCoun(),60);
            paginationComponent.render(paginationPane,(page) ->{
                try{

                    showStudent(page);
                }catch (Exception e){
                    ErrorPopupComponent.show(e);
                }
            });
        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
    }

    @FXML
    private void onViewMenuItemclick(ActionEvent event){
        Student selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/" + MainController.STUDENT_DETAILS_VIEW + ".fxml"));
            Pane pane1 = loader.load();

            StudentDetailsController controller = loader.getController();
            controller.setModel(selected);
            controller.setEditable(false);

            parentController.setView(MainController.STUDENT_DETAILS_VIEW,pane1,controller);
        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
    }

    @FXML
    private void onEditMenuItemClick(ActionEvent event){
        Student selecetd = tableView.getSelectionModel().getSelectedItem();
        if(selecetd == null)
            return;
       try {
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("../views/" + MainController.STUDENT_DETAILS_VIEW + ".fxml"));
           Pane pane = loader.load();

           StudentDetailsController controller = loader.getController();
           controller.setModel(selecetd);
           controller.setEditable(true);

           parentController.setView(MainController.STUDENT_DETAILS_VIEW,pane,controller);


       } catch (IOException e) {
           e.printStackTrace();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    @FXML
    private void onRemoveMenuItemClick(ActionEvent event)  {
        try{
            Student selectd = tableView.getSelectionModel().getSelectedItem();
            if(selectd == null)
                return;
            StudentRepository.remove(selectd.getId());
        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
    }

   @FXML
   private void onContextMenuShowing(WindowEvent event){
        int selected = tableView.getSelectionModel().getSelectedItems().size();
        if(selected == 0){
            viewMenuItem.setDisable(true);
            editMenuItem.setDisable(true);
            removeMenuItem.setDisable(true);
        }else if(selected == 1){
            viewMenuItem.setDisable(false);
            editMenuItem.setDisable(false);
            removeMenuItem.setDisable(false);
        }else{
            viewMenuItem.setDisable(true);
            editMenuItem.setDisable(true);
            removeMenuItem.setDisable(false);

        }

   }


    @FXML
    private void setMultipleButtonClick(ActionEvent event){
        if(multipleButton.isSelected()){
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }else{
            tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        }
    }




    private void showStudent(int page) throws Exception{
        List<Student> studentList = StudentRepository.list(50,page);
        tableView.getItems().clear();
        tableView.setItems(FXCollections.observableList(studentList));
    }


    @FXML
    private void onFindButtonClick(ActionEvent event) {
        try{
            List<Student> students = this.findStudents();
            if(students.get(0)!=null) {
                tableView.getItems().clear();

                tableView.setItems(FXCollections.observableList(students));
            }

        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
    }

    @FXML
    private void onShowAllButtonClick(ActionEvent event){
        try{
            showStudent(0);

        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }

    }


    private List<Student> findStudents() throws Exception{
        List<Student> students = new ArrayList<>();
        String text = new FindStudentComponent().showDialog();
       students.add(StudentRepository.find(text));
       //System.out.println(students.get(0).getEmail());
       if(students.size() == 0){
           return null;
       }
       return students;

    }

    private void showStudents(int page) throws Exception{
        List<Student> students = StudentRepository.list(0,page);
        tableView.getItems().clear();
        tableView.setItems(FXCollections.observableList(students));
    }

    private int studentCoun()throws Exception{
        return StudentRepository.count();
    }


}
