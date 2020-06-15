
package controllers.partials;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.ResourceBundle;

public class FindStudentController implements Initializable {

    @FXML private TextField  searchField;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setOnKeyPressedAction(SearchSubmittedHandler handler){
        searchField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER){
                handler.run(searchField.getText());
            }
        });

    }


}