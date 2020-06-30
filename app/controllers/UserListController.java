package controllers;

import com.sun.tools.javac.Main;
import components.ErrorPopupComponent;
import components.PaginationComponent;
import components.UserCardComponent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import models.User;
import repositories.UserRepository;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class UserListController extends  ChildController implements Initializable {
    private final int PAGE_SIZZE = 10;

    private PaginationComponent paginationComponent;


   @FXML
   private FlowPane contentPane;
   @FXML
   private HBox paginationPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        try {
            paginationComponent = new PaginationComponent(userCount(),PAGE_SIZZE);
            paginationComponent.render(paginationPane,(page) ->{
                try {
                    showUsers(page);
                }catch (Exception e){
                    ErrorPopupComponent.show(e);
                }
            });

        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }*/

        try{
            for (int i =0 ; i <=20; i++){
                UserCardComponent userCard = new UserCardComponent();
                contentPane.getChildren().addAll(userCard.renderLayout());
            }
        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
    }


    private int userCount() throws  Exception{
        return UserRepository.count();
    }



  private void showUsers(int page) throws Exception{
      /*  contentPane.getChildren().clear();
        List<User> users = UserRepository.list(PAGE_SIZZE,page);
        UserCardComponent userCardComponent = new UserCardComponent();
        for(User user: users){
          //  contentPane.getChildren().add(userCardComponent.getConent(user, e-> showUser(user)
           // ,e -> removeUser(user),e -> changeUserState(user)));
            contentPane.getChildren().add(userCardComponent.renderLayout());
        }*/

      try{
          for (int i =0 ; i <=20; i++){
              UserCardComponent userCard = new UserCardComponent();
              contentPane.getChildren().add(userCard.renderLayout());
          }
      }catch (Exception e){
          ErrorPopupComponent.show(e);
      }
    }




    private void removeUser(User user){
        try {
           UserRepository.remove(user.getId());

           int currPage = paginationComponent.getCursor();
           paginationComponent = new PaginationComponent(userCount(),PAGE_SIZZE);
           paginationComponent.render(paginationPane,(page) ->{
               try {
                   showUsers(page);
               }catch (Exception e){
                   ErrorPopupComponent.show(e);
               }
           });

        }catch (Exception e){
           ErrorPopupComponent.show(e);
        }
    }


    private void changeUserState(User user){
        try {
            user.setActive(!user.getActive());
            UserRepository.update(user);
        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
    }
    private void showUser(User user){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/" + MainController.USERS_DETAILS_VIEW + ".fxml"));

            Pane pane = loader.load();
            UserDetailsController controller = loader.getController();
            controller.setModel(user);

            parentController.setView(MainController.USERS_LIST_VIEW,pane,controller);

        }catch (Exception e){
            ErrorPopupComponent.show(e);
        }
    }

}
