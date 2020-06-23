import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.User;
import models.UserRole;
import repositories.UserRepository;
import utils.DbHelper;
import utils.SecurityHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App extends Application {

   public static void main(String[] args) throws Exception {

       DbHelper.getConnetion();
       //DbHelper.test();
       Application.launch(args);
        User user  = null;
      // User obj = new User(1,"alban","albani@gail.com","12345","1234", UserRole.Admin,true, new Date(),new Date());
       for(int i = 0; i < 10; i++){
           String salti = SecurityHelper.generateSalt();
           String pwd = SecurityHelper.computeHash("test",salti);
         user= new User(i,"user"+i,"mail"+i+"@gmail.com",pwd,salti,UserRole.Admin,true,new Date(), new Date());
          UserRepository.create(user);

       }

        List<User> list = UserRepository.list(6,0);

      for(int i = 0; i< list.size(); i++){
          System.out.println(list.get(i).getId());
          System.out.println(list.get(i).getName());

      }


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
