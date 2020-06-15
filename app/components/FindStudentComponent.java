package components;

import controllers.partials.FindStudentController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FindStudentComponent {

   public String showDialog() throws Exception{
       final StringBuilder sb = new StringBuilder("");
       Stage stage = new Stage();

       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(getClass().getResource("../views/partials/find-student.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);

       FindStudentController controller = loader.getController();
       controller.setOnKeyPressedAction(val ->{
           sb.append(val);
           stage.close();
       });

       stage.setTitle("Find Student");
       stage.initModality(Modality.APPLICATION_MODAL);
       stage.initStyle(StageStyle.UTILITY);
       stage.setScene(scene);
       stage.showAndWait();

       return sb.toString();

   }



}
