package components;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class ErrorPopupComponent {

    public static void show(Exception e){
        e.printStackTrace();
        String message = e.getMessage();
        if(message == null || message.length()==0){
            message = e.toString();
        }
        show(message);
    }

   public static void show(String message){
        show(message, "Error");
   }


    public static void show(String message, String title){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(title);

        Label label = new Label(message);
        label.setWrapText(true);
        label.setPrefWidth(300);
        label.setPrefHeight(300);
        StackPane stackPane = new StackPane(label);
        alert.getDialogPane().setContent(stackPane);
        alert.showAndWait();
    }
}
