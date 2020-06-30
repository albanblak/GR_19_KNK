import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.*;
import repositories.PunetoretRepository;
import repositories.StudentRepository;
import repositories.UserRepository;
import utils.DbHelper;
import utils.SecurityHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class App extends Application {

   public static void main(String[] args) throws Exception {


       //DbHelper.test();
       Application.launch(args);
       String salt = SecurityHelper.generateSalt();
       String pwd = SecurityHelper.computeHash("1234", salt);
       /* User user = new User(1,"alban","shokualban@gmail.com",pwd,salt,UserRole.Admin,true,new Date(),new Date());
        UserRepository.create(user);
       Punetoret punetoret = null;
        for(int i = 0; i < 20; i++){
            punetoret = new Punetoret( -1,"filani"+i,"fisteku"+i, PunetoretRole.Teknik,12312*i,"filani"+i+"@fistkeu.com","diku"+i,new Date(),"");
         PunetoretRepository.create(punetoret);


        for(int i = 0; i < 10; i++){

            Student student = new Student(-1,"student"+i,"fisteku"+i,"student"+i+"@student.uni-pr.edu",04434*i,"Fiek",i+10,"Prishtine",new Date(),"");
            StudentRepository.create(student);
        } */


   }



    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("views/login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setMinWidth(scene.getWidth());
        stage.setMinHeight(scene.getHeight());
        stage.show();


    }
}
